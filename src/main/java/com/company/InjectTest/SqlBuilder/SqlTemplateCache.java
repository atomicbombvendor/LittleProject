package com.company.InjectTest.SqlBuilder;

import java.lang.reflect.Method;
import java.sql.Array;
import java.util.*;

/**
 * Created by atomic on 9/8/2017.
 */
public class SqlTemplateCache {

    private static Map<Class, SqlTemplate> mapper = new HashMap<>();

    /**
     * 通过参数的类名，得到这个类的对应的表名，主键的字段和表其他字段的以及字段对应的get方法
     * @param clazz 类的class
     * @return SqlTemplate
     */
    public static SqlTemplate getSqlTemplate(Class clazz){
        //根据类名返回对应的SqlTemplate
        SqlTemplate sqlTemplate = mapper.get(clazz);
        if(sqlTemplate == null){
            DataBaseMapper dataBaseMapper = new DataBaseMapper();
            //Table 已经被实例化了，获得了注解的属性值
            //getAnnotation返回这个元素的注解指定类型
            Table table = (Table) clazz.getAnnotation(Table.class);
            dataBaseMapper.setTableName(table.value());
            dataBaseMapper.setPrimaryKey(new TreeSet<>(Arrays.asList(table.primaryKey())));

            //public Method[] getDeclaredMethods()会获取类自身声明的所有方法，包含public
            //protected private方法。getMethod获取的是类所有的共有方法，包括自身的损友public
            //方法，从基类继承的，从接口实现的所有public方法
            Method[] methods = clazz.getDeclaredMethods();
            Map<String, Method> methodMap= new TreeMap<>();

            Arrays.stream(methods).forEach(m -> {
                Column column = m.getAnnotation(Column.class);
                if(column != null){
                    //methodMap是一个Map
                    methodMap.put(column.value(), m);
                }
            });
            dataBaseMapper.setMethodMap(methodMap);
            sqlTemplate = new SqlTemplate(dataBaseMapper);
            mapper.put(clazz, sqlTemplate);
        }
        return sqlTemplate;
    }
}
