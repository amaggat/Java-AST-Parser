package Model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.*;
import org.xml.sax.InputSource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XmlFileParser implements IParser, IPathParser {

    private static final Logger logger = LogManager.getLogger(XmlFileParser.class);

    public XmlFileParser() {
    }

    @Override
    public Node parse(String path) {
        XmlFileNode node = new XmlFileNode();
        node.setAbsolutePath(path);
        return parse(node);
    }

    @Override
    public Node parse(Node node){
        try {
            XmlFileNode xmlFileNode = (XmlFileNode) node;
            FileInputStream fis = new FileInputStream(node.getAbsolutePath());
            InputSource is = new InputSource(fis);

            Document document = JciaDomHelper.parse(is);

            document.getDocumentElement().normalize();
            xmlFileNode.setDocument(document);

            xmlFileNode = (XmlFileNode) generateNodeTree(xmlFileNode);
            return xmlFileNode;
        } catch (FileNotFoundException e) {
            logger.error(String.format("not found file [%s]", node.getAbsolutePath()));
        } catch (Exception e) {
            logger.error(String.format("Error when parsing [%s]", node.getAbsolutePath()));
            //log for client
        }
        return null;
    }

    private Node generateNodeTree(Node root) {
        List<Node> children = new ArrayList<>();

        NodeList nodeList = getNodeList(root);
        if (nodeList == null) return root;
        else {
            for (int i = 0; i < nodeList.getLength(); i++) {
                org.w3c.dom.Node child = nodeList.item(i);
                if (child instanceof Element) {
                    // Create new tag node
                    XmlTagNode newChildNode = createNewTagNode(child, root);

                    // Set path for childNode
                    String path = root.getAbsolutePath() + File.separator + newChildNode.getTagName() +
                            ":" + newChildNode.getLineNumber() + ":" + newChildNode.getColumnNumber();
                    newChildNode.setAbsolutePath(path);

                    // Set parent
                    newChildNode.setParent(root);

                    children.add(generateNodeTree(newChildNode));
                }
            }
        }
        root.setChildren(children);

        return root;
    }

    private NodeList getNodeList(Node node) {
        NodeList nodeList = null;

        if (node instanceof XmlFileNode) {
            nodeList = ((XmlFileNode) node).getDocument().getChildNodes();
        } else if (node instanceof XmlTagNode) {
            org.w3c.dom.Node domNode = ((XmlTagNode) node).getDomNode();
            if (domNode == null) return null;

            nodeList = domNode.getChildNodes();
        }
        return nodeList;
    }

    private XmlTagNode createNewTagNode(org.w3c.dom.Node domNode, Node parent) {
        XmlTagNode node = new XmlTagNode();
        node.setDomNode(domNode);
        // Set tag name
        String nodeName = null;
        if (parent instanceof XmlTagNode)
            nodeName = parent.getName() + ">" + domNode.getNodeName();
        else if (parent instanceof XmlFileNode)
            nodeName = domNode.getNodeName();
        if (nodeName != null) node.setName(nodeName);

        node.setTagName(domNode.getNodeName());

        // set content for leaf node
        for (int i = 0; i < domNode.getChildNodes().getLength(); i++) {
            org.w3c.dom.Node child = domNode.getChildNodes().item(i);
            if (child instanceof Text) {
                Text text = (Text) child;
                String wholeText = StringHelper.strip(text.getWholeText());
                if (!wholeText.isEmpty()) {
                    node.setContent(wholeText);
                }
            }
        }
        if (!domNode.hasChildNodes()) {
            String content = domNode.getTextContent();
            if (content.isEmpty()) node.setContent(content);
        }

        // Set attributes to created node.
        Map<String, String> attrs = getListAttributes(domNode);
        node.setAttributes(attrs);
        // set number Attributes of node
        node.setNumOfAttr(attrs.size());

        for (Map.Entry<String, String> entry : attrs.entrySet()) {
            String locElement = entry.getKey();
            if (locElement.startsWith("loc:")) {
                node.setNumOfAttr(node.getNumOfAttr() - 1);
                if (locElement.equals("loc:column"))
                    node.setColumnNumber(Integer.parseInt(entry.getValue()));
                else if (locElement.equals("loc:line"))
                    node.setColumnNumber(Integer.parseInt(entry.getValue()));
            } else {
                node.addToListAttr(entry.getKey() + ": " + entry.getValue());
            }
        }
        return node;
    }

    private Map<String, String> getListAttributes(org.w3c.dom.Node node) {
        Map<String, String> result = new HashMap<>();
        NamedNodeMap attrs = node.getAttributes();

        if (attrs == null) return result;

        int attrLength = attrs.getLength();
        if (attrLength == 0) return result;
        else {
            for (int i = 0; i < attrLength; i++) {
                org.w3c.dom.Node attr = attrs.item(i);
                String name = attr.getNodeName();
                String value = attr.getNodeValue();
                result.put(name, value);
            }
        }
        return result;
    }
}
