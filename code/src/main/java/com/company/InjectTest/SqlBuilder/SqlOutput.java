package com.company.InjectTest.SqlBuilder;

import java.util.List;

/**
 * Created by kwang3 on 2017/4/10.
 */
public class SqlOutput {
    private String sql;
    List<Object[]> values;

    public SqlOutput() {
    }

    public SqlOutput(String sql, List<Object[]> values) {
        this.sql = sql;
        this.values = values;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public List<Object[]> getValues() {
        return values;
    }

    public void setValues(List<Object[]> values) {
        this.values = values;
    }
}
