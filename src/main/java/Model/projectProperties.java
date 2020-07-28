package Model;


import java.util.Set;

public class projectProperties extends NamedEntity{

    private Set<packageProperties> packagePropertiesSet;

    public projectProperties(Set<packageProperties> packagePropertiesSet) {
        this.packagePropertiesSet = packagePropertiesSet;
    }

    public Set<packageProperties> getPackagePropertiesSet() {
        return packagePropertiesSet;
    }

    public void setPackagePropertiesSet(Set<packageProperties> packagePropertiesSet) {
        this.packagePropertiesSet = packagePropertiesSet;
    }

}
