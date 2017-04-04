package com.company.ClassExtends;

/**
 * Created by eli9 on 4/3/2017.
 */
public class Man extends Person {
    private String man;

    public Man(){}

    public String getMan() {
        return man;
    }

    public void setMan(String man) {
        this.man = man;
    }

    public static void main(String[] args) {
        Man m = new Man();
        m.setName("Bob");
        m.setAge("22");
        m.setSex("Man");
        m.setMan("I'm man");
        /**
         * child class can visit father class's property
         */
        System.out.println(m.getName()+" is " + m.getAge());
    }
}
