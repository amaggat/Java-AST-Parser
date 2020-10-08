package Database;



import Module.Hibernate.Model.ReferElement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by dinht_000 on 4/5/2017.
 */
public class DatabaseColumnNode extends DatabaseNode {
    protected String entityName;

    protected boolean isPrimaryKey;
    protected boolean isNotNull;
    protected boolean isUnique;
    protected boolean isBinary;
    protected boolean isUnsigned;
    protected boolean isZeroFill;
    protected boolean isAutoIncrement;
    protected boolean isGenerated;
    protected boolean isForeignKey;
    private String mappingField;


    protected boolean isManytoOne;
    protected boolean isOnetoMany;
    protected boolean isManytoMany;

    protected String classManyToMany;
    protected String classOneToMany;
    protected String classManyToOne;

    protected String collumnNameMTMmapping;

    protected Set<ReferElement> referElementList;

    protected String dataType;
    private int length;


//    this.name = name;
//    this.typedata = type;
//    this.length = length;
//    this.primaryKey = primaryKey;
//    this.notNull = notNull;
//    this.unique = unique;
//    this.foreignKey = foreignKey;
//    this.autoIncrement = autoIncrement;


    public String getMappingField() {
        return mappingField;
    }

    public void setMappingField(String mappingField) {
        this.mappingField = mappingField;
    }

    // insert to H2DB must have AbsolutePath
    public DatabaseColumnNode() {
        super();
        this.setAbsolutePath("");
        this.referElementList = new HashSet<>();
    }


    public DatabaseColumnNode(String name) {
        super();
        this.name = name;
        this.setAbsolutePath("");
    }

    public void addReferElemet(ReferElement referElement) {
        this.referElementList.add(referElement);
    }

    public Set<ReferElement> getReferElementList() {
        return referElementList;
    }

    public void setReferElementList(Set<ReferElement> referElementList) {
        this.referElementList = referElementList;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public boolean isPrimaryKey() {
        return isPrimaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        isPrimaryKey = primaryKey;
    }

    public boolean isNotNull() {
        return isNotNull;
    }

    public void setNotNull(boolean notNull) {
        isNotNull = notNull;
    }

    public boolean isUnique() {
        return isUnique;
    }

    public void setUnique(boolean unique) {
        isUnique = unique;
    }

    public boolean isBinary() {
        return isBinary;
    }

    public void setBinary(boolean binary) {
        isBinary = binary;
    }

    public boolean isUnsigned() {
        return isUnsigned;
    }

    public void setUnsigned(boolean unsigned) {
        isUnsigned = unsigned;
    }

    public boolean isZeroFill() {
        return isZeroFill;
    }

    public void setZeroFill(boolean zeroFill) {
        isZeroFill = zeroFill;
    }

    public boolean isAutoIncrement() {
        return isAutoIncrement;
    }

    public void setAutoIncrement(boolean autoIncrement) {
        isAutoIncrement = autoIncrement;
    }

    public boolean isGenerated() {
        return isGenerated;
    }

    public void setGenerated(boolean generated) {
        isGenerated = generated;
    }


    public boolean isManytoOne() {
        return isManytoOne;
    }

    public void setManytoOne(boolean manytoOne) {
        isManytoOne = manytoOne;
    }

    public boolean isOnetoMany() {
        return isOnetoMany;
    }

    public void setOnetoMany(boolean onetoMany) {
        isOnetoMany = onetoMany;
    }

    public boolean isManytoMany() {
        return isManytoMany;
    }

    public void setManytoMany(boolean manytoMany) {
        isManytoMany = manytoMany;
    }

    public String getClassManyToMany() {
        return classManyToMany;
    }

    public void setClassManyToMany(String classManyToMany) {
        this.classManyToMany = classManyToMany;
    }

    public String getClassOneToMany() {
        return classOneToMany;
    }

    public void setClassOneToMany(String classOneToMany) {
        this.classOneToMany = classOneToMany;
    }

    public String getClassManyToOne() {
        return classManyToOne;
    }

    public void setClassManyToOne(String classManyToOne) {
        this.classManyToOne = classManyToOne;
    }

    public String getCollumnNameMTMmapping() {
        return collumnNameMTMmapping;
    }

    public void setCollumnNameMTMmapping(String collumnNameMTMmapping) {
        this.collumnNameMTMmapping = collumnNameMTMmapping;
    }


    public boolean isForeignKey() {
        return isForeignKey;
    }

    public void setForeignKey(boolean foreignKey) {
        isForeignKey = foreignKey;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "DatabaseColumnNode{" +
                ", name=" + name +
                ", isPrimaryKey=" + isPrimaryKey +
                ", isNotNull=" + isNotNull +
                ", isUnique=" + isUnique +
                ", isBinary=" + isBinary +
                ", isUnsigned=" + isUnsigned +
                ", isZeroFill=" + isZeroFill +
                ", isAutoIncrement=" + isAutoIncrement +
                ", isGenerated=" + isGenerated +
                ", isManyToOne=" + isManytoOne +
                ", isOneToMany=" + isOnetoMany +
                ", isManyToMany=" + isManytoMany +
                ", isForeingnkey= " + isForeignKey +
                ", dataType = " + dataType +
                ", length= " + length +
                '}';
    }
}
