package com.company.DesignMode.StrategyPattern;

/**
 * Created by atomic on 9/8/2017.
 */
public class OperationAdd implements Strategy{

    @Override
    public int doOpeartion(int num1, int num2) {
        return num1 + num2;
    }
}
