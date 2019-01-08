package com.company.DesignMode.AbstractFactoryDesign;

/**
 * Created by eli9 on 8/21/2017.
 */
public class FactoryProducer {
    public static AbstractFactory getFactory(String choice){
        if(choice.equalsIgnoreCase("Shape")){
            return new shapeFactory();
        }
        if(choice.equalsIgnoreCase("Color")){
            return new colorFactory();
        }
        return null;
    }
}
