package ReadFile;

import Model.*;
import org.eclipse.jdt.core.dom.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class JavaFileParser {


    public classProperties visit(String filePath, final String fileName) throws FileNotFoundException, IOException
    {
        final classProperties buffer = new classProperties();
        final Set<Dependency> dependency = new HashSet<>();
        ReadMultipleFile getContent = new ReadMultipleFile();

        ASTParser parser = ASTParser.newParser(AST.JLS8);
        char[] fileContent = getContent.getFileContent(filePath).toCharArray();
        parser.setSource(fileContent);

        CompilationUnit cu = (CompilationUnit) parser.createAST(null);

        cu.accept(new ASTVisitor() {

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
                }
                else
                {
                    buffer.addCons(JavaConstructorParser(node, fileName));
                }
                return false;
            }
        });

        return buffer;
    }

    Method JavaMethodParser (MethodDeclaration node, String fileName)
    {
        List<String> parameterStrList = JavaParameter(node);
        String returnType = node.getReturnType2().toString();
        String methodName = node.getName().toString();

        Method method = new Method(returnType, methodName, node.modifiers(), parameterStrList);
        JavaSpringDependency((List<Modifier>)node.modifiers(), fileName);
//        System.out.println("Method: " + method.getModifiers() + " " + method.getType() + " " + method.getName() + " " + method.getParameters());
        return method;
    }

    Constructor JavaConstructorParser(MethodDeclaration node, String fileName)
    {
        List<String> parameterStrList = JavaParameter(node);
        String constructorName = node.getName().toString();

        Constructor cons = new Constructor(constructorName, parameterStrList, node.modifiers());
        JavaSpringDependency((List<Modifier>)node.modifiers(), fileName);
//        System.out.println("Constructor: " + cons.getModifiers() + " " + cons.getName() + " " + cons.getParameters());
        return cons;
    }

    Field JavaFieldParser(FieldDeclaration node, String fileName)
    {
        VariableDeclaration z = (VariableDeclaration) node.fragments().get(0);
        String fieldName = z.getName().toString();
        String fieldType = node.getType().toString();

        Field field = new Field(fieldType, fieldName ,node.modifiers());
       JavaSpringDependency((List<Modifier>)node.modifiers(), fileName);
//        System.out.println("Field: " + field.getModifiers() + " " + field.getType() + " " + field.getName());
        return field;
    }

    Set<Dependency> JavaSpringDependency(List<Modifier> node, String callName)
    {

        Set<Dependency> dep = new HashSet<>();
        List<Modifier> mod = node;
        if(!mod.isEmpty())
        {
            for(Object o: mod)
            {
                if(o instanceof MarkerAnnotation)
                {
                    String type = ((MarkerAnnotation) o).getTypeName().toString();
                    dep.add(new Dependency(type, callName));
                    System.out.println(type);
                }
                else if(o instanceof NormalAnnotation)
                {
                    String type = (((NormalAnnotation) o).values().toString());
                    dep.add(new Dependency(type, callName));
                    System.out.println(type);
                }
                else if(o instanceof SingleMemberAnnotation)
                {
                    String type = ((SingleMemberAnnotation) o).getValue().toString();
                    dep.add(new Dependency(type, callName));
                    System.out.println(type);
                }
            }
        }
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
}
