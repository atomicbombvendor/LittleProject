package com.company.DesignMode.observer;

/**
 * Created by eli9 on 8/28/2017.
 */
public abstract class Observer {
    protected Subject subject;
    public abstract void update();
}