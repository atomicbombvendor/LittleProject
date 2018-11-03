package com.company.Properties.Properties;

import java.io.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.Collection;
import java.util.Properties;

/**
 * Created by atomic on 3/24/2017.
 */
public class PropertiesEditorImpl implements PropertiesEditor  {
    private String path;
    private Properties properties;

    public PropertiesEditorImpl(String path){
        this.path = path;
        properties = new Properties();
        try {
            InputStream inputStream =new FileInputStream(path);
            properties.load(inputStream);
            inputStream.close();//close file stream
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String[] getPrimaryKey(String primaryKey){try{
        System.out.println(22);
    }finally {

    }

    if(properties != null) {
             return properties.getProperty(primaryKey).split(";");
        }else {
            return null;
        }

    }

    public String[] getFields(String field){
        String[] fields;
        if(properties != null) {
            String fieldStr = properties.getProperty(field);
            fields = fieldStr.split(";");
            return fields;
        }else {
            return null;
        }
    }

    public String getTimestamp(String timestamp){
        if(properties != null) {
            return properties.getProperty(timestamp);
        }
        return null;
    }

    public void setProperty(String key, String value){
        try {
            OutputStream outputStream = new FileOutputStream(path);
            properties.setProperty(key,value);
            properties.store(outputStream,"Modify: " + key);
            outputStream.close();//Close file stream
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
