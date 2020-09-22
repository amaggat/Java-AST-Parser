package ReadFile;

import Package.ClassProperties;
import Project.PackageProperties;
import com.google.common.io.Files;

import java.io.*;
import java.util.List;

public class ReadMultipleFile {

    private List<File> allJavaFile;

    PackageProperties IPackage =new PackageProperties();
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

    public PackageProperties getAllFile (String directoryPath) throws FileNotFoundException, IOException
    {

        File folder = new File(directoryPath);
        File[] files = folder.listFiles();


        for(File file : files)
        {
            if(file.isFile())
            {
                if(Files.getFileExtension(file.getName()).equals("java"))
                {
//                    System.out.println(file.getName());
                    IPackage.addClassProperty(parser.visit(file.getPath(), file.getName()));
                }
            }
            else if (file.isDirectory())
            {
                getAllFile(file.getAbsolutePath());
            }
        }

        return IPackage;
    }

}
