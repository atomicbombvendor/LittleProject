package com.company.arithmetic.poke;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class PokeSituationTest {

    @Before
    public void runPoke(){
        Poke.analyzePokes("A23456669JTTJQKKKK");
        Poke.printAllPokes();
    }

    @Test
    public void getAllSituation() {
    }

    @Test
    public void getAllStraight() {
        List<List<Integer>> straights = PokeSituation.getAllStraight(Poke.getAllPokes());
        Assert.assertEquals(4, straights.size());
    }

    @Test
    public void getAllPairs() {
        List<List<Integer>> straights = PokeSituation.getAllPairs(Poke.getAllPokes());
        Assert.assertEquals(3, straights.size());
    }

    @Test
    public void getAllMGenN() {
        List<List<Integer>> fourWithOne = PokeSituation.getAllMGenN(Poke.getAllPokes(), 4, 1);
        Assert.assertEquals(0, fourWithOne.size());

        List<List<Integer>> fourWithTow = PokeSituation.getAllMGenN(Poke.getAllPokes(), 4, 2);
        Assert.assertEquals(0, fourWithTow.size());

        List<List<Integer>> ThreeWithOne = PokeSituation.getAllMGenN(Poke.getAllPokes(), 3, 1);
        Assert.assertEquals(10, ThreeWithOne.size());

        List<List<Integer>> ThreeWithZero = PokeSituation.getAllMGenN(Poke.getAllPokes(), 3, 0);
        Assert.assertEquals(1, ThreeWithZero.size());
    }

    @Test
    public void getAllSingle() {
        List<List<Integer>> straights = PokeSituation.getAllSingle(Poke.getAllPokes());
        Assert.assertEquals(11, straights.size());
    }
}