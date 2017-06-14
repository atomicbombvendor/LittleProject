package com.company.File;

import com.company.Entity.ExchangeEntity;
import com.sun.org.apache.regexp.internal.RE;

import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eli9 on 5/16/2017.
 */
public class FileUtils {
    /**
     * 得到目录下面所有的文件
     * @param fileServerPath 文件目录的路径
     * @param filePattern 文件名的格式
     * @return
     */
    public static List<String> getAllFilePath(String fileServerPath, String filePattern){
        List<String> fileList = new ArrayList<>();
        File dir = new File(fileServerPath);
        final String fileRegex = filePattern;
        File[] files = dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.matches(fileRegex);
            }
        });

        if (files != null) {
            for (File file : files) {
                fileList.add(file.getPath());
            }
        }
        return fileList;
    }

    public static void writeFile(List<ExchangeEntity> list){
        String filePath  = "D:\\ExchangeList.sql";
        try {
            File f = new File(filePath);
            if(f.exists()){
                f.delete();
            }else{
                f.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String format = "Insert Into mart.dbo.Exchange(" +
                "ExchangeId," +
                "ExchangeGlobalId," +
                "MIC," +
                "ExchangeName," +
                "CountryId," +
                "CountryName," +
                "RegionId," +
                "RegionName) " +
                "Values('%s','%s','%s','%s','%s','%s','%s','%s');";
        String ExchangeId;
        String ExchangeGlobalId;
        String MIC;
        String ExchangeName;
        String CountryId;
        String CountryName;
        String RegionId;
        String RegionName;
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(filePath);
            for(int i=0;i <list.size(); i++){
                ExchangeEntity entity = list.get(i);
                ExchangeId = entity.getExchangeId();
                ExchangeGlobalId = entity.getExchangeGlobalId();
                MIC = entity.getMIC();
                ExchangeName = entity.getExchangeName();
                CountryId = entity.getCountryId();
                CountryName = entity.getCountryName();
                RegionId = entity.getRegionId();
                RegionName = entity.getRegionName();
                fileWriter.write(String.format(format,
                        ExchangeId,
                        ExchangeGlobalId,
                        MIC,
                        ExchangeName,
                        CountryId,
                        CountryName,
                        RegionId,
                        RegionName));
                fileWriter.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        writeFile(ReadXML.getExchangeList());
    }
}
