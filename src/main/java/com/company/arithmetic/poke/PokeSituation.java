package com.company.arithmetic.poke;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class PokeSitution {

    /**
     * 分析输入的牌，得到顺子的情况
     * @return 当前牌的所有可能的顺子
     */
    public static List<List<Integer>> getStraight(Integer[][] pokes){

        List<List<Integer>> allStraight = new LinkedList<>();

        //顺子最大的开始位置是9； 9 T J Q K
        int maxIndex = 9;
        for (int i=1; i<=maxIndex; i++){
            int straightLength = 0;
            LinkedList<Integer> straight = new LinkedList<>();
            for (int j=i; j<=pokes.length-1; j++){
                //没有遇到中断（当前牌的数量为0）
                if (pokes[j][0] > 0){
                    straightLength ++;
                    straight.add(j);
                    //当顺子的长度达到5时，每次顺子长度加1，就把当前顺子加入总的顺子列表中
                    if (straightLength >= 5) {
                        allStraight.add((LinkedList<Integer>) straight.clone());
                    }
                }else{
                    //遇到中断，应该退出J循环，然后把当前长度大于4的加入顺子
                    if (straightLength >= 5) {
                        //不存在的时候，才可以添加。因为存在重复添加的情况
                        if (!allStraight.contains(straight)) {
                            allStraight.add(straight);
                        }
                    }
                    break;
                }
            }
        }
        return allStraight;
    }

}
