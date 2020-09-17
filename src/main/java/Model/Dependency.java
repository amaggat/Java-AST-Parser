package Model;

public class Dependency {
    String value = new String();
    String callClass = new String();

    public Dependency()
    {

    }
    public Dependency(String value, String callClass) {
        this.value = value;
        this.callClass = callClass;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCallClass() {
        return callClass;
    }

    public void setCallClass(String callClass) {
        this.callClass = callClass;
    }
}
