package com.company.Entity;

/**
 * Created by eli9 on 4/17/2017.
 */
//E表示的范型。表明这个类中可以有0到n个E类型的成员变量。E的类型在实例化时定义
public class Person<T> {
    private String name;
    private int age;
    private T t;

    public Person(){}
    public Person(String name){
        this.name = name;
    }
    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
