package com.company.InjectTest;

import javax.inject.Named;

/**
 * Created by eli9 on 4/3/2017.
 */
@Named
public class Person {

    private String name;

    public Person(){
        System.out.println("This is construction function");
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}
