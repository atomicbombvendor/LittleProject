package com.company.Dao;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by eli9 on 3/31/2017.
 */
@Named("TestB")
public class TestB {
    private String s;
    @Inject
    private TestC testC;

//    @Inject
//    public TestB(String s){
//        this.s = s;
//    }
    //基本对象类型不需要注入
    public void setS(String s) {
        this.s = s;
    }
}
