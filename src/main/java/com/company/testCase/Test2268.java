package com.company.testCase;

/**
 * Created by atomic on 3/27/2017.
 */
public class Test2268 {
    public static void doCalc(int input){
        int ans = 0;
        int remainder = 0;
        int half = (int)Math.sqrt(input);
        for(int i=2; i<= half; i++){
            remainder = input%i;
            if(remainder == 0){
                System.out.println((input/i)+ "*" +i+ "=" + input);
            }
        }
    }

    public static void main(String[] args) {
        doCalc(2268);
    }
}
