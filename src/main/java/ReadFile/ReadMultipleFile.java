package ReadFile;

import JavaAST.ParseCode;
import Model.PackageProperty;
import com.google.common.io.Files;

import java.io.*;
import java.util.List;

public class ReadMultipleFile {

    private List<File> allJavaFile;
    ParseCode parser;

    PackageProperty IPackage;

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

    public PackageProperty getAllFile (String directoryPath) throws FileNotFoundException, IOException
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
//                    IPackage.addClassProperty(parNameser.visit(file.getName()));
                }
            }
            else if (file.isDirectory())
            {
                System.out.println(file.getPath());
                getAllFile(file.getAbsolutePath());
                System.out.println();
                System.out.println();

            }
        }

        return IPackage;
    }

}
