package com.company.Properties.Entity;

/**
 * Created by eli9 on 3/27/2017.
 */
public interface TableStructureEntity {
    String[] getPrimaryKeys();
    String[] getFields();
    String getTimestamp();
    void setPrimaryKeys(String[] primaryKeys);
    void setFields(String[] fields);
    void setTimestamp(String timestamp);

}
