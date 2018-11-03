package com.company.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by atomic on 6/5/2017.
 */
public class StringUtil {
    /**
     * 查找Value中符合正则表达式的字符，进行替换操作。
     * 比如： [A.Id ]-> [coalesce(A.Id, B.Id) ]
     * 这个方法用来实现coalesce的替换功能。
     * @param reg 正则表达式
     */
    public static String coalesceReplace(String reg, String value){
        StringBuilder copy = new StringBuilder(value);
        String format = "coalesce(A.%s, B.%s) ";
        Pattern p = Pattern.compile(reg);
        Matcher matcher = p.matcher(value);
        while(matcher.find()){
            StringBuilder sb = new StringBuilder(matcher.group().substring(matcher.group().indexOf(".")+1).trim());
            System.out.println(matcher.group()+" "+String.format(format,sb,sb));
            value = value.replaceAll(matcher.group(), String.format(format,sb,sb));
        }
        return value;
    }
}
