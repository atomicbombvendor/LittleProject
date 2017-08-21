package com.company.DesignMode.SingleInstance;

/**
 * Created by eli9 on 8/21/2017.
 */
public class HungryManInstance {
    private static HungryManInstance instance = new HungryManInstance();
    private HungryManInstance(){}

    public static HungryManInstance getInstance(){
       return instance;
    }
}
