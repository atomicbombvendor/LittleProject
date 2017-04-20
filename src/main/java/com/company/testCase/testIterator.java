package com.company.testCase;

import com.company.Entity.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eli9 on 4/17/2017.
 */
public class testIterator {
    public static void main(String[] args) {
        testIte();
    }
    /**
     * 测试改变迭代器的tmp元素，是否会改变 集合 的内容。
     * 结果：会影响List的内容
     */
    public static void testIte(){
        List<Person> list = new ArrayList<>();
        Person p1 = new Person();p1.setName("test1");list.add(p1);
        Person p2 = new Person();p2.setName("test2");list.add(p2);
        Person p3 = new Person();p3.setName("test3");list.add(p3);
        Person p4 = new Person();p4.setName("test4");list.add(p4);
        Person p5 = new Person();p5.setName("test5");list.add(p5);
        System.out.println(list.get(3).getAge());
        for (Person p:list) {
            p.setAge(110);
        }

        System.out.println(list.get(3).getAge());
    }

}
