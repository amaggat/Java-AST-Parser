package Model;

import java.util.List;
import java.util.Set;

public class ClassMethod extends NamedEntity{

    public ClassMethod(String type, String name, List<String> status, List<String> input)
    {
        super.setType(type);
        super.setName(name);
        super.setStatus(status);
        this.input = input;
    }

    private List<String> input;

    public List<String> getInput() {
        return input;
    }

    public void setInput(List<String> input) {
        this.input = input;
    }

}
