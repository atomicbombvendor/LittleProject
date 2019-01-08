package com.company.DesignMode.AbstractFactoryDesign;

/**
 * Created by eli9 on 8/21/2017.
 */
public abstract class AbstractFactory {

    abstract Color getColor(String color);
    abstract Shape getShape(String shape);
}
