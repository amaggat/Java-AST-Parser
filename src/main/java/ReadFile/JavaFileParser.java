package ReadFile;

import org.eclipse.jdt.core.dom.*;

import Package.*;
import Class.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;


public class JavaFileParser {

    final ArrayList<String> springAnnotationDependency = new ArrayList<String>( Arrays.asList("GetMapping", "PostMapping", "Controller", "Service", "Repository", "RequestMapping", "Autowired") );

    public ClassProperties visit(String filePath, final String fileName) throws FileNotFoundException, IOException
    {
        final ClassProperties buffer = new ClassProperties();
        final List<SpringAnnotation> annotationDependency = new ArrayList<>();
        ReadMultipleFile getContent = new ReadMultipleFile();

        ASTParser parser = ASTParser.newParser(AST.JLS8);
        char[] fileContent = getContent.getFileContent(filePath).toCharArray();
        parser.setSource(fileContent);

        CompilationUnit cu = (CompilationUnit) parser.createAST(null);

        cu.accept(new ASTVisitor() {

            @Override
            public boolean visit(TypeDeclaration node) {
                buffer.setName(fileName);
                annotationDependency.addAll(JavaSpringDependency(node.modifiers(), fileName, node));
                buffer.setModifiers(node.modifiers());
                return true;
            }

            @Override
            public boolean visit(FieldDeclaration node) {
                buffer.addField(JavaFieldParser(node, fileName));
                return false;
            }

            @Override
            public boolean visit(MethodDeclaration node)
            {
                if(!node.isConstructor())
                {
                    buffer.addMethod(JavaMethodParser(node, fileName));
                    annotationDependency.addAll(JavaSpringDependency((List<Modifier>)node.modifiers(), fileName, node));
                }
                else
                {
                    buffer.addCons(JavaConstructorParser(node, fileName));
                    annotationDependency.addAll(JavaSpringDependency((List<Modifier>)node.modifiers(), fileName, node));
                }
                return false;
            }
        });

        buffer.addSpringAnootation(annotationDependency);

        return buffer;
    }

    Method JavaMethodParser (MethodDeclaration node, String fileName)
    {
        List<String> parameterStrList = JavaParameter(node);
        String returnType = node.getReturnType2().toString();
        String methodName = node.getName().toString();

        Method method = new Method(returnType, methodName, node.modifiers(), parameterStrList);
//        JavaSpringDependency((List<Modifier>)node.modifiers(), fileName);
//        System.out.println("Method: " + method.getModifiers() + " " + method.getType() + " " + method.getName() + " " + method.getParameters());
        return method;
    }

    Constructor JavaConstructorParser(MethodDeclaration node, String fileName)
    {
        List<String> parameterStrList = JavaParameter(node);
        String constructorName = node.getName().toString();

        Constructor cons = new Constructor(constructorName, parameterStrList, node.modifiers());
//        JavaSpringDependency((List<Modifier>)node.modifiers(), fileName);
//        System.out.println("Constructor: " + cons.getModifiers() + " " + cons.getName() + " " + cons.getParameters());
        return cons;
    }

    Field JavaFieldParser(FieldDeclaration node, String fileName)
    {
        VariableDeclaration z = (VariableDeclaration) node.fragments().get(0);
        String fieldName = z.getName().toString();
        String fieldType = node.getType().toString();

        Field field = new Field(fieldType, fieldName ,node.modifiers());
//       JavaSpringDependency((List<Modifier>)node.modifiers(), fileName);
//        System.out.println("Field: " + field.getModifiers() + " " + field.getType() + " " + field.getName());
        return field;
    }

    List<SpringAnnotation> JavaSpringDependency(List<Modifier> node, String callName, ASTNode module)
    {
        List<SpringAnnotation> dep = new ArrayList<>();
        dep.addAll(findAnnotation(node, callName, module));
        return dep;
    }

    List<String> JavaParameter(MethodDeclaration node)
    {
        List param = node.parameters();
        List<String> parameterStrList = new ArrayList<>();

        if(!param.isEmpty())
        {
            for(Object x : param)
            {
                SingleVariableDeclaration variableDeclaration = (SingleVariableDeclaration) x;
                String parameter = new String();
                if(!variableDeclaration.modifiers().isEmpty())
                {
                    parameter = variableDeclaration.modifiers().toString() + " " + variableDeclaration.getType();
                }
                else
                {
                    parameter = variableDeclaration.getType().toString();
                }
                parameterStrList.add(parameter);
            }
        }
        return parameterStrList;
    }

    public List<SpringAnnotation> findAnnotation(List<Modifier> node, String callName, ASTNode module)
    {
        List<SpringAnnotation> taglist = new ArrayList<>();
        if(!node.isEmpty())
        {
            for(Object o: node)
            {
                if(o instanceof MarkerAnnotation && ((MarkerAnnotation) o).isMarkerAnnotation())
                {
                    taglist.add(MarkerNode((MarkerAnnotation) o,callName));
                }
                else if(o instanceof NormalAnnotation && ((NormalAnnotation) o).isNormalAnnotation())
                {
                    taglist.add(NormalNode((NormalAnnotation) o, callName));
                }
                else if(o instanceof SingleMemberAnnotation && ((SingleMemberAnnotation) o).isSingleMemberAnnotation())
                {
                    taglist.add(SingleMemberNode((SingleMemberAnnotation) o, callName));
                }
            }
        }
        return taglist;
    }

    public SpringAnnotation MarkerNode(MarkerAnnotation o, String callName)
    {
        String type = ((MarkerAnnotation) o).getTypeName().toString();

        if(springAnnotationDependency.contains(type))
        {
//            taglist.add(new SpringAnnotation(type, callName));
//            System.out.println(type);
            return new SpringAnnotation(type, callName);
        }
        return null;
    }

    public SpringAnnotation SingleMemberNode(SingleMemberAnnotation o, String callName)
    {
        String value = ((SingleMemberAnnotation) o).getValue().toString();
        String type = ((SingleMemberAnnotation) o).getTypeName().toString();

        if(springAnnotationDependency.contains(type))
        {
            return new SpringAnnotation(value, callName, type);
//          System.out.println(type + " " + value);
        }
        return null;
    }

    public SpringAnnotation NormalNode(NormalAnnotation o, String callName)
    {
        String type = ((NormalAnnotation) o).getTypeName().getFullyQualifiedName();
        List<MemberValuePair> value = ((NormalAnnotation) o).values();

        if(springAnnotationDependency.contains(type) )
        {
            if(type.equals("Autowired"))
            {
                return new SpringAnnotation(type, callName, "class");
            }
            else
            {
                return new SpringAnnotation(type, callName);
            }
        }
        return null;
    }

}
