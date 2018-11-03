package com.company.Lambda;

import com.company.Entity.Person;
import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.google.common.base.Predicates.equalTo;
import static java.util.stream.Collectors.toList;

/**
 * Created by atomic on 9/26/2017.
 */
public class LambdaTest2 {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>(){{
           this.add(1);
           this.add(2);
           this.add(3);
           this.add(4);
           this.add(5);
        }};
        System.out.println(multiplyThrough(list));
    }

    public static double[] parallelInitialize(int size){
        double[] values = new double[size];
        //根据下表计算元素的值，
        Arrays.parallelSetAll(values, x -> x+1);
        return values;
    }

    public static double[] simpleMovingAverage(double[] values, int n){
        double[] sums = Arrays.copyOf(values, values.length);
        //返回一个数组，里面是每个位置的sum和
        Arrays.parallelPrefix(sums, Double::sum);
        int start = n-1;
        return IntStream.range(start, sums.length)
                .mapToDouble(i -> {
                    double prefix = i == start ? 0 : sums[i - n];
                    return (sums[i] - prefix) / n;
                })
                .toArray();
    }

    //顺序求列表中数字的平方和
    public static int sequentialSumOfSquares(IntStream range) {
        return range.parallel()
                .map(x -> x * x)
                .sum();
    }

    //把列表中的数字相乘，然后再将所得结果乘以 5
    public static int multiplyThrough(List<Integer> arrayList) {
        // 并行reduce初始值必须为特定值，所以为了保持结果不变，把最终结果 * 5
        //之前调用 reduce 方法，初始值可以为任意值，为了让其在并行化时能工作正常，初值必须
        //为组合函数的恒等值。拿恒等值和其他值做 reduce 操作时，其他值保持不变。比如，使用
        //reduce 操作求和，组合函数为 (acc, element) -> acc + element ，则其初值必须为 0， 因
        //为任何数字加 0，值不变。
        return 5 * arrayList.parallelStream()
                .reduce(1,
                        (acc, x) -> x * acc);
    }

    // 高效并行计算
    public int fastSumOfSquares(List<Integer> linkedListOfNumbers) {
        //使用MapToInt和Sum方法
        return linkedListOfNumbers.parallelStream().mapToInt(x -> x * x).sum();
    }
    // 高效串行计算
    public int serialFastSumOfSquares(List<Integer> linkedListOfNumbers) {
        return linkedListOfNumbers.stream().mapToInt(x -> x * x).sum();
    }

    //java8流中所有的操作都是蓄而不发的，只有执行foreach，collect等终结操作时，流的操作才会执行。
    //而且流内部会自动进行优化，只要得到想要的解决就不会继续进行计算了。
    //peek是个中间操作，它提供了一种对流中所有元素操作的方法，而不会把这个流消费掉（foreach会把流消费掉），然后你可以继续对流进行其他操作。
    public void testPeek(){
        Stream.of("one", "two", "three", "four").peek(e -> System.out.println(e))
                .collect(toList());
    }

    @Test
    public void testSortByName_with_lambda() throws Exception {

        ArrayList<Person> humans = Lists.newArrayList(
                new Person("TP", 22),
                new Person("TD", 25),
                new Person("TR", 25),
                new Person("TA", 25)
        );
        System.out.println(humans.get(0).getName());
        humans.sort(Comparator.comparing(Person::getName));

        Assert.assertEquals("TA", humans.get(0).getName());
    }

}
