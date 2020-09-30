package ReadFile;

import Package.*;
import Project.Dependency;

import java.util.ArrayList;
import java.util.List;

public class DependencySolver {

    public List<Dependency> returnDependency(List<SpringAnnotation> module)
    {
        List<Dependency> dep = new ArrayList<>();

        for(SpringAnnotation obj : module)
        {
            if(obj.getTypeName().equals("Autowired"))
            {
                for(String node : obj.getCalledClass())
                {
                    System.out.println(obj.getCallClass() + " " + node);
                    dep.add(new Dependency(obj.getCallClass(),node));
                }
            }
        }

        return dep;
    }
}

