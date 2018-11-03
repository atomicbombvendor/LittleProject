package com.company.testCase;

import java.util.HashMap;
import java.util.Hashtable;

/**
 * Created by atomic on 7/5/2017.
 */
public class test1 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Test test = null;
        test.hello();

        String s="hello";
        String t="hello";
        char c[]={'h','e','l','l','o'};

        System.out.println(s.equals(c)+""+(s==t));

        HashMap a = new HashMap();
        Hashtable b = new Hashtable();
    }
}

class Test {
    public static void hello() {
        System.out.println("hello");
    }
}