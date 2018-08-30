package com.company.Arithmetic.Poke;

import java.util.Arrays;

public class Poke {


    /**
     * 分牌器，根据规则返回当前有多少种可能的出牌情况；
     * 规则：
     * 扑克牌出牌：
     * A23456789TJQK,
     * 可以出牌的类型: AAA3;KKKK22;45678;22;3
     * 输入一串字符串, 输出最快几次可以把牌跑完
     * @param pokes 要分配的牌
     * @return 出牌的规则
     */
    public static String[] diviendPokeRule(String pokes) {
        //先排序
        String[] pokeArr = transPoke(pokes);
        //在找
        return null;
    }

    /**
     * 把扑克牌转换对应的数字
     * A -> 1
     * T -> 10
     * J -> 11
     * Q -> 12
     * K -> 13
     */
    private static String[] transPoke(String pokes){
        char[] pokeArray = pokes.toCharArray();
        String[] pokeArrStr = new String[pokeArray.length];
        for (int i=0; i < pokeArray.length; i++){
            if ("A".equals(String.valueOf(pokeArray[i]))){
                pokeArrStr[i] = "1";
            }else if("T".equals(String.valueOf(pokeArray[i]))){
                pokeArrStr[i] = "10";
            }else if("J".equals(String.valueOf(pokeArray[i]))){
                pokeArrStr[i] = "11";
            }else if("Q".equals(String.valueOf(pokeArray[i]))){
                pokeArrStr[i] = "12";
            }else if("K".equals(String.valueOf(pokeArray[i]))){
                pokeArrStr[i] = "13";
            }else{
                pokeArrStr[i] = String.valueOf(pokeArray[i]);
            }
        }

        Arrays.sort(pokeArrStr);
        return pokeArrStr;
    }

    /**
     * 这个排序算法可以用Arrays.sort来做，使用了TimSort,是归并算法的版本;
     * 打算用归并排序来做
     */
    private static char[] orderArray(char[] pokes){
        return null;
    }

    private void resotrePoke(String[] pokes){
        for (int i=0; i< pokes.length; i++){
            if ("1".equals(pokes[i])){
                pokes[i] = "A";
            }else if("10".equals(pokes[i])){
                pokes[i] = "T";
            }else if("11".equals(pokes[i])){
                pokes[i] = "J";
            }else if("12".equals(pokes[i])){
                pokes[i] = "Q";
            }else if("K".equals(pokes[i])){
                pokes[i] = "K";
            }
        }
    }

    /**
     * 根据规则，找到当前牌中有多少种出牌情况
     * @param pokes 要分析的牌
     * @return 可能的出牌情况
     */
    private static String[][] playPokeCase(String[] pokes){
        return null;
    }
}
