package com.morningstar.Dao;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by eli9 on 3/31/2017.
 */
@Named("testA")
public class TestA {

    private TestB b;

    @Inject
    public TestA(TestB b){
        this.b = b;
    }

    public void testSpring(){}
}
