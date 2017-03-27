package Properties.PropertiesEditor;

/**
 * Created by eli9 on 3/27/2017.
 */
public interface PropertiesEditor {
    String[] getPrimaryKey(String primaryKey);
    String[] getFields(String field);
    String getTimestamp(String timestamp);
    void setProperty(String key, String value);
}
