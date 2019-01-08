package com.company.DesignMode.SingleInstance;

/**
 * Created by eli9 on 8/21/2017.
 */
public class LzayManSingleObjectUnSafe {
    private static LzayManSingleObjectUnSafe instance;
    private LzayManSingleObjectUnSafe(){}

    public static LzayManSingleObjectUnSafe getInstance(){
        if(instance == null){
            return new LzayManSingleObjectUnSafe();
        }
        return instance;
    }
}
