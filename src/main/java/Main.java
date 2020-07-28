import Model.classProperties;
import ReadFile.ReadMultipleFile;

import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String args[]) throws IOException {
        Scanner inp = new Scanner(System.in);

        ReadMultipleFile readfile = new ReadMultipleFile();
        
        String filePath = new String(inp.nextLine());

        Set<classProperties> allClass= readfile.getAllFile(filePath).getClassPropertiesSet();
        
        for(classProperties pack: allClass)
        System.out.println(pack.getName());
    }
}
