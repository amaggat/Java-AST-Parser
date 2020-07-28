package Model;

import java.util.HashSet;
import java.util.Set;

public class packageProperties extends NamedEntity{
    private Set<classProperties> classPropertiesSet = new HashSet<>();

    public packageProperties()
    {

    }

    public packageProperties(Set<classProperties> classPropertiesSet) {
        this.classPropertiesSet = classPropertiesSet;
    }

    public Set<classProperties> getClassPropertiesSet() {
        return classPropertiesSet;
    }

    public void setClassPropertiesSet(Set<classProperties> classPropertiesSet) {
        this.classPropertiesSet = classPropertiesSet;
    }

    public void addClassProperty(classProperties classProperties)
    {
        classPropertiesSet.add(classProperties);
    }
}
