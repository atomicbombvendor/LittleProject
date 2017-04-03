package com.company.rewrite;

import javax.inject.Named;

/**
 * Created by eli9 on 3/31/2017.
 */
@Named
public class MyData {

    private int age = 1;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
