package com.company.DesignMode.FactoryDesign;

/**
 * Created by eli9 on 8/21/2017.
 */
public class FactoryDemo {
    public static void main(String[] args) {
        Factory factory = new Factory();

        FatherFactory f1 = factory.getBuild("Rectangle");
        f1.build();

        FatherFactory f2 = factory.getBuild("Square");
        f2.build();
    }
}
