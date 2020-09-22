package Model;


import org.eclipse.jdt.core.dom.Modifier;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class classProperties extends NamedEntity{


    private Set<Method> methodName = new HashSet<>();
    private Set<Field> fieldName = new HashSet<>();
    private Set<Constructor> consName = new HashSet<>();

    public classProperties()
    {

    }

    public classProperties(Set<Method> methodName, Set<Field> fieldName, Set<Constructor> consName) {
        this.methodName = methodName;
        this.fieldName = fieldName;
        this.consName = consName;
    }

    public void setUpDependency(List<Modifier> mods)
    {
        this.setModifiers(mods);
    }


    public Set<Method> getMethodName() {
        return methodName;
    }

    public void setMethodName(Set<Method> methodName) {
        this.methodName = methodName;
    }

    public Set<Field> getFieldName() {
        return fieldName;
    }

    public void setFieldName(Set<Field> fieldName) {
        this.fieldName = fieldName;
    }

    public Set<Constructor> getConsName() {
        return consName;
    }

    public void setConsName(Set<Constructor> consName) {
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
        System.out.println(super.getName());

        System.out.println();

        for(Field bf: fieldName)
        {
            System.out.println(bf.toString());
        }

        System.out.println();

        for(Constructor bf: consName)
        {
            System.out.println(bf.toString());
        }

        System.out.println();

        for(Method bf: methodName)
        {
            System.out.println(bf.toString());
        }
    }

}
