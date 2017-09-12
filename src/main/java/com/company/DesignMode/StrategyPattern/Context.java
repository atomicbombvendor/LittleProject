package com.company.DesignMode.StrategyPattern;

import com.company.JavaBase.Extends.StaticsExtends2;

/**
 * Created by eli9 on 9/8/2017.
 */
public class Context {
    private Strategy strategy;

    public Context(Strategy strategy){
        this.strategy = strategy;
    }

    public int executeStrategy(int num1, int num2){
        return strategy.doOpeartion(num1, num2);
    }
}
