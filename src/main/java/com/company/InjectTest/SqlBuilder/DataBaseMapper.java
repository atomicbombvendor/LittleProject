package com.company.InjectTest.SqlBuilder;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

/**
 * Created by eli9 on 9/8/2017.
 */
public class DataBaseMapper {
    private String tableName;
    private Set<String> primaryKey;
    private Map<String, Method> methodMap;

    public Set<String> getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(Set<String> primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Map<String, Method> getMethodMap() {
        return methodMap;
    }

    public void setMethodMap(Map<String, Method> methodMap) {
        this.methodMap = methodMap;
    }
}