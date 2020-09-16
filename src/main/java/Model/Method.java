package Model;

import org.eclipse.jdt.core.dom.Modifier;
import java.util.List;

public class Method extends NamedEntity{

    public Method(String type, String name, List<Modifier> status, List<String> parameters)
    {
        super.setType(type);
        super.setName(name);
        super.setModifiers(status);
        this.parameters = parameters;
    }

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

}
