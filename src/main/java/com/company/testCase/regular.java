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

    public static void getResult1(){
        String filePath = "test\\test\\test\\0C00001RHP.xml";
        Pattern pattern = Pattern.compile("0C.{8}");
        Matcher matcher = pattern.matcher(filePath);
        while(matcher.find()){
            System.out.println(matcher.group());
        }
        System.out.println(filePath.substring(filePath.length()-14,filePath.length()-4));
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
//        String[] r = getArraysByEnterOrSpace();
//        Arrays.asList(r).stream().forEach(s -> System.out.println(s));
//        testStringFormat();
        getResult1();
    }

    public static void testStringFormat(){
        String format = "test%stest%s";
        String s1 ="123";
        String[] s2 = new String[]{"ppp","ddd"};
        String result = String.format(format,s1,Arrays.toString(s2));
        System.out.println(result); // StringFormat怎么拼接数组
    }
}
