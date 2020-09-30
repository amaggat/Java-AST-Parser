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
        // /home/amaggat/IdeaProjects/Spring-Petclinic/src/main

        List<ClassProperties> allClass= readfile.getAllFile(filePath).getClassPropertiesSet();
        List<Dependency> AllDep = new ArrayList<>();

        for(ClassProperties classProperties : allClass)
        {
            classProperties.print();
            classProperties.printAnnotations();
        }

        for(ClassProperties node: allClass)
        {
            AllDep.addAll(depSolve.returnDependency(node.getSpringAnnotations()));
        }

    }
}
