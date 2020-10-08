package Class;

import Model.NamedEntity;
import org.eclipse.jdt.core.dom.*;

import java.util.List;

public class Method extends NamedEntity {

    public List<ReturnStatement> getReturnParam() {
        return returnParam;
    }

    public void setReturnParam(List<ReturnStatement> returnParam) {
        this.returnParam = returnParam;
    }

    public Method(String type, String name, List<Modifier> status, List<String> parameters, List<ReturnStatement> returnParam,
                  String className, List<StringLiteral> stringLiteral)
    {
        super.setType(type);
        super.setName(name);
        super.setModifiers(status);
        this.parameters = parameters;
        this.returnParam = returnParam;
        this.className = className;
        this.stringLiteral = stringLiteral;
    }

    private String className;

    private List<StringLiteral> stringLiteral;

    public List<StringLiteral> getStringLiteral() {
        return stringLiteral;
    }

    public void setStringLiteral(List<StringLiteral> stringLiteral) {
        this.stringLiteral = stringLiteral;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    private List<ReturnStatement> returnParam;

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
        System.out.println("Class: " + className);
        System.out.println("\tMethod: " + super.getType() + " " + super.getName() + " " + this.getParameters() + " " + super.getModifiers());
        if(returnParam!=null){
//            System.out.println("\t\t---Return Statement: " + this.getReturnParam());
            for(Statement node : returnParam){
                System.out.println("\t\t---Return Statement: " + node + " ---> Call Constructor: ");
            }
            for(StringLiteral node : stringLiteral){
                System.out.println("\t\t---String Literal: " + node + " ---> Call Constructor: ");
            }
        }
    }

}
