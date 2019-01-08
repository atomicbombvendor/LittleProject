package com.company.InjectTest.SqlBuilder;

import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * Created by eli9 on 9/8/2017.
 */
public class SqlTemplate {
    private String update;
    private String delete;

    private DataBaseMapper dataBaseMapper;

    public String getUpdate(){
        if(update == null){
            this.update = updateSql(dataBaseMapper);
        }
        return update;
    }

    public String getDelete(){
        if(delete == null){
            this.delete = deleteSql(dataBaseMapper);
        }
        return delete;
    }

    public DataBaseMapper getDataBaseMapper(){
        return dataBaseMapper;
    }

    public SqlTemplate(DataBaseMapper dataBaseMapper){
        this.dataBaseMapper =dataBaseMapper;
    }

    private static final String DELETE_SQL = "DELETE FROM %s WHERE %s";

    //在java中存在一些转义字符,比如"\n"为换行符,但是也有一些JDK自带的一些操作符
    //比如 : System.getProperty("line.separator")
    //这也是换行符,功能和"\n"是一致的,但是此种写法屏蔽了 Windows和Linux的区别 ，更保险一些.
    private static final String UPDATE_SQL = String.join(System.lineSeparator(),
            "MERGE INTO %s t1",
            "  USING (SELECT * from (VALUES {{VALUES}}) as tmp(%s)) as t2",
            "  ON %s",
            " WHEN MATCHED THEN",
            "  UPDATE SET %s",
            " WHEN NOT MATCHED THEN",
            "  INSERT (%s) values (%s);"
    );

    /**
     * 从DataBaseMapper得到表的结构
     * @param dataBaseMapper
     * @return
     */
    private String updateSql(DataBaseMapper dataBaseMapper){
        String tableName = dataBaseMapper.getTableName();
        StringJoiner columns = new StringJoiner(",");//分界符
        StringJoiner conditions = new StringJoiner(" AND ");
        StringJoiner updates = new StringJoiner(",");
        StringJoiner inserts = new StringJoiner(",");
        dataBaseMapper.getMethodMap().keySet().forEach(i -> {
            columns.add(i);
            if(dataBaseMapper.getPrimaryKey().contains(i)){
                conditions.add(String.format("t1.%s = t2.%s", i, i));
            }else{
                updates.add(String.format("t1.%s = t2.%s", i, i));
            }
            inserts.add("t2.".concat(i));
        });
        //String.format 如果参数的个数不匹配，会报错
        return String.format(UPDATE_SQL, tableName, columns.toString(), conditions.toString(), updates.toString(), columns.toString(), inserts.toString());
    }

    /**
     * Join Delete SQL
     * @param dataBaseMapper table structure
     * @return
     */
    private String deleteSql(DataBaseMapper dataBaseMapper){
        String tableName = dataBaseMapper.getTableName();
        String where = dataBaseMapper.getPrimaryKey().stream()
                .map(i -> i.concat("=?"))
                .collect(Collectors.joining(" AND "));
        return String.format(DELETE_SQL, tableName, where);
    }





}
