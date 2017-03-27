package Properties.Entity;

/**
 * Created by eli9 on 3/24/2017.
 */
public class TableStructureEntityImpl implements TableStructureEntity {
    private String [] PrimaryKeys;
    private String [] fields;
    private String timestamp;

    public String[] getPrimaryKeys() {
        return PrimaryKeys;
    }

    public void setPrimaryKeys(String[] primaryKeys) {
        PrimaryKeys = primaryKeys;
    }

    public String[] getFields() {
        return fields;
    }

    public void setFields(String[] fields) {
        this.fields = fields;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
