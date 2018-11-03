package com.company.testCase;

import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import org.apache.xmlbeans.impl.regex.Match;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by atomic on 3/31/2017.\
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
//        ArrayTst.asList(r).stream().forEach(s -> System.out.println(s));
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

    @Test
    public void testRegular1(){
        List<String> paths = new ArrayList<>();
        paths.add("/Bond/CanadaToronto/CanadaPrice_20171228HHmmss_1.txt");
        paths.add("/Bond/MSE/MSEPrice.txt");
        paths.add("/Bond/V1.0/IssueCurrentPerformance_20171228HHmmss_1.txt");
        paths.add("/Daily/BAABond/BAAYield_20171228HHmmss_1.zip");
        paths.add("/Bond/CanadaToronto/CanadaPrice_20171228HHmmss_1.txt");
        paths.add("/Bond/Performance/BondMonthEndPerf_20171228HHmmss_1.zip");
        paths.add("/Bond/MedBank/MedBankBond_Price_20180102HHmmss_1.txt");

        Pattern pattern = Pattern.compile("HHmmss");
        String[] filePathPart = pattern.split("/Bond/CanadaToronto/CanadaPrice_20171228HHmmss_1.txt");
        Assert.assertEquals(2, filePathPart.length);

        Pattern pattern2 = Pattern.compile("^(/.*/)([a-zA-Z]+_\\d{8})HHmmss(_\\d+\\..*)$");
        Matcher matcher = pattern2.matcher(paths.get(0));
        matcher.matches();
        Assert.assertEquals(3, matcher.groupCount());
        String file_directory = matcher.group(1);
        Assert.assertEquals("/Bond/CanadaToronto/", file_directory);
        String file_fileName = matcher.group(2);
        Assert.assertEquals("CanadaPrice_20171228", file_fileName);
        String file_postfix = matcher.group(3);
        Assert.assertEquals("_1.txt", file_postfix);


    }
}
