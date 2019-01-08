package com.company.testCase;

/**
 * Created by eli9 on 3/2/2017.
 */
public class LittleTest1 {
    public static void main(String[] args) {
        Integer a = new Integer(3);
        Integer b = 3; // 将 3 自动装箱成 Integer 类型
        int c = 3;
        System.out.println(a == b); // false 两个引用没有引用同一对象
        System.out.println(a == c); // true a 自动拆箱成 int 类型再和 c 比较

        Integer f1 = 100, f2 = 100, f3 = 150, f4 = 150;
        System.out.println(f1 == f2); //true
        System.out.println(f3 == f4); //false
        //Java 享元模式 -128~127的数字会被重用 大于这个的不会


        String str ="0P0000X48I20150831";
                   //123456789abcdefghi
        //截取位置start - (end-1),截取的长度为 (end-start)
        //0~10 截取0~9，长度为10
        System.out.println(str.substring(0,10)+"||"+str.substring(10,18));
    }
}
