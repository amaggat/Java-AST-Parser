package Model;

import java.util.Set;

public class ClassProperty extends NamedEntity{


    private Set<ClassMethod> methodName;
    private Set<ClassVariable> varName;
    private Set<ClassConstructor> consName;

    public ClassProperty()
    {

    }

    public ClassProperty(Set<ClassMethod> methodName, Set<ClassVariable> varName, Set<ClassConstructor> consName) {
        this.methodName = methodName;
        this.varName = varName;
        this.consName = consName;
    }

    public Set<ClassMethod> getMethodName() {
        return methodName;
    }

    public void setMethodName(Set<ClassMethod> methodName) {
        this.methodName = methodName;
    }

    public Set<ClassVariable> getVarName() {
        return varName;
    }

    public void setVarName(Set<ClassVariable> varName) {
        this.varName = varName;
    }

    public Set<ClassConstructor> getConsName() {
        return consName;
    }

    public void setConsName(Set<ClassConstructor> consName) {
        this.consName = consName;
    }

    public void addMethod(ClassMethod method)
    {
        this.methodName.add(method);
    }

    public void addVar(ClassVariable var)
    {
        this.varName.add(var);
    }

    public void addCons(ClassConstructor cons)
    {
        this.consName.add(cons);
    }

    public void print()
    {
        System.out.println(super.getName());

        System.out.println();

        for(ClassVariable bf: varName)
        {
            System.out.println(bf.toString());
        }

        System.out.println();

        for(ClassConstructor bf: consName)
        {
            System.out.println(bf.toString());
        }

        System.out.println();

        for(ClassMethod bf: methodName)
        {
            System.out.println(bf.toString());
        }
    }

}
