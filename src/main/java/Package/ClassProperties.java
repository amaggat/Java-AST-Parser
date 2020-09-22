package Package;

import Class.*;
import Model.NamedEntity;
import org.eclipse.jdt.core.dom.Annotation;
import org.eclipse.jdt.core.dom.Modifier;
import java.util.ArrayList;
import java.util.List;


public class ClassProperties extends NamedEntity {

    private List<Method> methodName = new ArrayList<>();
    private List<Field> fieldName = new ArrayList<>();
    private List<Constructor> consName = new ArrayList<>();

    public ClassProperties()
    {

    }

    public ClassProperties(List<Method> methodName, List<Field> fieldName, List<Constructor> consName) {
        this.methodName = methodName;
        this.fieldName = fieldName;
        this.consName = consName;
    }

    public void setUpDependency(List<Dependency> mods)
    {
        super.setDependencies(mods);
    }


    public List<Method> getMethodName() {
        return methodName;
    }

    public void setMethodName(List<Method> methodName) {
        this.methodName = methodName;
    }

    public List<Field> getFieldName() {
        return fieldName;
    }

    public void setFieldName(List<Field> fieldName) {
        this.fieldName = fieldName;
    }

    public List<Constructor> getConsName() {
        return consName;
    }

    public void setConsName(List<Constructor> consName) {
        this.consName = consName;
    }

    public void addMethod(Method method)
    {
        this.methodName.add(method);
    }

    public void addField(Field var)
    {
        this.fieldName.add(var);
    }

    public void addCons(Constructor cons)
    {
        this.consName.add(cons);
    }

    public void print()
    {

        if(!super.getModifiers().isEmpty())
        {
            for(Object obj : super.getModifiers())
            {
                if(obj instanceof Annotation)
                {
                    System.out.println(obj.toString());
                }
                if(obj instanceof Modifier)
                {
                    System.out.print(((Modifier) obj).getKeyword() + " ");
                }
            }
        }

        System.out.println(super.getName());


        for(Field field: fieldName)
        {
            field.print();
        }


        for(Constructor constructor: consName)
        {
            constructor.print();
        }


        for(Method method: methodName)
        {
            method.print();
        }

        System.out.println();
    }

}
