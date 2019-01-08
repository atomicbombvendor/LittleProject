package com.company.ClassExtends;

/**
 * Created by eli9 on 10/30/2017.
 */
public class Alienware extends Man {

    //没有构造函数的子类，在初始化自己时，当然会先调用父类的构造方法。
    //会调用父类的没有参数的构造方法。
    public static void main(String[] args) {
        Alienware alien = new Alienware();
    }
}
