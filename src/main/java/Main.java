import Model.ClassProperty;
import Model.PackageProperty;
import ReadFile.ReadMultipleFile;

import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String args[]) throws IOException {
        Scanner inp = new Scanner(System.in);

        ReadMultipleFile readfile = new ReadMultipleFile();
        
        String filePath = new String(inp.nextLine());

        Set<ClassProperty> allClass= readfile.getAllFile(filePath).getClassPropertySet();
        
        for(ClassProperty pack: allClass)
        System.out.println(pack.getName());
    }
}
