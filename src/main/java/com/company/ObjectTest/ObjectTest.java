package com.company.ObjectTest;

import java.math.BigDecimal;

/**
 * Created by ZZ on 2017/6/18.
 * 测试对象的引用
 */
public class ObjectTest {
    public int value ;

    public static final double POSITIVE_INFINITY = 1.0 / 0.0;

    ObjectTest(int value){
        this.value = value;
    }

    public static void testQuote(ObjectTest o){
        o = new ObjectTest(5);
        //o是一个对象的引用，在方法内部把o重新指向到新的对象上
    }

    public static void testQuote2(ObjectTest o){
        o.value = 7;
        //o 指向参数对象的地址；对o的操作就是对对象的操作
    }

    public static void testEquals(){
        System.out.println("testEquals");

        System.out.println("POSITIVE_INFINITY" + POSITIVE_INFINITY);
        //这种转换会出现 NumberFormatException的异常。不能这样做
        BigDecimal value = BigDecimal.valueOf(POSITIVE_INFINITY);
        System.out.println(BigDecimal.valueOf(10).equals(value));

    }
    public static void main(String[] args) {
        ObjectTest si1=new ObjectTest(1);
        System.out.println(si1.value);//输出1
        testQuote(si1);//并没有改变si1指向的对象地址
        System.out.println(si1.value);//输出1
        testQuote2(si1);//通过引用改变了该地址对象的值
        System.out.println(si1.value);//输出3
    }
}
