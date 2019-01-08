package com.company.Lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by eli9 on 3/16/2017.
 */
public class FunctionExam {
    public static int applyExam(int value){
        Function<Integer, String> convert = (i) -> Integer.toString(i);
        return convert.apply(value).length();
    }

    public static int composeExam(String value){
        Function<Integer, String> convert = (i) -> Integer.toString(i);
        Function<String, Integer> reverse = (s) -> Integer.parseInt(s);
        return convert.compose(reverse).apply(value).length();
    }

    public static int andThenExam(int value){
        Function<Integer, String> convert = (i) -> Integer.toString(i);
        Function<String, Integer> reverse = (s) -> Integer.parseInt(s);
        return convert.andThen(reverse).apply(value).byteValue();
    }

    public static int identityExam(int value){
        Function<Integer, Integer> id = Function.identity();
        return id.apply(value);
    }
                                                //multiple argument(0~n)
    public static int decoratorExam(int value, Function<Integer, Integer> ... decorators) {
//        return ArrayTst.asList(decorators).stream().reduce((current, next) -> current.andThen(next)).orElse
//                (Function::identity).apply(value);
        return Arrays.asList(decorators).stream().reduce((current, next) -> current.andThen(next))
                .orElseGet(Function::identity).apply(value);
    }

    public static void lamdabGrammerTest(){
        System.out.println("lamdabGrammerTest");
        Predicate<String> pre = (x) -> {
            System.out.print(x);
            return false;
        };
        System.out.println(": "+pre.test("Hello world"));

        //no return value
        Consumer<String> con = (x) -> {
            System.out.println(x);
        };
        con.accept("Hello world");

        BinaryOperator<String> bina = (x, y) ->{System.out.print(x+" "+y);return "BinaryOperator";};
        System.out.println("**"+bina.apply("hello ","world"));
    }

    public static void main(String[] args) {
        System.out.println(applyExam(3));
        System.out.println(applyExam(30));
        System.out.println(composeExam("200"));
        System.out.println(andThenExam(10000));
        System.out.println(identityExam(50));
        System.out.println(decoratorExam(5,(a) -> a*5, (b) -> b+5));
        System.out.println(decoratorExam(5,(a) -> a+5, (b) -> b*4));

        List<Integer> list=new ArrayList<Integer>();
        for(int i=0;i<100;i++){
            list.add(Integer.valueOf(i));
        }

        //reduce repeat to execute function
        //重复执行result + element的操作
        System.out.println(list.stream().reduce(
                (result,element)-> result+element).orElseGet(()->100)
        );

        lamdabGrammerTest();
    }
}
