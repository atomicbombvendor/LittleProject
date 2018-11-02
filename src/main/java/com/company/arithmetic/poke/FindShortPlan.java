package com.company.arithmetic.poke;

import java.util.Arrays;
import java.util.List;

public class FindShortPlan {

    private static Poke poke = new Poke();
    /**
     * 查找最短的出牌方式
     */
    public static TreeNode<List<Integer>> findShortPlan(String pokes) {
        poke.analyzePokes(pokes);
        poke.printAllPokes();

        List<List<Integer>> allSitutions = PokeSituation.getAllSituation(poke.getAllPokes());
        int currentPokeLength = pokes.length();
        TreeNode<List<Integer>> tree = new TreeNode<>(Arrays.asList(Integer.valueOf(0)));
        find1(allSitutions, currentPokeLength, poke.getAllPokes(), tree);
        return tree;
    }

    /**
     * 当currentPokes=0时，表示已经找到最短的出牌路径了，可以返回。每次出牌，累加当前已经出过的牌，传入没有出过的牌。
     */
    private static void find1(List<List<Integer>> al, int currentPokeLength, Integer[][] cuurentPoke,
                              TreeNode<List<Integer>> parent){
        if (currentPokeLength == 0){
            return;
        }

        // List<Integer>是一种出牌情形
        for (List<Integer> ll : al){
            TreeNode<List<Integer>> child = new TreeNode<>(ll);
            child.setParent(parent);
            parent.addChild(child);

            currentPokeLength -= ll.size();
            Integer[][] currentPokesT = poke.subCurrentPoke(ll, cuurentPoke);
            List<List<Integer>> alT = PokeSituation.getAllSituation(currentPokesT);
            find1(alT, currentPokeLength, currentPokesT, child);
        }
    }


}
