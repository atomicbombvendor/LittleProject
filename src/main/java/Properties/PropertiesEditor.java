package Properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;

/**
 * Created by eli9 on 3/24/2017.
 */
public class PropertiesEditor {
    private String path;

    public PropertiesEditor(String path){
        this.path = path;
    }

    public Properties getPropertiesForFile() {
        try {
            InputStream inputStream =new FileInputStream(path);
            Properties properties = new Properties();
            properties.load(inputStream);
            return properties;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String[] getPrimaryKey(String primaryKey){
        Properties properties = getPropertiesForFile();
        if(properties != null) {
             return properties.getProperty(primaryKey).split(";");
        }else {
            return null;
        }
    }

    public String[] getFields(String field){
        String[] fields;
        Properties properties = getPropertiesForFile();
        if(properties != null) {
            String fieldStr = properties.getProperty(field);
            fields = fieldStr.split(";");
            return fields;
        }else {
            return null;
        }
    }

    public String getTimestamp(String timestamp){

        Properties properties = getPropertiesForFile();
        if(properties != null) {
            return properties.getProperty(timestamp);
        }
        return null;
    }

    public static void main(String[] args) {
        String path = "cdctables.properties";
        PropertiesEditor p = new PropertiesEditor(path);
        String primaryKey = "CentralEndData.dbo_QuantitativeData_CT.PrimaryKey";
        String fields = "CentralEndData.dbo_QuantitativeData_CT.Fields";
        /**
         * primaryKey: CompanyId;TrailingType;Period
         * primaryKey2: null
         * not exits hierarchy in key
         */
        Arrays.stream(p.getPrimaryKey(primaryKey)).forEach(s -> System.out.println(s));

        String[] str = p.getFields(fields);
        //for (String s: str) {System.out.println(s);}
        //System.out.println(Arrays.asList(str));
        Arrays.stream(str).forEach(s-> System.out.println(s));
    }
}
