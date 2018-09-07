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
    public static String[] dividendPokeRule(String pokes) {
        //先排序
        char[] pokeArray = pokes.toCharArray();
        Integer[] pokeArrStr = digitizePoke(pokeArray);
        printArray(pokeArrStr);

        Arrays.sort(pokeArray);
//        orderArray(pokeArrStr);

        printArray(pokeArrStr);
        //在找
        return null;
    }


    /**
     * 这个排序算法可以用Arrays.sort来做，使用了TimSort,是归并算法的版本;
     * 打算用归并排序来做
     */
    private static void orderArray(Integer[] pokes){
        Integer[] temp = new Integer[pokes.length];
        sort(pokes, 0, pokes.length-1, temp);
    }

    private static void sort(Integer[] pokes, int left, int right, Integer[] temp){
        if (left < right){
            int mid = (left + right)/2;
            sort(pokes, left, mid, temp);
            sort(pokes, mid+1, right, temp);
            merge(pokes, left, mid, right, temp);
        }
    }

    private static void merge(Integer[] pokes, int left, int mid, int right, Integer[] temp){
        int t = left; //用来标记放入temp的标记位
        int i = left;
        int j = mid+1;
        //交叉比较大小，并置入temp中相对于应的位置
        while( i<= mid && j <= right){
            if (pokes[i].compareTo(pokes[j]) < 0){
                temp[t++] = pokes[i++];
            }else {
                temp[t++] = pokes[j++];
            }
        }
        while(i <= mid){ //将左边剩余元素填充进temp中
            temp[t++] = pokes[i++];
        }
        while(j <= right){ //将右序列剩余元素填充进temp中
            temp[t++] = pokes[j++];
        }

        //把temp中的数据放入到原来的数组中
        t = left;
        while(left <= right){
            pokes[left++] = temp[t++];
        }
    }

    /**
     * 恢复扑克牌的 1=>A, 10=>T, 11=>J, 12=>Q, 13=>K
     */
    private static String[] resotrePoke(Integer[] pokes){
        String[] pokesStr = new String[pokes.length];
        for (int i=0; i< pokes.length; i++){
            if (1 == pokes[i]){
                pokesStr[i] = "A";
            }else if(10 == pokes[i]){
                pokesStr[i] = "T";
            }else if(11 == pokes[i]){
                pokesStr[i] = "J";
            }else if(12 == pokes[i]){
                pokesStr[i] = "Q";
            }else if(13 == pokes[i]){
                pokesStr[i] = "K";
            }else{
                pokesStr[i] = pokes[i].toString();
            }
        }
        return pokesStr;
    }

    /**
     * 把扑克牌转换对应的数字,并排序
     * A -> 1
     * T -> 10
     * J -> 11
     * Q -> 12
     * K -> 13
     * @param pokes 要替换的扑克牌
     */
    private static Integer[] digitizePoke(char[] pokes){
        Integer[] pokeArrStr = new Integer[pokes.length];
        for (int i=0; i < pokes.length; i++){
            if ("A".equals(String.valueOf(pokes[i]))){
                pokeArrStr[i] = 1;
            }else if("T".equals(String.valueOf(pokes[i]))){
                pokeArrStr[i] = 10;
            }else if("J".equals(String.valueOf(pokes[i]))){
                pokeArrStr[i] = 11;
            }else if("Q".equals(String.valueOf(pokes[i]))){
                pokeArrStr[i] = 12;
            }else if("K".equals(String.valueOf(pokes[i]))){
                pokeArrStr[i] = 13;
            }else{
                pokeArrStr[i] = Integer.valueOf(String.valueOf(pokes[i]));
            }
        }
        return pokeArrStr;
    }
    /**
     * 根据规则，找到当前牌中有多少种出牌情况
     * @param pokes 要分析的牌
     * @return 可能的出牌情况
     */
    private static String[][] playPokeCase(String[] pokes){
        return null;
    }

    private static String printArray(Integer[] source){
        StringBuilder sb = new StringBuilder();
        for (Integer s: source) {
            sb.append(s);
            System.out.print(" " + s);
        }
        System.out.println("\n");
        return sb.toString();
    }
}
