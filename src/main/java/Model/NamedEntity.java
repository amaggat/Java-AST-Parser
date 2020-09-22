package Model;


import Package.*;
import org.eclipse.jdt.core.dom.Modifier;

import java.util.ArrayList;
import java.util.List;

public abstract class NamedEntity {

    private String type = new String();
    private String name = new String();
    private int id;
    private List<Modifier> Modifiers = new ArrayList<>();
    private List<Dependency> dependencies = new ArrayList<>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public List<Dependency> getDependencies() {
        return dependencies;
    }

    public void setDependencies(List<Dependency> dependencies) {
        this.dependencies = dependencies;
    }
}
