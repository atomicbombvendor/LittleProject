package com.company;

import org.junit.Assert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZZ on 2017/6/18.
 */
public class TempTest {

    public static void main(String[] args) {
        List<String> ss = new ArrayList<>(10);
        Assert.assertTrue(ss.isEmpty());

        List<String> ss2 = null;
        Assert.assertTrue(ss2.isEmpty());
    }

}
