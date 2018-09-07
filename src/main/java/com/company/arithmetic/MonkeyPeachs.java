package com.company.arithmetic;

/**
 * Created by eli9 on 6/20/2017.
 */
public class MonkeyPeachs {
    /**
     * 五只猴子分桃。半夜，第一只猴子先起来，它把桃分成了相等的五堆，多出一只。于是，它吃掉了一个，
     * 拿走了一堆； 第二只猴子起来一看，只有四堆桃。于是把四堆合在一起，分成相等的五堆，又多出一个。
     * 于是，它也吃掉了一个，拿走了一堆；.....其他几只猴子也都是 这样分的。问：这堆桃至少有多少个？
     * http://blog.csdn.net/a542214712/article/details/8122871
     * @return peachs
     */
    public static void peachs() {
        int monkey = 1;//要分桃的猴子数
        int peaches = 1;//桃的总数
        int peach = 1;//每次分桃的总数
        //循环进行给每个猴子分桃
        while (monkey <= 5) {
            //如果成功分桃
            if (peach % 5 == 1 && peach / 5 != 0) {
                //可分桃的总数为现在的4/5
                peach = (peach / 5) * 4;
                //换下一个猴子
                monkey++;
            }
            //如果失败，重新分桃这时可分桃数量加1
            else {
                peaches++;
                peach = peaches;
                monkey = 1;
            }
        }
        System.out.println("桃的最小总数为：" + peaches);
    }

    public static void main(String[] args) {
        peachs();
    }
}
