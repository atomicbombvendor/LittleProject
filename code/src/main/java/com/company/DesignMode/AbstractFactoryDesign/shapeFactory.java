package com.company.DesignMode.AbstractFactoryDesign;

/**
 * Created by eli9 on 8/21/2017.
 */
public class shapeFactory extends AbstractFactory {
    @Override
    Color getColor(String color) {
        return null;
    }

    @Override
    Shape getShape(String shape) {
        if(shape == null){
            return null;
        }
        if(shape.equalsIgnoreCase("Rectangle")){
            return new Rectangle();
        }
        if(shape.equalsIgnoreCase("Square")){
            return new Square();
        }
        return null;
    }
}
