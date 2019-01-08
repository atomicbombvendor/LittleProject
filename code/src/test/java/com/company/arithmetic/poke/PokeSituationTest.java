package com.company.arithmetic.poke;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class PokeSituationTest {

    private Poke poke = new Poke();

    @Before
    public void runPoke(){
        poke.analyzePokes("A23456669JTTJQKKKK");
        poke.printAllPokes();
    }

    @Test
    public void getAllSituation() {
        List<List<Integer>> allStituation = PokeSituation.getAllSituation(poke.getAllPokes());
        print(allStituation);

    }

    @Test
    public void getAllStraight() {
        List<List<Integer>> straights = PokeSituation.getAllStraight(poke.getAllPokes());
        print(straights);
        Assert.assertEquals(4, straights.size());
    }

    @Test
    public void getAllPairs() {
        List<List<Integer>> straights = PokeSituation.getAllPairs(poke.getAllPokes());
        print(straights);
        Assert.assertEquals(3, straights.size());
    }

    @Test
    public void getAllMGenN() {
        List<List<Integer>> fourWithOne = PokeSituation.getAllMGenN(poke.getAllPokes(), 4, 1);
        print(fourWithOne);
        Assert.assertEquals(0, fourWithOne.size());

        List<List<Integer>> fourWithTow = PokeSituation.getAllMGenN(poke.getAllPokes(), 4, 2);
        print(fourWithTow);
        Assert.assertEquals(0, fourWithTow.size());

        List<List<Integer>> ThreeWithOne = PokeSituation.getAllMGenN(poke.getAllPokes(), 3, 1);
        print(ThreeWithOne);
        Assert.assertEquals(10, ThreeWithOne.size());

        List<List<Integer>> ThreeWithZero = PokeSituation.getAllMGenN(poke.getAllPokes(), 3, 0);
        print(ThreeWithZero);
        Assert.assertEquals(1, ThreeWithZero.size());
    }

    @Test
    public void getAllSingle() {
        List<List<Integer>> straights = PokeSituation.getAllSingle(poke.getAllPokes());
        Assert.assertEquals(11, straights.size());
    }

    private void print(List<List<Integer>> lll){
        for (List<Integer> ll : lll){
            System.out.print("s>>");
            for (Integer l : ll){
                System.out.print(l);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}