package com.company.ClassExtends;

/**
 * Created by eli9 on 4/3/2017.
 */
public class Person {
    private String name;
    private String age;
    private String sex;

    public Person(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return this.name+" "+this.age+" "+this.sex;
    }

    @Override
    public int hashCode() {
        return String.valueOf(this.name+this.age+this.sex).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        Person o = (Person)obj;
        boolean flag = (name.equals(o.name) && age.equals(o.age) && sex.equals(o.sex));
        return flag;
    }

}
