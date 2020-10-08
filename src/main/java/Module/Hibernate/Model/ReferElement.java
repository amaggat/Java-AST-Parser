package Module.Hibernate.Model;

import java.io.Serializable;

public class ReferElement implements Serializable {

    private String referForeignKey;
    private String referTable;

    public ReferElement() {
    }

    public ReferElement(String referForeignKey, String referTable) {
        this.referForeignKey = referForeignKey;
        this.referTable = referTable;
    }

    public String getReferForeignKey() {
        return referForeignKey;
    }

    public void setReferForeignKey(String referForeignKey) {
        this.referForeignKey = referForeignKey;
    }

    public String getReferTable() {
        return referTable;
    }

    public void setReferTable(String referTable) {
        this.referTable = referTable;
    }
}
