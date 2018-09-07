package com.company.arithmetic.poke;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author ZZ
 */
public class PokeSituation {

    /**
     * 得到所有的情况：
     * 单张，对子，三带一，三不带，四带二，四不带，长度大于等于5的顺子
     */
    public static List<List<Integer>> getAllSituation(Integer[][] pokes){
        List<List<Integer>> allSituation = new LinkedList<>();
        // 对子
        allSituation.addAll(getAllPairs(pokes));
        // 顺子
        allSituation.addAll(getAllStraight(pokes));
        // 四带二
        allSituation.addAll(getAllMGenN(pokes, 4, 2));
        // 三带一
        allSituation.addAll(getAllMGenN(pokes, 3, 1));
        // 四不带
        allSituation.addAll(getAllMGenN(pokes, 4, 0));
        // 三不带
        allSituation.addAll(getAllMGenN(pokes, 3, 0));
        // 单张
        allSituation.addAll(getAllSingle(pokes));

        return allSituation;
    }

    /**
     * 分析输入的牌，得到顺子的情况
     * @return 当前牌的所有可能的顺子
     */
    public static List<List<Integer>> getAllStraight(Integer[][] pokes){

        List<List<Integer>> allStraight = new LinkedList<>();

        //顺子最大的开始位置是9； 9 T J Q K
        int maxIndex = 9;
        //最短的顺子长度
        int minLength = 5;

        for (int i=1; i<=maxIndex; i++){
            int straightLength = 0;
            LinkedList<Integer> straight = new LinkedList<>();
            for (int j=i; j<=pokes.length-1; j++){
                //没有遇到中断（当前牌的数量为0）
                if (pokes[j][0] > 0){
                    straightLength ++;
                    straight.add(j);
                    //当顺子的长度达到5时，每次顺子长度加1，就把当前顺子加入总的顺子列表中
                    if (straightLength >= minLength) {
                        allStraight.add((LinkedList<Integer>) straight.clone());
                    }
                }else{
                    //遇到中断，应该退出J循环，然后把当前长度大于4的加入顺子
                    if (straightLength >= minLength) {
                        //不存在的时候，才可以添加。因为存在重复添加的情况
                        if (!allStraight.contains(straight)) {
                            allStraight.add(straight);
                        }
                    }else{
                        // 长度不足5，就清空
                        straight.clear();
                    }
                    break;
                }
            }
        }
        return allStraight;
    }

    /**
     * 得到所有的对子
     */
    public static List<List<Integer>> getAllPairs(Integer[][] pokes){
        int pairCount = 2;
        List<List<Integer>> allPairs = new LinkedList<>();
        for(int i=1; i<=pokes.length-1; i++){
            List<Integer> pair = new LinkedList<>();
            if (pokes[i][0] >= pairCount){
                pair.add(i);
                pair.add(i);
            }
            if (pair.size() == pairCount){
                allPairs.add(pair);
            }else{
                pair.clear();
            }
        }
        return allPairs;
    }

    /**
     * 找到所有的 N带M；限定为四带二，四不带，三带一，四不带
     * @param pokes 输入的牌
     * @param duplicatePokeNum 三带一中的三， 四带二中的四，三个不带，四个不带;也可能四带对子
     * @param withPokeNum 三带一中的一， 四带二中的二
     */
    public static List<List<Integer>> getAllMGenN(Integer[][] pokes, int duplicatePokeNum, int withPokeNum) {

        int masterFour = 4;
        int masterFourWithPoke = 2;
        int masterThree = 3;
        int masterThreeWithPoke = 1;
        // 限定为四带二，四不带，三带一，四不带
        boolean isLegal =
                (duplicatePokeNum == masterFour && withPokeNum == masterFourWithPoke) ||
                        (duplicatePokeNum == masterThree && withPokeNum == masterThreeWithPoke) ||
                        (duplicatePokeNum == masterFour && withPokeNum == 0) ||
                        (duplicatePokeNum == masterThree && withPokeNum == 0);
        if (!isLegal){
            System.out.println("Must be four with tow OR three with one OR Four With Zero OR Three With Zero");
        }

        List<List<Integer>> allDuplicatePokeGenPoke = new LinkedList<>();
        for (int i = 1; i <= pokes.length - 1; i++) {
            if (pokes[i][0] >= duplicatePokeNum) {
                // 找到N张
                LinkedList<Integer> duplicatePokes = new LinkedList<>(Arrays.asList(i, i, i));
                // 带M的所有可能情况
                // 什么都不带的情况
                if (withPokeNum == 0){
                    allDuplicatePokeGenPoke.add(duplicatePokes);
                }else {
                    for (int j = 1; j <= pokes.length - 1; j++) {
                        if (j != i && pokes[j][0] >= withPokeNum) {
                            List<List<Integer>> withPokes = getAllWithPokeSituation(pokes, i, withPokeNum);
                            withPokes.stream().forEach(r -> {
                                LinkedList duplicatePokesClone = (LinkedList<Integer>) duplicatePokes.clone();
                                duplicatePokesClone.addAll(r);
                                allDuplicatePokeGenPoke.add(duplicatePokesClone);
                            });
                        }
                    }
                }
            }
        }
        return allDuplicatePokeGenPoke;
    }

    /**
     * 得到所有带牌的情况，包括对子
     * 比如 四带一对
     * @param pokes 所有的扑克牌
     * @param index 当前主牌的索引
     * @param withPokeNum 要带牌的数量
     */
    private static List<List<Integer>> getAllWithPokeSituation(Integer[][] pokes, int index, int withPokeNum) {
        int masterFourWithPoke = 2;
        int masterThreeWithPoke = 1;

        List<List<Integer>> allWithPokes = new LinkedList<>();

        // 带一
        if (withPokeNum == masterThreeWithPoke) {
            for (int i = 1; i <= pokes.length - 1; i++) {
                if (pokes[i][0] >= masterThreeWithPoke) {
                    allWithPokes.add(Arrays.asList(i));
                }
            }
            // 带二
            if (withPokeNum == masterFourWithPoke) {
                for (int i = 1; i <= pokes.length - 1; i++) {
                    if (i != index && pokes[i].length > 0) {
                        // 如果当前位置的长度大于等于2个
                        if (pokes[i].length >= masterFourWithPoke) {
                            allWithPokes.add(Arrays.asList(i, i));
                        }
                        // 只考虑每个取一个的情况；从i+1开始查找，直到找到结尾
                        for (int j = i + 1; j <= pokes.length - 1; j++) {
                            // 当前位置有大于0个,表示可取
                            if (pokes[j][0] > 0) {
                                allWithPokes.add(Arrays.asList(i, j));
                            }
                        }
                    }
                }
            }
        }
        return allWithPokes;
    }

    /**
     * 得到所有的单张
     */
    public static List<List<Integer>> getAllSingle(Integer[][] pokes){
        List<List<Integer>> allSinglePoke = new LinkedList<>();
        for (int i = 1; i <= pokes.length - 1; i++) {
            if (pokes[i][0] > 0){
                allSinglePoke.add(Arrays.asList(i));
            }
        }
        return allSinglePoke;
    }
}
