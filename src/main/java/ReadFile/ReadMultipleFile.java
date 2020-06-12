package ReadFile;

import Model.*;
import com.google.common.io.Files;
import org.eclipse.jdt.core.dom.*;

import java.io.*;
import java.util.List;

public class ReadMultipleFile {

    private List<File> allJavaFile;

    PackageProperty IPackage =new PackageProperty();

    public ReadMultipleFile() {
    }

    public String getFileContent (String filepath) throws FileNotFoundException, IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(filepath));
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();
        while (line != null)
        {
            sb.append(line);
            sb.append(System.lineSeparator());
            line = br.readLine();
        }
        return sb.toString();
    }

    public PackageProperty getAllFile (String directoryPath) throws FileNotFoundException, IOException
    {

        File folder = new File(directoryPath);
        File[] files = folder.listFiles();


        for(File file : files)
        {



            if(file.isFile())
            {
                if(Files.getFileExtension(file.getName()).equals("java"))
                {

                    System.out.println(file.getName());
                    IPackage.addClassProperty(visit(file.getPath()));
                }
            }
            else if (file.isDirectory())
            {
                System.out.println(file.getPath());
                getAllFile(file.getAbsolutePath());
                System.out.println();
                System.out.println();

            }
        }

        return IPackage;
    }

    public ClassProperty visit(String filePath) throws FileNotFoundException, IOException
    {
        final ClassProperty buffer = new ClassProperty();

        ASTParser parser = ASTParser.newParser(AST.JLS13);
        char[] fileContent = getFileContent(filePath).toCharArray();
        parser.setSource(fileContent);

        CompilationUnit cu = (CompilationUnit) parser.createAST(null);

        cu.accept(new ASTVisitor() {

            @Override
            public boolean visit(FieldDeclaration node) {
                ClassVariable var = new ClassVariable(node.getType().toString(), node.fragments().toString() ,node.modifiers());
                System.out.println(var);
                buffer.addVar(var);
                return false;
            }

            @Override
            public boolean visit(MethodDeclaration node)
            {
                if(!node.isConstructor())
                {
                    ClassMethod method = new ClassMethod(node.getReturnType2().toString(), node.getName().toString(), node.modifiers(),node.parameters());
                    System.out.println(method.getInput());
                    System.out.println();
                    buffer.addMethod(method);
                }
                else
                {
                    ClassConstructor cons = new ClassConstructor(node.getName().toString(), node.parameters(), node.modifiers());
                    System.out.println(cons.getName());
                    System.out.println();
                    buffer.addCons(cons);
                }
                return false;
            }
        });


        return buffer;
    }

}
