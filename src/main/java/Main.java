import Package.ClassProperties;
import ReadFile.ReadMultipleFile;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String args[]) throws IOException {
        Scanner inp = new Scanner(System.in);

        ReadMultipleFile readfile = new ReadMultipleFile();
        
        String filePath = new String(inp.nextLine());
        // /home/amaggat/IdeaProjects/Spring-Petclinic/src/main

        List<ClassProperties> allClass= readfile.getAllFile(filePath).getClassPropertiesSet();

        for(ClassProperties classProperties : allClass)
        {
            classProperties.print();
        }


    }
}
