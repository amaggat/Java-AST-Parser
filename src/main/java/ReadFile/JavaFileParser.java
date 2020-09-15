package ReadFile;

import Model.Constructor;
import Model.Field;
import Model.Method;
import Model.classProperties;
import org.eclipse.jdt.core.dom.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class JavaFileParser {


    public classProperties visit(String filePath) throws FileNotFoundException, IOException
    {
        final classProperties buffer = new classProperties();
        ReadMultipleFile getContent = new ReadMultipleFile();

        ASTParser parser = ASTParser.newParser(AST.JLS13);
        char[] fileContent = getContent.getFileContent(filePath).toCharArray();
        parser.setSource(fileContent);

        CompilationUnit cu = (CompilationUnit) parser.createAST(null);

        cu.accept(new ASTVisitor() {

            @Override
            public boolean visit(FieldDeclaration node) {
//                VariableDeclaration z = (VariableDeclaration) node.fragments().get(0);
//                Field field = new Field(node.getType().toString(), z.getName().toString() ,node.modifiers());
//                System.out.println("Field: " + field.getModifiers() + " " + field.getType() + " " + field.getName());

                buffer.addField(JavaFieldParser(node));
                return false;
            }

            @Override
            public boolean visit(MethodDeclaration node)
            {
                if(!node.isConstructor())
                {
//                    List param = node.parameters();
//                    List<String> parameterStrList = new ArrayList<>();
//
//                    if(!param.isEmpty())
//                    {
//                        for(Object x : param)
//                        {
//                            SingleVariableDeclaration variableDeclaration = (SingleVariableDeclaration) x;
//                            String parameter = new String();
//                            if(!variableDeclaration.modifiers().isEmpty())
//                            {
//                                parameter = variableDeclaration.modifiers().toString() + " " + variableDeclaration.getType();
//                            }
//                            else
//                            {
//                                parameter = variableDeclaration.getType().toString();
//                            }
//                            parameterStrList.add(parameter);
//                        }
//                    }
//
//                    Method method = new Method(node.getReturnType2().toString(), node.getName().toString(), node.modifiers(), parameterStrList);
//                    System.out.println("Method: " + method.getModifiers() + " " + method.getType() + " " + method.getName() + " " + method.getParameters());
                    buffer.addMethod(JavaMethodParser(node));
                }
                else
                {
//                    List param = node.parameters();
//                    List<String> parameterStrList = new ArrayList<>();
//
//                    if(!param.isEmpty())
//                    {
//                        for(Object x : param)
//                        {
//                            SingleVariableDeclaration variableDeclaration = (SingleVariableDeclaration) x;
//                            String parameter = new String();
//                            if(!variableDeclaration.modifiers().isEmpty())
//                            {
//                                parameter = variableDeclaration.modifiers().toString() + " " + variableDeclaration.getType();
//                            }
//                            else
//                            {
//                                parameter = variableDeclaration.getType().toString();
//                            }
//                            parameterStrList.add(parameter);
//                        }
//                    }
//
//                    Constructor cons = new Constructor(node.getName().toString(), parameterStrList, node.modifiers());
//                    System.out.println("Constructor: " + cons.getModifiers() + " " + cons.getName() + " " + cons.getParameters());
                    buffer.addCons(JavaConstructorParser(node));
                }
                return false;
            }
        });

        return buffer;
    }

    Method JavaMethodParser (MethodDeclaration node)
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
        return method;
    }

    Constructor JavaConstructorParser(MethodDeclaration node)
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
        return cons;
    }

    Field JavaFieldParser(FieldDeclaration node)
    {
        VariableDeclaration z = (VariableDeclaration) node.fragments().get(0);
        Field field = new Field(node.getType().toString(), z.getName().toString() ,node.modifiers());
        System.out.println("Field: " + field.getModifiers() + " " + field.getType() + " " + field.getName());
        return field;
    }
}
