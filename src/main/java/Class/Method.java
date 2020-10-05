package Class;

import Model.NamedEntity;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.SimpleName;

import java.util.List;

public class Method extends NamedEntity {

    public List<String> getReturnParam() {
        return returnParam;
    }

    public void setReturnParam(List<String> returnParam) {
        this.returnParam = returnParam;
    }

    public Method(String type, String name, List<Modifier> status, List<String> parameters, List<String> returnParam)
    {
        super.setType(type);
        super.setName(name);
        super.setModifiers(status);
        this.parameters = parameters;
        this.returnParam = returnParam;
    }

//    public Method(List<String> returnParam){
//        this.returnParam = returnParam;
//    }

    private List<String> returnParam;

    private List<String> parameters;

    public List<String> getParameters() {
        return parameters;
    }

    public void addParameters(String parameter)
    {
        parameters.add(parameter);
    }

    public void setParameters(List<String> parameters) {
        this.parameters = parameters;
    }

    public void print()
    {
        System.out.println("\tMethod: " + super.getType() + " " + super.getName() + " " + this.getParameters() + " " + super.getModifiers());
        if(returnParam!=null){
            System.out.println("\t\t---Return Statement: " + this.getReturnParam());
        }
    }

}
