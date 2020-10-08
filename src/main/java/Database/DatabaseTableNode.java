package Database;


/**
 * Created by dinht_000 on 4/5/2017.
 */
public class DatabaseTableNode extends DatabaseNode {

//    protected ClassProperties classProperties;
    private String mappingClass;

    public DatabaseTableNode() {super();}

    public String getMappingClass() {
        return mappingClass;
    }

    public void setMappingClass(String mappingClass) {
        this.mappingClass = mappingClass;
    }

    public DatabaseTableNode(String name) {
        this();
        this.name = name;
    }



}

