package com.company.Lambda;

import java.util.List;
import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * Created by atomic on 3/16/2017.
 */
public class BasicCalc {
    public static Function<List<Double>, Double> sum = l -> l.stream().mapToDouble(i->i).sum();

    public static UnaryOperator<Double> power(double i){ return r-> Math.pow(r,i);}

    public static UnaryOperator<Double> plus(double x) {return y -> y+x;}

    public static UnaryOperator<Double> minus(double x) {return y -> y-x;}

    public static UnaryOperator<Double> multiply(double x) {
        return y -> y * x;
    }

    public static UnaryOperator<Double> divide(double x) { return y -> y / x;}
}
