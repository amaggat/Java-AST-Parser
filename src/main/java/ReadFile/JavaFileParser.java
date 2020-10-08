package ReadFile;

import org.eclipse.jdt.core.dom.*;

import Package.*;
import Class.*;
import Class.Constructor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;


public class JavaFileParser {

    final static ArrayList<String> springAnnotationDependency = new ArrayList<String>( Arrays.asList("GetMapping", "PostMapping", "Controller", "Service", "Repository", "RequestMapping", "Autowired") );

    public static ClassProperties visit(String filePath, final String fileName) throws FileNotFoundException, IOException
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
                buffer.setName(node.getName().toString());
                annotationDependency.addAll(JavaSpringDependency(node.modifiers(), fileName, node));
                buffer.setModifiers(node.modifiers());
                buffer.setInheritance(node.superInterfaceTypes());
                if(node.isInterface()) System.out.println(node.getName());
                return true;
            }

            @Override
            public boolean visit(FieldDeclaration node) {
                buffer.addField(JavaFieldParser(node, fileName));
                annotationDependency.addAll(JavaSpringDependency(node.modifiers(), fileName, node));
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

        buffer.addSpringAnnotation(annotationDependency);
        return buffer;
    }

    public static Method JavaMethodParser (MethodDeclaration node, String fileName)
    {
        List<String> parameterStrList = JavaParameter(node);
        String returnType = node.getReturnType2().toString();
        String methodName = node.getName().toString();
        List<ReturnStatement> returnStatements =  ReturnStatement(node);
        List<StringLiteral> stringLiterals = StringLiteral(node);
        String className = fileName;
        Method method = new Method(returnType, methodName, node.modifiers(), parameterStrList, returnStatements, className, stringLiterals);
//      System.out.println("Method: " + method.getModifiers() + " " + method.getType() + " " + method.getName() + " " + method.getParameters());
        return method;
    }

    public static boolean isCalledByController(ReturnStatement returnStatement, List<Constructor> constructors){
        System.out.println(returnStatement.getExpression());
        return true;
    }

    public static Constructor JavaConstructorParser(MethodDeclaration node, String fileName)
    {
        List<String> parameterStrList = JavaParameter(node);
        String constructorName = node.getName().toString();

        Constructor cons = new Constructor(constructorName, parameterStrList, node.modifiers());

//      System.out.println("Constructor: " + cons.getModifiers() + " " + cons.getName() + " " + cons.getParameters());
        return cons;
    }

    public static Field JavaFieldParser(FieldDeclaration node, String fileName)
    {
        VariableDeclaration z = (VariableDeclaration) node.fragments().get(0);
        String fieldName = z.getName().toString();
        String fieldType = node.getType().toString();

        Field field = new Field(fieldType, fieldName ,node.modifiers());

//      System.out.println("Field: " + field.getModifiers() + " " + field.getType() + " " + field.getName());
        return field;
    }

    public static List<SpringAnnotation> JavaSpringDependency(List<Modifier> node, String callName, ASTNode module)
    {
        List<SpringAnnotation> dep = new ArrayList<>();
        dep.addAll(findAnnotation(node, callName, module));
        return dep;
    }

    public static List<String> JavaParameter(MethodDeclaration node)
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

    public static List<ReturnStatement> ReturnStatement(MethodDeclaration node){
        final List<ReturnStatement> returnParam = new ArrayList<>();
        final Collection<Statement> returns = new ArrayList<>();
        ASTVisitor finder = new ASTVisitor() {
            public boolean visit(ReturnStatement node) {
                ReturnStatement result = node;
                returnParam.add(node);
                return returns.add(node);
            }
        };
        node.accept(finder);
        return returnParam;
    }

    public static List<StringLiteral> StringLiteral(MethodDeclaration node){
        final List<StringLiteral> returnParam = new ArrayList<>();
        final Collection<StringLiteral> returns = new ArrayList<>();
        ASTVisitor finder = new ASTVisitor() {
            public boolean visit(StringLiteral node) {
                StringLiteral result = node;
                returnParam.add(node);
                return returns.add(node);
            }
        };
        node.accept(finder);
        return returnParam;
    }

    public static List<SpringAnnotation> findAnnotation(List<Modifier> node, String callName, ASTNode module)
    {
        List<SpringAnnotation> taglist = new ArrayList<>();
        if(!node.isEmpty())
        {
            for(Object o: node)
            {
                if(o instanceof MarkerAnnotation && ((MarkerAnnotation) o).isMarkerAnnotation())
                {
                    String type = ((MarkerAnnotation) o).getTypeName().toString();

                    if(springAnnotationDependency.contains(type))
                    {
                        if(type.equals("Autowired"))
                        {
                            if(module instanceof MethodDeclaration)

                                taglist.add(new SpringAnnotation(type, callName, JavaParameter((MethodDeclaration) module), "Autowired"));

                            if(module instanceof FieldDeclaration)
                            {
                                List<String> parameters = new ArrayList<>();
                                parameters.add(((FieldDeclaration) module).getType().toString());
                                taglist.add(new SpringAnnotation(type, callName, parameters, "Autowired"));
                            }
                            if(module instanceof TypeDeclaration)
                                taglist.add(new SpringAnnotation(type, callName));
                        }
                        else
                        {
                            taglist.add(new SpringAnnotation(type, callName));
                        }
                    }

//                    if(springAnnotationDependency.contains(type))
//                    {
//                        taglist.add(new SpringAnnotation(type, callName));
//                    }
                }
                else if(o instanceof NormalAnnotation && ((NormalAnnotation) o).isNormalAnnotation())
                {
                    String type = ((NormalAnnotation) o).getTypeName().getFullyQualifiedName();
                    List<MemberValuePair> value = ((NormalAnnotation) o).values();

                    if(springAnnotationDependency.contains(type) )
                    {
                        taglist.add(new SpringAnnotation(type, callName, value));
                    }
                }
                else if(o instanceof SingleMemberAnnotation && ((SingleMemberAnnotation) o).isSingleMemberAnnotation())
                {
                    String value = ((SingleMemberAnnotation) o).getValue().toString();
                    String type = ((SingleMemberAnnotation) o).getTypeName().toString();

                    if(springAnnotationDependency.contains(type))
                    {
                        taglist.add(new SpringAnnotation(value, callName, type));
                    }
                }
            }
        }
        return taglist;
    }

}
