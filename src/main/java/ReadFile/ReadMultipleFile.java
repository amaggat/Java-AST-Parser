package ReadFile;

import Model.*;
import com.google.common.io.Files;
import org.eclipse.jdt.core.dom.*;

import java.io.*;
import java.util.List;

public class ReadMultipleFile {

    private List<File> allJavaFile;

    packageProperties IPackage =new packageProperties();

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

    public packageProperties getAllFile (String directoryPath) throws FileNotFoundException, IOException
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
                    System.out.println();
                }
            }
            else if (file.isDirectory())
            {
                System.out.println(file.getPath());
                getAllFile(file.getAbsolutePath());
                System.out.println();


            }
        }

        return IPackage;
    }

    public classProperties visit(String filePath) throws FileNotFoundException, IOException
    {
        final classProperties buffer = new classProperties();

        ASTParser parser = ASTParser.newParser(AST.JLS13);
        char[] fileContent = getFileContent(filePath).toCharArray();
        parser.setSource(fileContent);

        CompilationUnit cu = (CompilationUnit) parser.createAST(null);

        cu.accept(new ASTVisitor() {

            @Override
            public boolean visit(FieldDeclaration node) {
                VariableDeclaration z = (VariableDeclaration) node.fragments().get(0);
                Field field = new Field(node.getType().toString(), z.getName().toString() ,node.modifiers());
                System.out.println(field.getStatus() + " " + field.getType() + " " + field.getName());

                buffer.addField(field);
                return false;
            }

            @Override
            public boolean visit(MethodDeclaration node)
            {
                if(!node.isConstructor())
                {
                    Method method = new Method(node.getReturnType2().toString(), node.getName().toString(), node.modifiers(),node.parameters());
                    System.out.println(method.getStatus() + " " + method.getType() + " " + method.getName() + " " + method.getParameters());
                    buffer.addMethod(method);
                }
                else
                {
                    Constructor cons = new Constructor(node.getName().toString(), node.parameters(), node.modifiers());
//                    System.out.println(cons.getName());
                    buffer.addCons(cons);
                }
                return false;
            }
        });


        return buffer;
    }

}
