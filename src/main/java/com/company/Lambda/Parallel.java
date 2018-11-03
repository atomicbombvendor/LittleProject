package com.company.Lambda;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.summingDouble;

/**
 * Created by atomic on 9/25/2017.
 */
public class Parallel {

    public static void main(String[] args) {

        Parallel parallel = new Parallel();
        Map<Integer, Double> DiceRollsResult = parallel.parallelDiceRolls(100);
        DiceRollsResult.forEach(
                (k, v) -> {
                    System.out.println("Key->" + k + " v->" + v);
                }
        );

        System.out.println("\nFrequency -> ");
        parallel.parallelDiceRollsForLong(100)
                .forEach((k, v) -> {
                    System.out.println("Key->" + k + " v->" + v);
                });
    }

    public Map<Integer, Double> parallelDiceRolls(int N){
        double fraction = 1.0 / N; //每出现一次，概率增加的单位

        return IntStream.range(0, N) //1
                .parallel() //2
                .mapToObj(x -> twoDiceThrows()) //3
                .collect(Collectors.groupingBy(side -> side, //4
                        summingDouble(n -> fraction))); //5 n是side出现的次数
                        //Collectors.counting())));

        // N 代表模拟次数，在➊处使用 IntStream
        //的 range 方法创建大小为 N 的流，在➋处调用 parallel 方法使用流的并行化操作，
        //twoDiceThrows 函数模拟了连续掷两次骰子事件，返回值是两次点数之和。在➌处使用
        //mapToObj 方法以便在流上使用该函数。
        //在➍处得到了需要合并的所有结果的流，使用前一章介绍的 groupingBy 方法将点数一样
        //的结果合并。我说过要计算每个点数的出现次数，然后除以总的模拟次数 N 。在流框架中，
        //将数字映射为 1/N 并且相加很简单，这和前面说的计算方法是等价的。在➎处我们使用
        //summingDouble 方法完成了这一步。最终的返回值类型是 Map<Integer, Double> ，是点数之
        //和到它们的概率的映射。
    }

    public Map<Integer, Long> parallelDiceRollsForLong(int N){

        return IntStream.range(0, N) //1
                .parallel() //2
                .mapToObj(x -> twoDiceThrows()) //3
                .collect(Collectors.groupingBy(side -> side, //4
                         Collectors.counting()));

        // N 代表模拟次数，在➊处使用 IntStream
        //的 range 方法创建大小为 N 的流，在➋处调用 parallel 方法使用流的并行化操作，
        //twoDiceThrows 函数模拟了连续掷两次骰子事件，返回值是两次点数之和。在➌处使用
        //mapToObj 方法以便在流上使用该函数。
        //在➍处得到了需要合并的所有结果的流，使用前一章介绍的 groupingBy 方法将点数一样
        //的结果合并。我说过要计算每个点数的出现次数，然后除以总的模拟次数 N 。在流框架中，
        //将数字映射为 1/N 并且相加很简单，这和前面说的计算方法是等价的。在➎处我们使用
        //summingDouble 方法完成了这一步。最终的返回值类型是 Map<Integer, Double> ，是点数之
        //和到它们的概率的映射。
    }

    //返回值是两次点数之和
    private int twoDiceThrows(){
        Random random = new Random();
        int r1 = random.nextInt(6) + 1; //generate 0<=x<5
        int r2 = random.nextInt(6) + 1; //generate 0<=x<5
        return r1 + r2;
    }
}
