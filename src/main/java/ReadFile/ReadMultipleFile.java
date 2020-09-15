package ReadFile;

import Model.*;
import com.google.common.io.Files;
import org.eclipse.jdt.core.dom.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadMultipleFile {

    private List<File> allJavaFile;

    packageProperties IPackage =new packageProperties();
    JavaFileParser parser = new JavaFileParser();

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

    public packageProperties getAllFile (String directoryPath) throws FileNotFoundException, IOException
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
                    IPackage.addClassProperty(parser.visit(file.getPath()));
                    System.out.println();
                }
            }
            else if (file.isDirectory())
            {
                getAllFile(file.getAbsolutePath());
                System.out.println();
            }
        }

        return IPackage;
    }

}
