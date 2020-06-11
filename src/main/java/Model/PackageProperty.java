package Model;

import java.util.Set;

public class PackageProperty extends NamedEntity{
    private Set<ClassProperty> classPropertySet;

    public PackageProperty()
    {

    }

    public PackageProperty(Set<ClassProperty> classPropertySet) {
        this.classPropertySet = classPropertySet;
    }

    public Set<ClassProperty> getClassPropertySet() {
        return classPropertySet;
    }

    public void setClassPropertySet(Set<ClassProperty> classPropertySet) {
        this.classPropertySet = classPropertySet;
    }

    public void addClassProperty(ClassProperty classProperty)
    {
        classPropertySet.add(classProperty);
    }
}
