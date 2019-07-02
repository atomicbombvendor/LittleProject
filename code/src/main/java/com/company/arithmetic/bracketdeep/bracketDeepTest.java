package com.company.arithmetic.bracketdeep;

public class bracketDeepTest {

    public static void main(String[] args) {
        String in = "()()()";
        int cnt = 0, max = 0, i;
        for (i = 0; i < in.length(); ++i) {
            if (in.charAt(i) == '(')
                cnt++;
            else
                cnt--;
            max = Math.max(max, cnt);
        }
        System.out.println(max);
    }
}
