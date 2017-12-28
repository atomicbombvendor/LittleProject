package com.company.JavaLib;

import org.junit.Test;

import java.util.Random;

/**
 * Created by ZZ on 2017/12/28.
 * 用来测试java.util.Random
 */
public class RandomTest {
    //Random是一个类，可以直接new的一个类。
    //Random中有两个构造方法，第一个不带参数的；第二个是带参数的。

    //同一个参数的Random(即使是不同的Random对象)，生产的随机序列是一样的
    @Test
    public void testRandom1(){
        Random random1 = new Random(10);
        for(int i=0; i<10; i++){
            System.out.print(random1.nextInt(10)+" ");//NextInt 产生[0,10)的序列
        }
        System.out.println("\n");
        Random random2 = new Random(10);
        for(int i=0; i<10; i++){
            System.out.print(random2.nextInt(10) + " ");//NextInt 产生[0,10)的序列
        }
    }

    //不带参数的Random使用系统的毫秒数来在种子；所以生成的序列是不一样的。
    @Test
    public void testRandom2(){
        Random random1 = new Random();
        for(int i=0; i<10; i++){
            System.out.print(random1.nextInt(10)+" ");//NextInt 产生[0,10)的序列
        }
        System.out.println("\n");
        Random random2 = new Random();
        for(int i=0; i<10; i++){
            System.out.print(random2.nextInt(10) + " ");//NextInt 产生[0,10)的序列
        }
    }

    //Random中有很多不同的方式；
    //NextInt()无参数的方法
    @Test
    public void testRandom3(){
        Random random1 = new Random();
        for(int i=0; i<10; i++){
            System.out.print(random1.nextInt() + " ");//NextInt 产生随机的数字
            //-1211378280 -1236649177 515718807 -1876106210 936692322 -470621321 338762835 1062142267 360563485 -406595832
        }
        System.out.println("\n");
        Random random2 = new Random();
        for(int i=0; i<10; i++){
            System.out.print(random2.nextInt() + " ");//NextInt 产生随机的数字
        }
    }

    //Random中有很多不同的方式；
    //NextInt()无参数的方法
    @Test
    public void testRandom4(){
        Random random1 = new Random();
        for(int i=0; i<10; i++){
            System.out.print(random1.nextInt(10) + " ");//NextInt 产生随机的数字[0,10)
        }
        System.out.println("\n");
    }

    //NextDouble [0.0,1.0)
    @Test
    public void testRandom5(){
        Random random1 = new Random();
        for(int i=0; i<10; i++){
            System.out.print(random1.nextDouble() + " ");//NextInt 产生随机的数字[0,10)
        }
        System.out.println("\n");
    }
}
