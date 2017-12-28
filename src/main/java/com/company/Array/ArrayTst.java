package com.company.Array;


import java.util.Arrays;

/**
 * Created by ZZ on 2017/6/18.
 */
public class ArrayTst {
    /**
     * 测试二维数组是怎么定义的
     * 二维数组是先定义行数，列数
     * a[10][5]表示有10行5列
     */
    public static void testArry(){
        int[][] test = new int[10][5];
        int[] a = test[0];
        System.out.println(a.length);
    }

    /**
     * 只初始化而没有赋值的数组，所有空间的值都是0
     */
    public static void test2(){
        int[] x = new int[25];
        Arrays.stream(x).forEach(System.out::println);
    }

    /**
     * 只初始化而没有被赋值的String类型的数组，所有的值都是Null
     */
    public static void test3(){
        String[] x = new String[25];
        Arrays.stream(x).forEach(System.out::println);
    }
    public static void main(String[] args) {
        //testArry();
        //test2();
        test3();
    }
}
