package com.company.DesignMode.DecoratorDesign;

/**
 * Created by eli9 on 8/28/2017.
 */
public class DecoratorDesignDemo {
    public static void main(String[] args) {
        Shape circle = new Circle();

        ShapeDecorator redCircle = new RedShapeDecorator(circle);
        ShapeDecorator glassRedCircle = new GlassDecorator(redCircle);
        glassRedCircle.draw();
    }
}
//the key is decorator must implement component interface.
//File file = new File("");
//FileInputStream fis = new FileInputStream(file);
//InputStreamReader is = new InputStreamReader(fis,"UTF-8");
//BufferedReader br = new BufferedReader(is);
