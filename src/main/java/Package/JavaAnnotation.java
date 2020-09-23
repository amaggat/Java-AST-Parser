package Package;

import org.eclipse.jdt.core.dom.MemberValuePair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class JavaAnnotation {

    String value = new String();
    String callClass = new String();
    String typeName = new String();
    List<MemberValuePair> valueInMarkerAnnotation = new LinkedList<>();

    public JavaAnnotation()
    {

    }
    public JavaAnnotation(String value, String callClass) {
        this.value = value;
        this.callClass = callClass;
    }

    public JavaAnnotation(String value, String callClass, String typeName) {
        this.value = value;
        this.callClass = callClass;
        this.typeName = typeName;
    }

    public JavaAnnotation(String callClass, String typeName, List<MemberValuePair> valueInMarkerAnnotation) {
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

}
