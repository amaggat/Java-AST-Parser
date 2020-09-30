package Project;


import Model.NamedEntity;
import Model.Node;

import java.util.Set;

public class ProjectProperties extends Node {

    private Set<PackageProperties> packagePropertiesSet;

    public ProjectProperties() {
    }

    public ProjectProperties(Set<PackageProperties> packagePropertiesSet) {
        this.packagePropertiesSet = packagePropertiesSet;
    }

    public Set<PackageProperties> getPackagePropertiesSet() {
        return packagePropertiesSet;
    }

    public void setPackagePropertiesSet(Set<PackageProperties> packagePropertiesSet) {
        this.packagePropertiesSet = packagePropertiesSet;
    }

    public void addPackage(PackageProperties IPackage)
    {
        packagePropertiesSet.add(IPackage);
    }

}
