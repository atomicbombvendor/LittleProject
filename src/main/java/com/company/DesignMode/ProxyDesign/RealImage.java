package com.company.DesignMode.ProxyDesign;

/**
 * Created by atomic on 8/29/2017.
 */
public class RealImage implements Image {

    private String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void display() {
        System.out.println(fileName);
    }
}
