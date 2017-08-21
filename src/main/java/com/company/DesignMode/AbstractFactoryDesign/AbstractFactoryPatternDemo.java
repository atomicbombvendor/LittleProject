package com.company.DesignMode.AbstractFactoryDesign;

/**
 * Created by eli9 on 8/21/2017.
 */
public class AbstractFactoryPatternDemo {
    public static void main(String[] args) {
        AbstractFactory shapeFactory = FactoryProducer.getFactory("Shape");

        Shape s1 = shapeFactory.getShape("Square");
        s1.draw();

        AbstractFactory colorFactory = FactoryProducer.getFactory("Color");
        Color c1 = colorFactory.getColor("Red");
        c1.fill();
    }
}
