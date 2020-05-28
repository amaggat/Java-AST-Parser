package Model;


import java.util.Set;

public class ProjectProperty extends NamedEntity{

    private Set<PackageProperty> packagePropertySet;

    public ProjectProperty(Set<PackageProperty> packagePropertySet) {
        this.packagePropertySet = packagePropertySet;
    }

    public Set<PackageProperty> getPackagePropertySet() {
        return packagePropertySet;
    }

    public void setPackagePropertySet(Set<PackageProperty> packagePropertySet) {
        this.packagePropertySet = packagePropertySet;
    }

}
