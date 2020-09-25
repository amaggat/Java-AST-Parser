package Model;


import Package.*;
import org.eclipse.jdt.core.dom.Modifier;

import java.util.ArrayList;
import java.util.List;

public abstract class NamedEntity extends Node{

    private String type = new String();
    private List<Modifier> Modifiers = new ArrayList<>();
    private List<SpringAnnotation> dependencies = new ArrayList<>();

    public List<Modifier> getModifiers() {
        return Modifiers;
    }

    public void setModifiers(List<Modifier> modifiers) {
        this.Modifiers = modifiers;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<SpringAnnotation> getDependencies() {
        return dependencies;
    }

    public void setDependencies(List<SpringAnnotation> dependencies) {
        this.dependencies = dependencies;
    }
}
