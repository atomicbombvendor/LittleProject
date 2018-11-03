package com.company.Dao;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by atomic on 3/31/2017.
 */
@Named("TestA")
public class TestA {
    @Inject
    private TestB b;

    public void testSpring(){
        System.out.println("b: " + b.equals(""));
    }

}
