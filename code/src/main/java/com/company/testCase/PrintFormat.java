package com.company.testCase;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by eli9 on 4/25/2017.
 * 测试打印格式
 */
public class PrintFormat {

    /**
     * 打印数组带分隔符 使用 StringUtils join 可以拼接
     */
    public static void arrPrintFormat(){
        StringBuilder[] arr = new StringBuilder[10];
        for (int i = 0; i < 10 ; i++) {
            arr[i] = new StringBuilder("a").append(i);
        }

        System.out.println(StringUtils.join(arr,"|"));
    }

    public static void format(){
        double d = 0.30694;
        System.out.println(String.format("%6f",d));
    }

    public static void main(String[] args) {
        format();
    }
}
