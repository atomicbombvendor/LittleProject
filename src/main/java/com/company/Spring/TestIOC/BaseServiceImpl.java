package com.company.Spring.TestIOC;

import com.company.Dao.TestC;

/**
 * Created by eli9 on 8/24/2017.
 */
public class BaseServiceImpl {
    private TestC testCBase;

    public TestC getTestC(){
        return testCBase;
    }

    public void setTestC(TestC testC){
        this.testCBase = testC;
    }
}
