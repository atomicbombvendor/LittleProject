package com.company.DesignMode.FactoryDesign;

/**
 * Created by eli9 on 8/21/2017.
 */
public class RectangleFactoryDesign implements FatherFactory {
    @Override
    public void build() {
        System.out.println("Build Rectangle");
    }
}
