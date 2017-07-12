package com.company.ObjectTest;

/**
 * Created by ZZ on 2017/6/18.
 * 测试对象的帅引用
 */
public class ObjectTest {
    public int value ;
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

    public static void main(String[] args) {
        ObjectTest si1=new ObjectTest(1);
        System.out.println(si1.value);//输出1
        testQuote(si1);//并没有改变si1指向的对象地址
        System.out.println(si1.value);//输出1
        testQuote2(si1);//通过引用改变了该地址对象的值
        System.out.println(si1.value);//输出3
    }
}
