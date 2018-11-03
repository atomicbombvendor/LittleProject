package com.company.DesignMode.AbstractFactoryDesign;

/**
 * Created by atomic on 8/21/2017.
 */
public abstract class AbstractFactory {

    abstract Color getColor(String color);
    abstract Shape getShape(String shape);
}
