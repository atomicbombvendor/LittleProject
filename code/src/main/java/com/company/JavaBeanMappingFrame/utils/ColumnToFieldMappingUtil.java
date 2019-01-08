package com.company.JavaBeanMappingFrame.utils;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 将结果集ResultSet映射成为JavaBean(前提是JavaBean中的属性名称和数据库中的字段名称一样)
 */
public class ColumnToFieldMappingUtil {

    private static final Logger logger = Logger.getLogger(ColumnToFieldMappingUtil.class);

    public static <T> List<T> ColumnFiled(ResultSet rs, Class<T> clazz){
        List<T> list = new ArrayList<>();
        try {
            T obj = clazz.newInstance();
            Field[] fields = clazz.getDeclaredFields();
            while(rs.next()){ // rs指的是一行记录
                for(Field f : fields){
                    String name = f.getName();
                    //默认情况下列名等于JavaBean里的属性的值
                    String columnName = new String(name);

                    Class type = f.getType();

                    if(f.isAnnotationPresent(Column.class)){
                        columnName = f.getAnnotation(Column.class).value();
                    }
                    Method method ;
                    try {
                        method = clazz.getMethod("set" + name.replaceFirst(
                            name.substring(0, 1),
                            name.substring(0, 1).toUpperCase()), type);
                        ConvertUtils.register(new CustomDateConvert(), LocalDateTime.class);
                        Object qqq=ConvertUtils.convert(rs.getString(columnName),type);
                        System.out.println("QQQ:" + qqq);
                        method.invoke(obj, ConvertUtils.convert(rs.getString(columnName), type));
                    }catch (IllegalArgumentException e){
                        logger.error("IllegalArgumentException", e);
                    } catch (NoSuchMethodException e) {
                        logger.error("NoSuchMethodException", e);
                    } catch (InvocationTargetException e) {
                        logger.error("InvocationTargetException", e);
                    }
                }
                list.add(obj);
            }
        } catch (InstantiationException | IllegalAccessException | SQLException e) {
            logger.error("ColumnFiled Exception", e);
        }
        return list;
    }
}
