package com.company.arithmetic.poke;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author ZZ
 */
public class Poke {

    private static final List<Character> charsShouldBound = Arrays.asList('A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q',
            'K');

    /**
     * 这是定义好的存储扑克牌结构的数组；每一个Poke的数字值对应数组的Index.
     * 比如：A的数字值是1，对应于数组中的Value1 = AllPokes[1]
     * Value1是当前牌A的数量
     */
    private Integer[][] AllPokes = new Integer[14][1];

    public Integer[][] getAllPokes() {
        return AllPokes;
    }

    /**
     * 填充AllPokes
     */
    public void analyzePokes(String pokes){
        System.out.print("You pokes are ");
        printArray(pokes.toCharArray());

        System.out.println("Analyze Pokes....");
        //先排序
        Integer[] pokeIntStr = transIntPoke(pokes.toCharArray());

        for (Integer poke : pokeIntStr){
            AllPokes[poke][0] = Objects.isNull(AllPokes[poke][0])? 1 : AllPokes[poke][0] + 1;
        }
        Arrays.stream(AllPokes).forEach(row -> {
            if (Objects.isNull(row[0])){
                row[0] = 0;
            }
        });
    }

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
    public String[] dividendPokeRule(String pokes) {
        System.out.print("You pokes are ");
        printArray(pokes.toCharArray());
        //先排序
        Integer[] pokeArrStr = transIntPoke(pokes.toCharArray());

        System.out.print("Do Trans");
        printArray(pokeArrStr);

        Arrays.sort(pokeArrStr);
        // 这一步是自己写的排序算法
        //orderArray(pokeArrStr);
        System.out.print("Do Sort");
        printArray(pokeArrStr);

        restorePoke(pokeArrStr);
        System.out.print("Do Restore");
        printArray(pokeArrStr);
        //在找
        return null;
    }

    // region 归并排序算法
    /**
     * 这个排序算法可以用Arrays.sort来做，使用了TimSort,是归并算法的版本;
     * 打算用归并排序来做
     */
    private void orderArray(Integer[] pokes){
        Integer[] temp = new Integer[pokes.length];
        sort(pokes, 0, pokes.length-1, temp);
    }

    private void sort(Integer[] pokes, int left, int right, Integer[] temp){
        if (left < right){
            int mid = (left + right)/2;
            sort(pokes, left, mid, temp);
            sort(pokes, mid+1, right, temp);
            merge(pokes, left, mid, right, temp);
        }
    }

    private void merge(Integer[] pokes, int left, int mid, int right, Integer[] temp){
        //用来标记放入temp的标记位
        int t = left;
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
        //将左边剩余元素填充进temp中
        while(i <= mid){
            temp[t++] = pokes[i++];
        }
        //将右序列剩余元素填充进temp中
        while(j <= right){
            temp[t++] = pokes[j++];
        }

        //把temp中的数据放入到原来的数组中
        t = left;
        while(left <= right){
            pokes[left++] = temp[t++];
        }
    }

