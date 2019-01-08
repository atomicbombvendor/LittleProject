package com.company.utils;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

/**
 * Created by eli9 on 6/5/2017.
 */
public class StringUtilTest {
    @Test
    public void coalesceReplace() throws Exception {
        String value = "A.Id Id, B.Name Name, A.Sex Sex";
        String reg = "(A|B)\\.\\w+ ";
        String result = StringUtil.coalesceReplace(reg, value);
        System.out.println(result);
    }

}