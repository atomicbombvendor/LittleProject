package com.company.Properties.Properties;

/**
 * Created by atomic on 3/27/2017.
 */
public interface PropertiesEditor {
    String[] getPrimaryKey(String primaryKey);
    String[] getFields(String field);
    String getTimestamp(String timestamp);
    void setProperty(String key, String value);
}
