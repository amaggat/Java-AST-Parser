package Model;

public class ClassVariable extends NamedEntity{

    @Override
    public String toString() {

        return super.getStatus() + " " + super.getName() + ": " + super.getType();
    }
}
