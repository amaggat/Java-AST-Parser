package Model;

import org.eclipse.jdt.core.dom.Modifier;
import java.util.List;

public class Field extends NamedEntity{

    public Field(String type, String name, List<Modifier> status)
    {
        super.setName(name);
        super.setType(type);
        super.setModifiers(status);
    }

    @Override
    public String toString() {

        return super.getModifiers() + " " + super.getName() + ": " + super.getType();
    }
}
