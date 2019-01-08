package com.company.Spring.TestAOP;


/**
 * Created by eli9 on 8/24/2017.
 */
public class CalculationImpl implements Calculation {
    @Override
    public int add(int i, int j) {
        int result = i+j;
        return result;
    }

    @Override
    public int sub(int i, int j) {
        int result = i-j;
        return result;
    }

    @Override
    public int mul(int i, int j) {
        int result = i*j;
        return result;
    }

    @Override
    public int div(int i, int j) {
        int result = i/j;
        return result;
    }
}
