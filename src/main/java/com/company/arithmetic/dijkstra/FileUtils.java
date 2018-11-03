package com.company.arithmetic.dijkstra;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {


    /**
     * 默认读取所有的文件内容
     */
    public static String read(final String filePath) {
        return read(filePath, null);
    }
    /**
     * 读取文件并按行输出
     * @param spec  允许解析的最大行数， spec==null时，解析所有行
     */
    public static String read(final String filePath, final Integer spec) {
        Path path;
        BufferedReader br = null;
        FileReader fb = null;
        StringBuilder sb = new StringBuilder();
        try {
            path = Paths.get(filePath);
            File file = path.toFile();
            // 当文件不存在或者不可读时
            if ((!isFileExists(file)) || (!file.canRead())) {
                System.out.println("file [" + filePath + "] is not exist or cannot read!!!");
                return null;
            }

            fb = new FileReader(file);
            br = new BufferedReader(fb);

            String str;
            int index = 0;
            while (((spec == null) || index++ < spec) && (str = br.readLine()) != null) {
                sb.append(str + "\n");

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeQuietly(br);
            closeQuietly(fb);
        }

        return sb.toString();
    }

    /**
     * 写文件
     *
     * @param filePath 输出文件路径
     * @param content  要写入的内容
     * @param append   是否追加
     */
    public static int write(final String filePath, final String content, final boolean append) {
        File file = new File(filePath);
        if (content == null) {
            System.out.println("file [" + filePath + "] invalid!!!");
            return 0;
        }

        // 当文件存在但不可写时
        if (isFileExists(file) && (!file.canRead())) {
            return 0;
        }

        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            if (!isFileExists(file)) {
                file.createNewFile();
            }

            fw = new FileWriter(file, append);
            bw = new BufferedWriter(fw);

            bw.write(content);
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        } finally {
            closeQuietly(bw);
            closeQuietly(fw);
        }

        return 1;
    }

    private static void closeQuietly(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException e) {
            System.out.println("Some IOException." + e.getMessage());
        }
    }

    private static boolean isFileExists(final File file) {
        return file.exists() && file.isFile();

    }
}
