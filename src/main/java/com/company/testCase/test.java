package com.company.testCase;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

/**
 * Created by eli9 on 9/18/2017.
 */
public interface test {

    public default void welcome(){

    }

    public void message();

    public static void main(String[] args) {
        Collections.emptyMap();
        Arrays.asList(1);
    }
}
