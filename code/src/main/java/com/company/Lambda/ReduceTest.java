package com.company.Lambda;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by eli9 on 9/25/2017.
 */
public class ReduceTest {

    public static void main(String[] args) throws Exception{
        List<Foo> fooList = Lists.newArrayList(
                new Foo("A","san",1.0,2),
                new Foo("A","nas",13.0,1),
                new Foo("B","san",112.0,3),
                new Foo("C","san",43.0,5),
                new Foo("B","nas",77.0,7)
        );
        List<Bar> barList = Lists.newArrayList();
        fooList
                .stream()
                .collect(Collectors.groupingBy(Foo::getName,Collectors.toList()))
                .forEach((name,fooListByName)->{
                    Bar bar = new Bar();
                    bar = fooListByName
                            .stream()
                            .reduce(bar,(u,t)-> u.sum(t), (a,b)-> a.Combination(b));
                    //(Bar)u.sum((Foo)t)是一个累加器，返回一个Bar类型
//#第一个参数返回实例u，传递你要返回的U类型对象的初始化实例u

//#第二个参数累加器accumulator，可以使用二元ℷ表达式（即二元lambda表达式），
// 声明你在u上累加你的数据来源t的逻辑
//#例如(u,t)->u.sum(t),此时lambda表达式的行参列表是返回实例u和遍历的集合元素t，
// 函数体是在u上累加t

//#第三个参数组合器combiner，同样是二元ℷ表达式，(u,t)->u
//#lambda表达式行参列表同样是(u,t)，函数体返回的类型则要和第一个参数的类型保持一致
//第三个参数是一个结合函数，可以将并行流中的第二个参数返回的结果再次进行Combine操作
//分成几步来解释把。当返回类型相同的时候为啥没有combiner，因为没有必要，
// 即使在并行流里直接使用accumulator本身合并结果就行。那么为什么返回类型不同时需要combiner，
// 因为即是不是并行流也有可能需要combiner，一个流的实现完全可以是先用当前的U和接下来的两个T调用
// 两次accumulator拿到两个U，然后再用combiner合并成一个，然后下一次迭代时再用那个。
// 或者先用identity调用accumulator把所有的T给map成U，然后再reduce出结果。
// 总体来说只存在3个参数且返回结果类型不同的reduce并不是因为不能有2个参数的，
// 单纯是因为这样更合理而已。reduce的定义本身并不考虑其顺序，只考虑不管什么顺序都能拿到最终结果

                    System.out.println(bar.toString());
                    barList.add(bar);
                });
    }
    /*
    输出结果
    name:A
    count:3
    totalTypeValue:14.0
    bazList:
        type:san
        typeValue:1.0
        type:nas
        typeValue:13.0

    name:B
    count:10
    totalTypeValue:189.0
    bazList:
        type:san
        typeValue:112.0
        type:nas
        typeValue:77.0

    name:C
    count:5
    totalTypeValue:43.0
    bazList:
        type:san
        typeValue:43.0
    */

}
