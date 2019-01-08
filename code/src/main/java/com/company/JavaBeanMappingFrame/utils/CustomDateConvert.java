package com.company.JavaBeanMappingFrame.utils;

import org.apache.commons.beanutils.Converter;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 自定义时间格式转换器;每次转换前在这里进行转换
 */
public class CustomDateConvert implements Converter{
    private static final Logger logger = Logger.getLogger(CustomDateConvert.class);

    @Override
    public Object convert(Class type, Object value) {

        if(value == null){
            return null;
        }

        if(!type.toString().equals("class java.time.LocalDateTime")){
            return value;
        }

        String datetime = (String)value;
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(datetime, df);
    }
}
