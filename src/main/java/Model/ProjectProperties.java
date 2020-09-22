package Model;


import java.util.Set;

public class ProjectProperties extends NamedEntity{

    private Set<PackageProperties> packagePropertiesSet;

    public ProjectProperties(Set<PackageProperties> packagePropertiesSet) {
        this.packagePropertiesSet = packagePropertiesSet;
    }

    public Set<PackageProperties> getPackagePropertiesSet() {
        return packagePropertiesSet;
    }

    public void setPackagePropertiesSet(Set<PackageProperties> packagePropertiesSet) {
        this.packagePropertiesSet = packagePropertiesSet;
    }

}
