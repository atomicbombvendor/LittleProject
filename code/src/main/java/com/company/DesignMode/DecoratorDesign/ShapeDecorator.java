package com.company.DesignMode.DecoratorDesign;

/**
 * Created by eli9 on 8/28/2017.
 */
public class ShapeDecorator implements Shape {
    protected Shape shape;
    public  ShapeDecorator(Shape shape){
        this.shape = shape;
    }

    @Override
    public void draw(){
        shape.draw();
    }
}
