package com.company.testCase;

/**
 * Created by ZZ on 2017/6/18.
 */
public class HelloB extends HelloA
{
    public HelloB()
    {
    }
    {
        System.out.println("I’m B class");
    }//构造代码块
    static
    {
        System.out.println("static B");
    }
    public static void main(String[] args)
    {
        new HelloB();
    }
}
class HelloA
{
    public HelloA()
    {
    }
    {
        System.out.println("I’m A class");
    }
    static
    {
        System.out.println("static A");
    }
}

