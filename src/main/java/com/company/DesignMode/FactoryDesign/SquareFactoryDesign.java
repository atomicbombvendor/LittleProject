package com.company.DesignMode.FactoryDesign;

/**
 * Created by atomic on 8/21/2017.
 */
public class SquareFactoryDesign implements FatherFactory {
    @Override
    public void build() {
        System.out.println("Build Square");
    }
}
