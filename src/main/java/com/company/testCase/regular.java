package com.company.testCase;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by eli9 on 3/31/2017.
 */
public class regular {
    public static void getResult(){
        String strAndNum = "ReferenceId=201703302050";
        String regex = "\\d*";//"[^0-9]"; //"(\\d+)";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(strAndNum);
        if(m.find()) {
            System.out.println(m.group(0).trim());
        }
    }

    public static String[] getArraysByEnterOrSpace(){
        String str = "EX$$$$XNAS\n" +
                "EX$$$$XNYS\n" +
                "EX$$$$XASE\n" +
                "EX$$$$PINX\n" +
                "EX$$$$XOTC\n" +
                "EX$$$$ARCX\n" +
                "EX$$$$USIN\n" +
                "EX$$$$0001      EX$$$$BATS";
        System.out.println("Value:\n"+str+"\n-------");
        String[] result = str.split("\n");
        return result;
    }

    public static void main(String[] args) {
        //getResult();
        String[] r = getArraysByEnterOrSpace();
        Arrays.asList(r).stream().forEach(s -> System.out.println(s));
    }
}
