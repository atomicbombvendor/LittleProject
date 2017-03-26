package Properties;

/**
 * Created by eli9 on 3/24/2017.
 */
public class Table {
    private String [] PrimaryKeys;
    private String [] fileds;
    private String timestamp;

    public String[] getPrimaryKeys() {
        return PrimaryKeys;
    }

    public void setPrimaryKeys(String[] primaryKeys) {
        PrimaryKeys = primaryKeys;
    }

    public String[] getFileds() {
        return fileds;
    }

    public void setFileds(String[] fileds) {
        this.fileds = fileds;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
