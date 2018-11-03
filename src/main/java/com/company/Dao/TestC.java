package com.company.Dao;

import javax.inject.Named;

/**
 * Created by atomic on 4/3/2017.
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
