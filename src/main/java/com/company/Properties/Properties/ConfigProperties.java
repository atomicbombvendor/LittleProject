package com.company.Properties.Properties;

/**
 * Created by eli9 on 8/14/2017.
 */
import java.io.*;
import java.util.Properties;

public class ConfigProperties {

    private static Properties properties = null;
    //配置文件加载
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
}