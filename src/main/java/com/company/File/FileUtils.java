package com.company.File;

import com.company.Entity.ExchangeEntity;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eli9 on 5/16/2017.
 */
public class FileUtils {
    /**
     * 得到目录下面所有的文件
     *
     * @param fileServerPath 文件目录的路径
     * @param filePattern    文件名的格式
     * @return
     */
    public static List<String> getAllFilePath(String fileServerPath, String filePattern) {
        List<String> fileList = new ArrayList<>();
        File dir = new File(fileServerPath);
        final String fileRegex = filePattern;
        File[] files = dir.listFiles((dir1, name) -> name.matches(fileRegex));

        if (files != null) {
            for (File file : files) {
                fileList.add(file.getPath());
            }
        }
        return fileList;
    }

    public static File[] getFiles(String fileServerPath, String filePattern) {

        File dir = new File(fileServerPath);
        final String fileRegex = filePattern;
        File[] files = dir.listFiles((dir1, name) -> name.matches(fileRegex));
        return files;
    }

    /** */
    /**
     * 文件重命名
     *
     * @param path    文件目录
     * @param oldName 原来的文件名
     * @param newName 新文件名
     */
    public static void renameFile(String path, String oldName, String newName) {

        if (!oldName.equals(newName)) {//新的文件名和以前文件名不同时,才有必要进行重命名
            File oldFile = new File(path + "/" + oldName);
            File newFile = new File(path + "/" + newName);
            if (!oldFile.exists()) {
                return;//重命名文件不存在
            }
            if (newFile.exists())//若在该目录下已经有一个文件和新文件名相同，则不允许重命名
                System.out.println(newName + "已经存在！");
            else {
                oldFile.renameTo(newFile);
            }
        } else {
            System.out.println("新文件名和旧文件名相同...");
        }
    }

    public static String getNewFileName(String oldName) {
        String prefix = "dbo.";
        StringBuilder newFileName = new StringBuilder(prefix);
        if (oldName.contains("_Test")) {
            newFileName.append(oldName.replaceAll("_Test", ""));
        } else {
            newFileName.append(oldName);
        }
        return newFileName.toString();
    }

    public static void test1() {
        String path = "C:\\Users\\eli9\\Desktop\\test";
        String filePattern = "^[A-Za-z0-9_]+_Test\\.sql$";
        File[] files = getFiles(path, filePattern);

        if (files != null) {
            for (File file : files) {
                String newFileName = getNewFileName(file.getName());
                renameFile(path, file.getName(), newFileName);
            }
        }
    }

    public static void writeFile(List<ExchangeEntity> list) {
        String filePath = "D:\\ExchangeList.sql";
        try {
            File f = new File(filePath);
            if (f.exists()) {
                f.delete();
            } else {
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
            for (int i = 0; i < list.size(); i++) {
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

    public static List<String> getContent(String fileServerPath, String filePattern) {
        List<String> result = new ArrayList<>();
        String filePath = getAllFilePath(fileServerPath, filePattern).get(0);
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;//用于包装InputStreamReader,提高处理性能。因为BufferedReader有缓冲的，而InputStreamReader没有。

        try {
            String value = null;
            fis = new FileInputStream(filePath);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);

            while ((value = br.readLine()) != null) {
                result.add(value);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                isr.close();
                fis.close();
                // 关闭的时候最好按照先后顺序关闭最后开的先关闭所以先关s,再关n,最后关m
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static void main(String[] args) {
        //writeFile(ReadXML.getExchangeList());
        String filePath = "C:\\Users\\eli9\\Desktop\\Tables\\Equity2Tables-20171102";
        String fileName = "^dbo.[A-Za-z0-9_]+\\.sql$";
        List<String> result = getAllFilePath(filePath, fileName);
        result.stream().forEach(s -> System.out.println(s.replace(".sql", "_Test")));
        System.out.println(result.size());

        //重命名文件
        //test1();

//        String filePath = "C:\\Users\\eli9\\Desktop\\Tables\\Equity2Tables-20171102";
//        String filePattern = "^dbo.[A-Za-z0-9_]+\\.sql$";
//        getAllFilePath(filePath, filePattern)
//                .forEach(System.out::println);
    }

    public static List<String> getFileContent(String file) {
        List<String> content = new ArrayList<>();

        if (file == null) {
            return content;
        }
        File f = new File(file);
        if (f.isDirectory()) {
            return content;
        }

        BufferedReader reader = null;
        try {
            String tempString;
            reader = new BufferedReader(new FileReader(f));

            while ((tempString = reader.readLine()) != null) {
                content.add(processStr1(tempString));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.getMessage();
                }
            }
        }
        return content;
    }

    public static void writeFile(String filepath, List<String> content) throws Exception
    {
        if(content == null || content.size() == 0){
            return;
        }

        FileWriter fw = null;
        try
        {
            File file = new File(filepath);
            if(file.isDirectory()){
                throw new RuntimeException("Result path can't be directory");
            }
            // if file doesnt exists, then create it
            if (!file.exists())
            {
                file.createNewFile();
            }
            fw = new FileWriter(file.getAbsoluteFile(), true);
            for (String c : content) {
                fw.write(c + "\r\n");
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (fw != null)
            {
                fw.close();
            }
        }
    }

    /**
     * 特殊的处理字符串的逻辑，不可重用
     */
    static String processStr1(String temp){
        String[] strs = temp.split("\t");
        String cusip = strs[1];
        String dateId = strs[2].replace("-","");
        String case3 = processStr1OfDouble(strs[3]);
        String case4 = processStr1OfDouble(strs[4]);
        String case5 = processStr1OfDouble(strs[5]);
        String case6 = processStr1OfDouble(strs[6]);

        return String.valueOf(new StringBuilder(strs[0]).append("\t")
                .append(cusip).append("\t")
                .append(dateId).append("\t")
                .append(case3).append("\t")
                .append(case4).append("\t")
                .append(case5).append("\t")
                .append(case6));
    }

    private static String processStr1OfDouble(String cases){
        if(!cases.contains(".")){
            return cases;
        }
        int dotIndex = cases.indexOf(".");
        //数字的小数位超过5位，达到6位及以上时，要做四舍五入
        if((cases.length()-dotIndex) > 6){
            double value = Math.round(Double.parseDouble(cases)*100000)/100000.00000;
            return String.valueOf(value);
        }else{
            return cases;
        }

    }
}
