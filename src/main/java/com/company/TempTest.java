package com.company;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by ZZ on 2017/6/18.
 */
public class TempTest {
    public static int findSum(int m,int n){
        int sum=0;
        for(int i=m;i<=n;i++){
            sum+=i;
        }
        return sum;
    }

    public static int test2(){
        return (~10);
    }

    /**
     * short and byte
     */
    public static void test3(){
        short a =128;
        byte b =(byte) a;
        System.out.println(a + ""+b);
    }

    public static void doSomething(Integer integer){
        integer=new Integer(2);
    }
    public static void main(String[] args) {
        Integer var1=new Integer(1);
        Integer var2=var1;
        doSomething(var2);
        System.out.print(var1.intValue());
        System.out.print(var1==var2);
        test3();
    }
}
