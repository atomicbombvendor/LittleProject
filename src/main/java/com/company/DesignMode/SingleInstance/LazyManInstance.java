package com.company.DesignMode.SingleInstance;

/**
 * Created by atomic on 8/21/2017.
 */
public class LazyManInstance {
    private static LazyManInstance instance;
    private LazyManInstance(){}

    public static synchronized LazyManInstance getInstance(){
        if(instance == null){
            return new LazyManInstance();
        }
        return instance;
    }
}
