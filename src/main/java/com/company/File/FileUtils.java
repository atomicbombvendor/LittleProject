package com.company.File;

import java.io.File;
import java.io.FilenameFilter;
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
}
