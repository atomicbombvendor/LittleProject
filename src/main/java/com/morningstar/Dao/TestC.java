package com.morningstar.Dao;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by eli9 on 4/3/2017.
 */
@Named("TestC")
public class TestC {
    private String c;

    public void functionC(){}

//    @Inject
//    public TestC(String c) {
//        this.c = c;
//    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }
}
