package Model;

import java.util.Set;

public class ClassMethod extends NamedEntity{

    private Set<String> input;

    public Set<String> getInput() {
        return input;
    }

    public void setInput(Set<String> input) {
        this.input = input;
    }

    @Override
    public String toString() {
        String buffer = new String();

        for(String i : input)
        {
            buffer = i + ", ";
        }

        return super.getStatus() + " " + super.getName() + "(" + buffer + ")";
    }
}
