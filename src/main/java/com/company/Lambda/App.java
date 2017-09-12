package com.company.Lambda;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.HashSet;

/**
 * Created by eli9 on 3/15/2017.
 */
public class App {

    public static void main(String[] args) {
        System.out.println(" : " + testComplier("Hello"));

    }

    public static String testComplier(String value){
        System.out.println(value);
        try {
            byte[] byteTsoSource = "20111010".getBytes("UTF-8");
            System.out.println(byteTsoSource.length);//=8
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "World";
    }
}
