package Model;

import java.util.List;

public class Constructor extends NamedEntity{

    public Constructor(String name, List<String> parameters, List<String> status) {
        super.setName(name);
        super.setModifiers(status);
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
