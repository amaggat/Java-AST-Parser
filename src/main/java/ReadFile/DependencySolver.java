package ReadFile;

import Package.*;
import Project.Dependency;
import Class.Method;
import org.eclipse.jdt.core.dom.*;
import Class.Constructor;

import java.util.ArrayList;
import java.util.List;

public class DependencySolver {

    public List<Dependency> returnAutowiredDependency(List<SpringAnnotation> module)
    {
        List<Dependency> dep = new ArrayList<>();
        for(SpringAnnotation obj : module) {
            if(obj.getTypeName().equals("Autowired")) {
                for(String node : obj.getCalledClass()) {
//                    System.out.println(obj.getCallClass() + " " + node);
                    dep.add(new Dependency(obj.getCallClass(),node, "Autowired"));
                }
            }
        }
        return dep;
    }

    public List<Dependency> returnInheritance(ClassProperties module)
    {
        List<Dependency> dep = new ArrayList<>();

        if(!module.getInheritance().isEmpty()) {
            for(Object o : module.getInheritance()) {
                if(o instanceof SimpleType) {
//                    System.out.println(module.getName() + " " + o.toString());
                    dep.add(new Dependency(module.getName(), o.toString(), "Inheritance"));
                }
            }
        }

        return dep;
    }

    public List<Dependency> returnDependency(List<ClassProperties> module)
    {
        List<Dependency> Autowired = new ArrayList<>();
        List<Dependency> Inheritance = new ArrayList<>();

        for(ClassProperties node: module) {
            Autowired.addAll(returnAutowiredDependency(node.getSpringAnnotations()));
            Inheritance.addAll(returnInheritance(node));
        }

        List<Dependency> dep = new ArrayList<>();

        for(Dependency obj_a : Autowired) {
            for(Dependency obj_i: Inheritance) {
                if(obj_a.getCalledClass().equals(obj_i.getCalledClass())) {
                    dep.add(new Dependency(obj_i.getCallClass(), obj_a.getCallClass()));
                }
            }
        }

        return dep;
    }

    public List<Dependency> returnControllerDep(List<ClassProperties> module, ReturnStatement statement){
        List<Method> methods = new ArrayList<>();
        List<Dependency> dep = new ArrayList<>();
        for(ClassProperties node : module){
            List<Method> s = node.getMethodName();
//            System.out.println("------Test-------");
//            s.forEach(t -> {
//                methods.add(t);
//            });
//            s.forEach(m -> {
//                System.out.println("Class: " + m.getClassName() + " ---> method: " + m.getName() + "()");
//            });
//            System.out.println("------End Test-------\n\n\n");
        }
        for(Method method : methods){
            //code here
        }
        return dep;
    }

}

