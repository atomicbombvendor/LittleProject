package com.company.DesignMode.DecoratorDesign;

/**
 * Created by atomic on 8/28/2017.
 */
public class RedShapeDecorator extends ShapeDecorator{
    public RedShapeDecorator(Shape shape) {
        super(shape);
    }

    public void draw(){
        setRedBorder();
        shape.draw();
    }

    public void setRedBorder(){
        System.out.println("Border Color: Red");
    }
}
