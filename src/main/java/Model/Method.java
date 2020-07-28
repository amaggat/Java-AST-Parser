package Model;

import java.util.List;

public class Method extends NamedEntity{

    public Method(String type, String name, List<String> status, List<String> parameters)
    {
        super.setType(type);
        super.setName(name);
        super.setStatus(status);
        this.parameters = parameters;
    }

    private List<String> parameters;

    public List<String> getParameters() {
        return parameters;
    }

    public void setParameters(List<String> parameters) {
        this.parameters = parameters;
    }

}
