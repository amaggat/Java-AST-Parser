package ReadFile;

import Model.*;
import com.google.common.io.Files;
import org.eclipse.jdt.core.dom.*;

import java.io.*;
import java.util.ArrayList;
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
                System.out.println("Field: " + field.getModifiers() + " " + field.getType() + " " + field.getName());

                buffer.addField(field);
                return false;
            }

            @Override
            public boolean visit(MethodDeclaration node)
            {
                if(!node.isConstructor())
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

                    Method method = new Method(node.getReturnType2().toString(), node.getName().toString(), node.modifiers(), parameterStrList);
                    System.out.println("Method: " + method.getModifiers() + " " + method.getType() + " " + method.getName() + " " + method.getParameters());
                    buffer.addMethod(method);
                }
                else
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

                    Constructor cons = new Constructor(node.getName().toString(), parameterStrList, node.modifiers());
                    System.out.println("Constructor: " + cons.getModifiers() + " " + cons.getName() + " " + cons.getParameters());
                    buffer.addCons(cons);
                }
                return false;
            }
        });


        return buffer;
    }

}
