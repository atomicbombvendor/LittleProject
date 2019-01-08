package com.company.Lambda;

import com.company.Entity.Person;
import junit.framework.Assert;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.company.Lambda.BasicCalc.divide;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.*;

public class LambdaTest {
    static int i;
    public static Function<Double, Function<Double, Double>> division = first ->
            second -> {
                Double result = null;
                if (Objects.nonNull(first) && Objects.nonNull(second)) {
                    //divide实现的是 UnaryOperator<Double> interface
                    // UnaryOperator extends from function, so we must use function apply
                    result = divide(second).apply(first);
                }
                return result;
            };

    public static void main(String[] args) {
//        Function<Double,Double> t = division.apply(2.3);
//        System.out.println(t.apply(2.6));
//        System.out.println(i);
        testT1();
    }

    public static void collectT(){
        List<String> collected = Stream.of("a","b","c").collect(toList());

        //Map 将对流操作后的结果当做流返回。
        List<String> c2 =
                Stream.of("a","b", "Hello").map(String::toUpperCase).collect(toList());

        List<String> c3 =
                Stream.of("a","b", "Hello").filter(v -> v.length() > 0).collect(toList());

        List<Integer> c4 =
                Stream.of(asList(1,2), asList(3,4))
                .flatMap(Collection::stream).collect(toList());
        Assert.assertEquals(asList(1, 2, 3, 4), c4);

        List<String> s1 = asList("A", "B", "C");
        String r1 = s1.stream().min(Comparator.comparing(String::length)).get();

        Integer reduce = Stream.of(1, 2, 3).reduce(0, (acc, e) -> (acc + e));
    }

    public static void testT1(){
        List<Integer> is = new ArrayList<>();
        Integer count = is.stream().reduce(0, (a, b) -> a+b);

        //flatMap 方法可用 Stream 替换值，然后将多个 Stream 连接成一个 Stream
        Integer count2 = Stream.of(is).flatMap(x -> x.stream()).reduce(0, (a,b) -> a+b);

        //计算一个字符串中小写字母的个数（提示：参阅 String 对象的 chars 方法）。
        String testString = "AbdsAAAA"; String testString2 = "BDd"; String testString3 = "dmaduelsd";
        testString.chars()
                .filter(Character::isLowerCase)
                .toArray();
        System.out.println(count);

        //在一个字符串列表中，找出包含最多小写字母的字符串。对于空列表，返回 Optional<String> 对象。
        String result = Stream.of(testString, testString2, testString3)
                .min(Comparator.comparing( s -> s.chars().filter(Character::isLowerCase).count())).get();

        List<Person> ps = new ArrayList<>();
        ps.stream().map(p -> p.getName())
                .collect(Collectors.joining("","",""));

        ps.stream().collect(groupingBy(p -> p.getName(), counting()));

        //下游收集器
        ps.stream().collect(groupingBy(Person::getName, mapping(Person::getName, toList())));

        //Test Comparable -> Comparator
        List<Person> pss = new ArrayList<Person>(){{
            this.add(new Person("xiaoming5",25));
            this.add(new Person("xiaoming4",24));
            this.add(new Person("xiaoming3",23));
            this.add(new Person("xiaoming2",22));
            this.add(new Person("xiaoming",21));
        }};

        pss.forEach(p -> System.out.println(p.toString()));
        System.out.println("---");
        pss.stream().sorted(Comparator.comparing(Person::getAge))
                .forEach(p -> System.out.println(p.toString()));
        System.out.println("Comparator comparing sorted 是 升序排列");
    }
}

