import Model.IPathParser;
import Model.Node;
import Model.XmlFileParser;
import Package.*;
import Project.Dependency;
import Class.Method;
import ReadFile.DependencySolver;
import ReadFile.ReadMultipleFile;
import org.eclipse.jdt.core.dom.ReturnStatement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String args[]) throws IOException {
        Scanner inp = new Scanner(System.in);

        ReadMultipleFile readfile = new ReadMultipleFile();
        DependencySolver depSolve = new DependencySolver();

        SpringAnnotation springDep = new SpringAnnotation();
//        String filePath = new String(inp.nextLine());
        String filePath = new String("/Users/apple/Desktop/code/spring-framework-petclinic");
        // Bản dài dòng: /home/amaggat/IdeaProjects/Spring-Petclinic/src/main
        // Bản tối ưu  : /home/amaggat/IdeaProjects/PetClinic-SpringBoot/src/main/java

        List<ClassProperties> allClass = readfile.getAllFile(filePath).getClassPropertiesSet();
        List<Dependency> AllDep = new ArrayList<>();
        List<Dependency> ControllerDep = new ArrayList<>();

        AllDep.addAll(depSolve.returnDependency(allClass));
//        ControllerDep.addAll(depSolve.returnControllerDep(allClass));

        for(Dependency obj : AllDep)
        {
            obj.print();
        }

        List<Method> methods = new ArrayList<Method>();

        for(ClassProperties classProp : allClass){
            List<Method> tmp = classProp.getMethodName();
            tmp.forEach(m -> {
                methods.add(m);
            });
            System.out.println();
        }

        List<ReturnStatement> statements = new ArrayList<>();
        methods.forEach(method -> {
            method.getReturnParam().forEach(returnStatement -> {
                statements.add(returnStatement);
            });
        });

        System.out.println("----- Controller Dependency -----");
        statements.forEach(returnStatement -> {
            ControllerDep.addAll(depSolve.returnControllerDep(allClass, returnStatement));
        });
        System.out.println("----- End Controller Dependency -----");


        ControllerDep.forEach(dep -> {
            dep.print();
        });

        System.out.println("\n\n\n --- End Dependencies ---");

        for(ClassProperties classProperties : allClass)
        {
            classProperties.print();
            classProperties.printAnnotations();
            System.out.println("\n");
        }

        System.out.println("\n\n\n\n\n\n\n\n-----XML File Parser-----");
        IPathParser parser = new XmlFileParser();
        Node parsedNode = null;
        parsedNode = parser.parse("/Users/apple/Desktop/code/spring-framework-petclinic/src/main/resources/spring/mvc-view-config.xml");
        System.out.println("-----End XML File Parser-----");
    }
}
