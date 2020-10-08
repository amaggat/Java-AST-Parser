package Project;

import Model.Node;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jcia on 24/03/2017.
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.MINIMAL_CLASS,
        include = JsonTypeInfo.As.PROPERTY,
        property = "json_type")
public class JciaDependency implements Serializable {

    private static final Logger logger = LogManager.getLogger(Dependency.class);
    private static final long serialVersionUID = -6958270350847533871L;

    protected int id;

    @JciaIgnore
    protected Node caller;
    @JciaIgnore
    protected Node callee;

    private boolean converted = false;


    public JciaDependency() {
//        convertedEdges = new ArrayList<>();
    }

    public JciaDependency(Node positive, Node negative) {
        this();
        this.id = JciaData.getInstance().generateDependencyId();
        this.caller = positive;
        this.callee = negative;

        //TODO: add weight for dependency
        if (caller != null && callee != null && !caller.equals(callee)
                && !caller.getDependencies().contains(this)
                && !callee.getDependencies().contains(this)) {

            this.caller.addJciaDependency(this);
            this.callee.addJciaDependency(this);

//            if (this instanceof JspToStrutsConfigurationDependency) {
//                Class<?> enclosingClass = getClass().getEnclosingClass();
//                String dependencyClass = enclosingClass != null ? enclosingClass.getSimpleName() : getClass().getSimpleName();
//              /*  logger.debug(String.format("[%s:%d] = [%s] -> [%s]",
//                        dependencyClass, this.id, caller.getAbsolutePath(), callee.getAbsolutePath()));*/
//            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        JciaDependency that = (JciaDependency) obj;
        return this.getCaller().equals(that.getCaller()) && this.getCallee().equals(that.getCallee());
    }

    public Node getCaller() {
        return caller;
    }

    public void setCaller(Node caller) {
        this.caller = caller;
    }

    public Node getCallee() {
        return callee;
    }

    public void setCallee(Node callee) {
        this.callee = callee;
    }

    public int getId() {
        return id;
    }

    public boolean isConverted() {
        return converted;
    }

    public void setConverted(boolean converted) {
        this.converted = converted;
    }

    @Override
    public String toString() {
        return "Dependency{" + this.getClass().getSimpleName() + "," +
                caller.getAbsolutePath() +
                "->" + callee.getAbsolutePath() +
                "\n" +
                '}';
    }

//    public List<GraphEdge> getConvertedEdges(Direction direction) {
//        if (!this.isIgnored()) {
//            convertedEdges.clear();
//            return this.convertToEdges(direction);
//        }
//        else return new ArrayList<>();
//    }

//    @Override
//    public List<GraphEdge> convertToEdges(Direction direction) {
//        if (isIgnored()) return new ArrayList<>();
//        if (direction.equals(Direction.DEFAULT)) direction = Direction.VIEWTODB;
//
//
//        convertedEdges.add(new GraphEdge(caller, callee, this, direction));
//        printConvertedEdges();
//        return convertedEdges;
//    }

//    protected void printConvertedEdges() {
//        logger.debug("Edge converted {" + this.getClass().getSimpleName() + "}: " + convertedEdges);
//    }

    public void setId(int id) {
        this.id = id;
    }
}