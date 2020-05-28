package JavaAST;

import Model.PackageProperty;
import ReadFile.ReadMultipleFile;
import org.eclipse.jdt.core.dom.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.*;

public class ParseCode {

    PackageProperty pack = new PackageProperty();
    ReadMultipleFile returnFile;

    public void visit(String filePath) throws FileNotFoundException, IOException
    {
        ASTParser parser = ASTParser.newParser(AST.JLS13);
        char[] fileContent = returnFile.getFileContent(filePath).toCharArray();
        parser.setSource(fileContent);


    }

}
