package Project;

public class Dependency {
    private String callClass = new String();
    private String calledClass = new String();
    private String type = new String();

    public Dependency() {
    }

    public Dependency(String callClass, String calledClass) {
        this.callClass = callClass;
        this.calledClass = calledClass;
    }

    public Dependency(String callClass, String calledClass, String type) {
        this.callClass = callClass;
        this.calledClass = calledClass;
        this.type = type;
    }

    public String getCallClass() {
        return callClass;
    }

    public void setCallClass(String callClass) {
        this.callClass = callClass;
    }

    public String getCalledClass() {
        return calledClass;
    }

    public void setCalledClass(String calledClass) {
        this.calledClass = calledClass;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void print()
    {
        System.out.println(this.getCallClass() + " affected " + this.getCalledClass());
    }
}
