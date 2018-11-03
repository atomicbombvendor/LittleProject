package com.company.DesignMode.SingleInstance;

/**
 * Created by atomic on 8/21/2017.
 */
public class DoubleCheckInstance {
    private volatile static DoubleCheckInstance instance;
    private DoubleCheckInstance(){}

    public static DoubleCheckInstance getInstance(){
        if(instance == null){
            synchronized (DoubleCheckInstance.class){
                if(instance == null){
                    instance = new DoubleCheckInstance();
                }
            }
        }
        return instance;
    }
}
