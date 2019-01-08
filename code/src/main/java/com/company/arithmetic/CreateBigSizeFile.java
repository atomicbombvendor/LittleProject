package com.company.arithmetic;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.util.Random;

/**
 * Created by eli9 on 12/27/2017.
 * 构建一个文本文件data.txt，文件内容为1亿行数据，每行数据有5列，分别为：ID, A, B, C,  D。
 ID为10位整数，从100000000开始，每行唯一，顺序递增；A、B、C、D 均为0到10以内随机正数，小数保留2位；列之间用空格分开。
 如下：
 100000001 9.3 6.71 5.76 0.17
 100000002 3.57 7.56 7.11 0.08
 … …

 要求：
 1.程序在-Xmx1024m内存限制下2分钟内可以生产出数据文件
 2.代码风格良好，有必要的注释说明，程序运行过程中，有阶段性的信息打印在console上以知晓进度
 3.不使用第三方库

 提示：
 通过一行数据的字符串长度可以评估出一亿行数据的size，是1GB内存容纳不下的，可以考虑使用流的方式分批写出一定行数的数据到文本文件内；
 由于内存和时间都有限，垃圾收集不能过于频繁，需要减少对象创建，尽可能复用对象乃至引用；用到的数据结构，提前预判好处理的size，减少内存硬拷贝。

 评分标准：
 1. 有意义的类名，方法名和变量名 （5分）
 2. 正确产生0到10之间的随机正数，小数位控制在2位以内 （5分）
 3. 正确使用String Builder组装行数据 （2分） 并懂得设置合理的capacity （3分）
 4. 能够正确将1亿行数据分组处理 （5分）
 5. 分组处理数据的时候能打印处理进度信息和时间 （5分）
 6. 懂得使用正确的输出流，并知道使用完毕后关闭对象 （5分）
 7. 有合适的注释 （3分）
 8. 知道定义类级别重用的对象 （2分）
 9. 程序能在限制条件下正确产生结果 （5分）
 */
public class CreateBigSizeFile {
    
    private static int lineCount = 100000000;
    private static final String dataPath = "d:/data.txt";
    private static final Random random = new Random();
    private static final NumberFormat format = NumberFormat.getInstance();

    // set fraction digits less than 2
    static {
        format.setMaximumFractionDigits(2);
    }

    public static void main(String[] arg) {
        Path path = Paths.get(dataPath);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path.toFile(), true))) {
            for (int i = 0; i < 1000; i++) {
                long start = System.currentTimeMillis();
                String data = generateLineDataPerTenThousand();
                writer.write(data);
                System.out.println("iter " + i + ", size " + data.length() / 1024 + " KB, cost " + (System.currentTimeMillis() - start) + "ms");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ensure random data in [0,10)
    private static String generateRandomNumber() {
        return format.format(random.nextInt(10 % 11) + random.nextDouble());
    }

    // to reduce memory cost, generate 100,000 line data one time.
    private static String generateLineDataPerTenThousand() {
        StringBuilder lineData = new StringBuilder(3100000);
        for (int i = 0; i < 100000; i++) {
            lineData.append(++lineCount)
                    .append(" ")
                    .append(generateRandomNumber())
                    .append(" ")
                    .append(generateRandomNumber())
                    .append(" ")
                    .append(generateRandomNumber())
                    .append(" ")
                    .append(generateRandomNumber())
                    .append(System.getProperty("line.separator"));
        }
        return lineData.deleteCharAt(lineData.length() - 2).toString();
    }

}
