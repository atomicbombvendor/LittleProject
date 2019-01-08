package com.company.RandomNumber;

import java.util.Arrays;
import java.util.Random;

import static java.util.Arrays.asList;

/**
 * Created by ZZ on 2017/4/11.
 */
public class RandomNum {

    public static void main(String[] args) {
        try {
            System.out.println(getRandom1To10ThrowException());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static int getRandom1To10(){
        Random random = new Random();
        int result = random.nextInt(10);// nextInt 0~n
        return result+1;
    }

    public static int getRandom1To10ThrowException() throws Exception {
        String n = String.valueOf(getRandom1To10());
        String[] arr = new String[]{"2","4"};
        String[] arr2 = new String[]{"11","12","13"};
        System.out.println("random is "+n);
        if(asList(arr).contains(n)){
            return 200;
        }else{
            throw new Exception("Random is not in"+arr.toString());
        }
    }

    public static void getRandom1To10ThrowExceptionNoReturn() throws Exception {
        String n = String.valueOf(getRandom1To10());
        String[] arr = new String[]{"2","4"};
        String[] arr2 = new String[]{"11","12","13"};
        System.out.println("random is "+n);
        if(asList(arr).contains(n)){
            System.out.println("200 OK");
        }else{
            throw new RuntimeException("Random is not in"+arr.toString());
        }
    }
}
