package Project;

import Model.NamedEntity;
import Model.Node;
import Package.ClassProperties;

import java.util.ArrayList;
import java.util.List;

public class PackageProperties extends Node {

    private List<PackageProperties> packagePropertiesSet = new ArrayList<>();
    private List<ClassProperties> classPropertiesSet = new ArrayList<>();

    public PackageProperties()
    {

    }

    public PackageProperties(List<ClassProperties> classPropertiesSet) {
        this.classPropertiesSet = classPropertiesSet;
    }

    public List<PackageProperties> getPackagePropertiesSet() {
        return packagePropertiesSet;
    }

    public void setPackagePropertiesSet(List<PackageProperties> packagePropertiesSet) {
        this.packagePropertiesSet = packagePropertiesSet;
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
