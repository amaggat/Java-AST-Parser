package Package;

import org.eclipse.jdt.core.dom.MemberValuePair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SpringAnnotation {

    String value = new String();
    String callClass = new String();
    List<String> calledClass = new ArrayList<>();

    String typeName = new String();
    List<MemberValuePair> valueInMarkerAnnotation = new LinkedList<>();

    public SpringAnnotation()
    {

    }

    public SpringAnnotation(String typeName, String callClass, List<String> calledClass, String mode)
    {
        this.typeName = typeName;
        this.callClass = callClass;
        this.calledClass = calledClass;
    }

    public SpringAnnotation(String value, String callClass) {
        this.value = value;
        this.callClass = callClass;
    }

    public SpringAnnotation(String value, String callClass, String typeName) {
        this.value = value;
        this.callClass = callClass;
        this.typeName = typeName;
    }

    public SpringAnnotation(String typeName,String callClass, List<MemberValuePair> valueInMarkerAnnotation) {
        this.callClass = callClass;
        this.typeName = typeName;
        this.valueInMarkerAnnotation = valueInMarkerAnnotation;
    }

    public List<MemberValuePair> getValueInMarkerAnnotation() {
        return valueInMarkerAnnotation;
    }

    public void setValueInMarkerAnnotation(List<MemberValuePair> valueInMarkerAnnotation) {
        this.valueInMarkerAnnotation = valueInMarkerAnnotation;
    }

    public List<String> getCalledClass() {
        return calledClass;
    }

    public void setCalledClass(List<String> calledClass) {
        this.calledClass = calledClass;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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

    public void print()
    {
        System.out.println(this.getCallClass() + " " + this.getTypeName() + " " + this.getValue() + " " + this.getValueInMarkerAnnotation() + " " + this.getCalledClass());
    }

}