    // endregion代码部分，接口是orderArr j
    /**
     * 恢复扑克牌的 1=>A, 10=>T, 11=>J, 12=>Q, 13=>K
     */
    private String[] restorePoke(Integer[] pokes){
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
     * 恢复扑克牌
     * A=>A B=>2 C=>3 D=>4 E=>5 F=>6 G=>7 H=>8 I=>9
     * J=>T K=>J L=>Q M=>k
     */
    private void restorePoke(String[] pokes){
        String[] tmp = new String[pokes.length];
        for (int i=0; i< pokes.length; i++){
            if ("A".equals(String.valueOf(pokes[i]))) {
                tmp[i] = "A";
            } else if ("J".equals(String.valueOf(pokes[i]))) {
                tmp[i] = "T";
            } else if ("K".equals(String.valueOf(pokes[i]))) {
                tmp[i] = "J";
            } else if ("L".equals(String.valueOf(pokes[i]))) {
                tmp[i] = "Q";
            } else if ("M".equals(String.valueOf(pokes[i]))) {
                tmp[i] = "K";
            } else {
                // 和 字符"A"比较大小，绝对应该是数字几。然后和字符'1'的ascii的值相加得到该数字的ascii int值
                // 比如： "B"对应数字字符"2", "B"-"A"=1，表示"B"比字符"1"大了1;加上'1'的ascii的值，就得到"B"字符的ascii的值
                // 然后转为Char，再转为String
                tmp[i] = String.valueOf((char)(pokes[i].compareTo("A") + ((int)'1')));
            }
        }
        System.arraycopy(tmp, 0, pokes, 0, pokes.length);
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
    private Integer[] transIntPoke(char[] pokes){
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
     * A  2  3  4  5  6  7  8  9   T   J   Q   K
     * A1 B2 C3 D4 E5 F6 G7 H8 I9 J10 K11 L12 M13
     * 把扑克牌的所有按照字母顺序转换，方便用正则表达式查找顺子，三带一，四代二，对子等等。
     * @param pokes 未转换的扑克牌
     * @return 转换后的扑克牌
     */
    private String[] transStrPoke(char[] pokes){
        String[] pokeArrStr = new String[pokes.length];
        for (int i=0; i < pokes.length; i++){
            if (charsShouldBound.contains(pokes[i])) {
                if ("A".equals(String.valueOf(pokes[i]))) {
                    pokeArrStr[i] = "A";
                } else if ("T".equals(String.valueOf(pokes[i]))) {
                    pokeArrStr[i] = "J";
                } else if ("J".equals(String.valueOf(pokes[i]))) {
                    pokeArrStr[i] = "K";
                } else if ("Q".equals(String.valueOf(pokes[i]))) {
                    pokeArrStr[i] = "L";
                } else if ("K".equals(String.valueOf(pokes[i]))) {
                    pokeArrStr[i] = "M";
                } else {
                    pokeArrStr[i] = String.valueOf((char)(pokes[i] - '1' + 'A'));
                }
            }else{
                throw new RuntimeException("错误：输入了非法字符>>> " + pokes[i]);
            }
        }
        return pokeArrStr;
    }

    /**
     * 根据规则，找到当前牌中有多少种出牌情况
     * @param pokes 要分析的牌
     * @return 可能的出牌情况
     */
    private String[][] playPokeCase(String[] pokes){
        return null;
    }

    private String printArray(Integer[] source){
        StringBuilder sb = new StringBuilder();
        for (Integer s: source) {
            sb.append(s);
            System.out.print(" " + s);
        }
        System.out.println("\n");
        return sb.toString();
    }

    private String printArray(String[] source){
        StringBuilder sb = new StringBuilder();
        for (String s: source) {
            sb.append(s);
            System.out.print(" " + s);
        }
        System.out.println("\n");
        return sb.toString();
    }

    private String printArray(char[] source){
        StringBuilder sb = new StringBuilder();
        for (char s: source) {
            sb.append(s);
            System.out.print(" " + s);
        }
        System.out.println("\n");
        return sb.toString();
    }

    public void printAllPokes(){
        Integer[][] tmp = getAllPokes();
        for (int i=1; i< tmp.length; i++){
            System.out.println("Poke " + getPoke(i) + " have " + tmp[i][0]);
        }
    }

    public Integer[][] subCurrentPoke(List<Integer> pokes, Integer[][] allPokes){
        for (Integer p : pokes){
            allPokes[p][0] = allPokes[p][0]-1;
        }
        return allPokes;
    }

    private String getPoke(Integer pokeInt) {
        if (pokeInt <= 9 && pokeInt >= 2) {
            return pokeInt.toString();
        } else {
            switch (pokeInt) {
                case 1:
                    return "A";
                case 10:
                    return "T";
                case 11:
                    return "J";
                case 12:
                    return "Q";
                case 13:
                    return "K";
                default:
                    System.out.println("Error Poke>>> " + pokeInt);
                    return "*";
            }
        }
    }
}
