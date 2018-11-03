package com.company.DesignMode.AbstractFactoryDesign;

/**
 * Created by atomic on 8/21/2017.
 */
public class colorFactory extends AbstractFactory {

    @Override
    public Color getColor(String color) {
        if(color == null){
            return null;
        }
        if(color.equalsIgnoreCase("Red")){
            return new Red();
        }
        if(color.equalsIgnoreCase("Green")){
            return new Green();
        }
        return null;
    }

    @Override
    Shape getShape(String shape) {
        return null;
    }
}
