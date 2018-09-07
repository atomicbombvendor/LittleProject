package com.company.arithmetic.poke;

import org.junit.Assert;
import org.junit.Test;

public class PokeTest {

    @Test
    public void diviendPokeRule() {
        Poke.dividendPokeRule("89A354TKJQ");
    }

    @Test
    public void test(){
        int a = '2' - '1' + 'A';
        System.out.println((char)a);

        int b = "B".compareTo("A") + ((int)'1');
        System.out.println(String.valueOf((char)b));
    }


    @Test
    public void analyzePokes() {
        Poke.analyzePokes("89A354TKJQQQQQQQQ");
        Poke.printAllPokes();
        Assert.assertEquals(Poke.getAllPokes()[12][0].intValue(), 8);
    }
}