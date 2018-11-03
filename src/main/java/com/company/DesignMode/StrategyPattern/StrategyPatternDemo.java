package com.company.DesignMode.StrategyPattern;

/**
 * Created by atomic on 9/8/2017.\
 */
public class StrategyPatternDemo {
    public static void main(String[] args) {
        Context context = new Context((v1, v2) -> v1+v2);
        System.out.println("10 + 5 = " + context.executeStrategy(10, 5));

        context = new Context((v1, v2) -> v1/v2);
        System.out.println("10 - 5 = " + context.executeStrategy(10, 5));

        context = new Context((v1, v2) -> v1*v2);
        System.out.println("10 * 5 = " + context.executeStrategy(10, 5));
    }

}
