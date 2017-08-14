package com.company.Properties.Properties;

/**
 * Created by eli9 on 8/14/2017.
 */
import java.io.*;
import java.util.Properties;

public class ConfigProperties {

    private static Properties properties = null;
    //多个配置文件加载
    static {
        InputStream is = null;
        try {
            properties = new Properties();
            is = ConfigProperties.class.getClassLoader().getResourceAsStream("Config-atlas-app-listener.properties");
            properties.load(is);
            is = ConfigProperties.class.getClassLoader().getResourceAsStream("Config-atlas-lib-source-cdc.properties");
            properties.load(is);
            is = ConfigProperties.class.getClassLoader().getResourceAsStream("Config-atlas-lib-source-pricexoi" +
                    ".properties");
            properties.load(is);
            is = ConfigProperties.class.getClassLoader().getResourceAsStream("TableProperties.properties");
            properties.load(is);
            is = ConfigProperties.class.getClassLoader().getResourceAsStream
                    ("Config-atlas-lib-source-pricexoi-groupId.properties");
            properties.load(is);
        } catch (java.io.IOException e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String getProperty(String name) {
        return properties.getProperty(name);
    }

    public static String setProperty(String name, String value) {
        OutputStream out = null;
        try {
            out = new FileOutputStream(ConfigProperties.class.getClassLoader().getResource("TableProperties.properties").getPath().toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Object property = properties.setProperty(name, value);
        try {
            properties.store(out,null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (property == null) {
            return "";
        }
        return property.toString();
    }
}