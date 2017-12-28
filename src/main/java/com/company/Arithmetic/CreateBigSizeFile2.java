package com.company.Arithmetic;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

/**
 * Created by ZZ on 2017/12/28.
 */
public class CreateBigSizeFile2 {
    private static final int lineCount = 1000;
    private static int ID = 100000000;
    private static final String dataPath = "d:\\data.txt";
    private static final Random random = new Random();
    private static final int interval = 100;

    private static String generateRandomNumber(){
        return String.format("%.2f", random.nextInt(10) + random.nextDouble());
    }

    private static String generateLine(){//id start from 100,000,000
        StringBuilder content = new StringBuilder(interval * 31);
        for(int i=0; i < interval; i++){
            StringBuilder lineContent = new StringBuilder(31);//每行的大小定为31
            lineContent.append(ID++) //长度：9
                    .append(" ") // 1
                    .append(generateRandomNumber())// 4
                    .append(" ") // 1
                    .append(generateRandomNumber())// 4
                    .append(" ") // 1
                    .append(generateRandomNumber())// 4
                    .append(" ") // 1
                    .append(generateRandomNumber()) //4
                    .append(System.getProperty("line.separator")); //2 //总长度31
            content.append(lineContent);
        }
        return content.deleteCharAt(content.length()-2).toString();
        //return content.toString();
    }

    public static void createFile(){
        Path path = Paths.get(dataPath);

        try {
            if(path.toFile().exists()){
                path.toFile().delete();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(path.toFile(), true));
            for(int i=0; i < lineCount/interval; i++){
                long start = System.currentTimeMillis();
                String data = generateLine();
                writer.write(data);
                System.out.println("iter " + i + ", size " + data.length()/1024 + " KB, cost " + (System.currentTimeMillis() - start) + "ms");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        createFile();
    }
}
