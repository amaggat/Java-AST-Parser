package Model;

import java.util.List;

public abstract class NamedEntity {

    private String type = new String();
    private String name = new String();
    private int id;
    private List<String> Modifiers;
    private String Annotation = new String();

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

    public List<String> getModifiers() {
        return Modifiers;
    }

    public void setModifiers(List<String> modifiers) {
        this.Modifiers = modifiers;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAnnotation() {
        return Annotation;
    }

    public void setAnnotation(String annotation) {
        Annotation = annotation;
    }
}
