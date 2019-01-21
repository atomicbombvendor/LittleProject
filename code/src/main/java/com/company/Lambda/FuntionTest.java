package com.company.Lambda;

import java.util.function.Function;

public class FuntionTest {

    public static void main(String[] args) {
        MyFunction f = new MyFunction();
        System.out.println(f.apply("111"));
    }

}
class MyFunction implements Function<String, Integer> {

    @Override
    public Integer apply(String s) {
        return s.length();
    }
}