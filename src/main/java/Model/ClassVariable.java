package Model;

import java.util.List;

public class ClassVariable extends NamedEntity{

    public ClassVariable(String type, String name, List<String> status)
    {
        super.setName(name);
        super.setType(type);
        super.setStatus(status);
    }

    @Override
    public String toString() {

        return super.getStatus() + " " + super.getName() + ": " + super.getType();
    }
}
