package com.company.Lambda;

/**
 * Created by eli9 on 3/15/2017.
 */
public class App {
    public static void main(String[] args) {
        System.out.println(" : " + testComplier("Hello"));
    }

    public static String testComplier(String value){
        System.out.print(value);
        return "World";
    }
}
