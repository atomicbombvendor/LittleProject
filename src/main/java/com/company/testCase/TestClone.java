package com.company.testCase;

/**
 * Created by eli9 on 4/6/2017.
 */
public class TestClone {
    public String name;
    public int value;
    public void change(TestClone a){
        a.name = "b";
    }

    public static void main(String[] args) {
        TestClone a1=new TestClone();
        TestClone a2=new TestClone();
        a1.name="a1";
        a1.value=2;
        a2=a1;//因为对象的浅克隆，指向的是同一个空间所以才会有修改了成员变量，两个class instance 都被修改了
        a2.name="a2";
        a2.value=3;
        System.out.println("a1.name="+a1.name.hashCode()+" Class: "+a1.hashCode());
        System.out.println("a2.name="+a2.name.hashCode()+" Class: "+a2.hashCode());
        a2.name="他";
        System.out.println("a1.name="+a1.name.hashCode()+" Class: "+a1.hashCode());
        System.out.println("a2.name="+a2.name.hashCode()+" Class: "+a2.hashCode());

        String s1 = "s1";
        String s2 = "s2";
        System.out.println("s1: "+s1.hashCode());
        System.out.println("s2: "+s2.hashCode());
        System.out.println("end");
        s1="s2";// s1 s2 指向"s2"的空间
        System.out.println("s1: "+s1.hashCode());
        System.out.println("s2: "+s2.hashCode());
        System.out.println("end");
        s1="s3";// s1指向"s3"空间 s2指向"s2"空间
        System.out.println("s1: "+s1.hashCode());
        System.out.println("s2: "+s2.hashCode());
        System.out.println("end");
        s2=s1;// s1 s2指向"s3"空间
        System.out.println("s1: "+s1.hashCode());
        System.out.println("s2: "+s2.hashCode());
        System.out.println("end");
        //s1="s4";// s1指向了"s4"的空间，被改变 s2的指向没有变
        s2="s4";
        System.out.println("s1: "+s1.hashCode());
        System.out.println("s2: "+s2.hashCode());
        System.out.println("end");

        String str1="aaa";
        String str2 = str1;
        System.out.println("str1: "+str1.hashCode());
        System.out.println("str2: "+str2.hashCode());
        System.out.println("end");
        str2="bbb";
        System.out.println("str1: "+str1.hashCode());
        System.out.println("str2: "+str2.hashCode());
        System.out.println("end");
        //如果是String, 用“=”赋值时，直接是引用赋值。左边的变量直接指向右边的空间
        //如果是类的话，直接修改了类成员变量引用空间的值。
    }

}
