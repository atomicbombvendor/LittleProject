package com.company.DesignMode.DecoratorDesign;

/**
 * Created by atomic on 8/28/2017.
 */
public class GlassDecorator extends ShapeDecorator {

    public GlassDecorator(Shape shape) {
        super(shape);
    }

    public void draw(){
        setGlassCover();
        shape.draw();
    }

    private void setGlassCover(){
        System.out.println("Cover: glass");
    }
}
