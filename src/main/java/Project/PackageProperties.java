package Project;

import Model.NamedEntity;
import Package.ClassProperties;

import java.util.ArrayList;
import java.util.List;

public class PackageProperties extends NamedEntity {
    private List<ClassProperties> classPropertiesSet = new ArrayList<>();

    public PackageProperties()
    {

    }

    public PackageProperties(List<ClassProperties> classPropertiesSet) {
        this.classPropertiesSet = classPropertiesSet;
    }

    public List<ClassProperties> getClassPropertiesSet() {
        return classPropertiesSet;
    }

    public void setClassPropertiesSet(List<ClassProperties> classPropertiesSet) {
        this.classPropertiesSet = classPropertiesSet;
    }

    public void addClassProperty(ClassProperties classProperties)
    {
        classPropertiesSet.add(classProperties);
    }
}
