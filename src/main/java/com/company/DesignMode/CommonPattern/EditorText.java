package com.company.DesignMode.CommonPattern;

/**
 * Created by eli9 on 11/1/2017.
 */
public class EditorText implements Editor {
    @Override
    public void save() {
        System.out.println("Save");
    }

    @Override
    public void open() {
        System.out.println("Open");
    }

    @Override
    public void close() {
        System.out.println("Close");
    }
}
