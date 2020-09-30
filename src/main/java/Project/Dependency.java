package Project;

public class Dependency {
    private String callClass = new String();
    private String calledClass = new String();

    public Dependency() {
    }

    public Dependency(String callClass, String calledClass) {
        this.callClass = callClass;
        this.calledClass = calledClass;
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
}
