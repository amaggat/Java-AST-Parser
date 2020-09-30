import Package.*;
import Project.Dependency;
import ReadFile.DependencySolver;
import ReadFile.ReadMultipleFile;

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
        String filePath = new String(inp.nextLine());
        // Bản dài dòng: /home/amaggat/IdeaProjects/Spring-Petclinic/src/main
        // Bản tối ưu  : /home/amaggat/IdeaProjects/PetClinic-SpringBoot/src/main/java

        List<ClassProperties> allClass= readfile.getAllFile(filePath).getClassPropertiesSet();
        List<Dependency> AllDep = new ArrayList<>();
        List<Dependency> AutowiredDep = new ArrayList<>();
        List<Dependency> InheritanceDep = new ArrayList<>();

//        for(ClassProperties classProperties : allClass)
//        {
//            classProperties.print();
//            classProperties.printAnnotations();
//        }

        for(ClassProperties node: allClass)
        {
            AutowiredDep.addAll(depSolve.returnAutowiredDependency(node.getSpringAnnotations()));
            InheritanceDep.addAll(depSolve.returnInheritance(node));
        }

        AllDep.addAll(depSolve.returnDependency(AutowiredDep, InheritanceDep));

        for(Dependency obj : AllDep)
        {
            obj.print();
        }
    }
}
