package Database;

import org.eclipse.jdt.core.dom.MemberValuePair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class HibernateAnnotation {

    String value = new String();
    String callClass = new String();

    String typeName = new String();
    List<MemberValuePair> valueInMarkerAnnotation = new LinkedList<>();

    public HibernateAnnotation()
    {

    }

    public HibernateAnnotation(String typeName, String callClass, List<String> calledClass, String mode)
    {
        this.typeName = typeName;
        this.callClass = callClass;
    }

    public HibernateAnnotation(String value, String callClass) {
        this.value = value;
        this.callClass = callClass;
    }

    public HibernateAnnotation(String value, String callClass, String typeName) {
        this.value = value;
        this.callClass = callClass;
        this.typeName = typeName;
    }

    public HibernateAnnotation(String typeName,String callClass, List<MemberValuePair> valueInMarkerAnnotation) {
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

    public void print()
    {
        System.out.println(this.getCallClass() + " " + this.getTypeName() + " " + this.getValue() + " " + this.getValueInMarkerAnnotation());
    }

}
