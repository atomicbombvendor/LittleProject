package com.company.Array;


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

    public static void main(String[] args) {
        testArry();
    }
}
