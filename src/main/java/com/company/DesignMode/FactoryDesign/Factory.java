package com.company.DesignMode.FactoryDesign;

/**
 * Created by atomic on 8/21/2017.
 */
public class Factory {
    public FatherFactory getBuild(String type){
        if(type == null){
            return null;
        }
        if(type.equalsIgnoreCase("Rectangle")){
            return new RectangleFactoryDesign();
        }else if(type.equalsIgnoreCase("Square")){
            return new SquareFactoryDesign();
        }
        return null;
    }

}
