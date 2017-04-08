package com.company.testCase;

import com.company.ClassExtends.Person;

import java.util.*;

/**
 * Created by eli9 on 4/8/2017.
 */
public class RepeatedList {

    public static void main(String[] args) {
        //testObjectList();//行不通
        testObjectList2();//重写 equals 和 hashcode方法
    }

    public static List<String> clearRepeatedListBySet(List<String> l){
        Set set = new HashSet<>();
        List newList = new ArrayList();
        for(String cd: l){
            if(set.add(cd)){
                newList.add(cd);
            }
        }
        return newList;
    }

    public static List<String> clearRepeatedListByCheck(List<String> l){
        List<String> newList = new ArrayList<>();
        for(String s: l){
            if(!newList.contains(s)){
                newList.add(s);
            }
        }
        return newList;
    }

    public static List<String> clearRepeatedListByNewHashSet(List<String> l){
        List<String> newList = new ArrayList<>();
        Set set = new HashSet();
        set.addAll(l);
        newList.addAll(set);
        return newList;
    }

    public static void printList(List<String> l){
        l.stream().forEach(s -> System.out.println("string: " + s));
    }

    public static void testStringList(){
        List<String> list = new ArrayList<>();
        list.add("aaa");
        list.add("bbb");
        list.add("aaa");
        list.add("aba");
        list.add("aaa");

        List temp;
        temp = RepeatedList.clearRepeatedListByCheck(list);
        RepeatedList.printList(temp);
        System.out.println("");

        temp = RepeatedList.clearRepeatedListByNewHashSet(list);
        RepeatedList.printList(temp);
        System.out.println("");

        temp = RepeatedList.clearRepeatedListBySet(list);
        RepeatedList.printList(temp);
        System.out.println("");

        Set set = new HashSet();
        set.addAll(list);
        list.clear();
        list.addAll(set);
        printList(list);
    }

    public static void testObjectList2(){
        List<Person> list = new ArrayList<>();
        Set<Person> set = new HashSet<>();
        for (int i = 0; i < 10 ; i++) {
            Person p = new Person();
            p.setName("Name"+i);
            p.setAge(String.valueOf(i+10));
            p.setSex("man");
            set.add(p);
            list.add(p);
        }

        for (int i = 0; i < 10 ; i++) {
            Person p = new Person();
            p.setName("Name"+i);
            p.setAge(String.valueOf(i+10));
            p.setSex("man");
            set.add(p);
            list.add(p);
        }

        System.out.println(list.get(0).hashCode()+" " + list.get(10).hashCode());
        System.out.println("0 -> 10:" + list.get(0).equals(list.get(10)));
        List<Person> newList = new ArrayList<>();
        Set<Person> setT = new HashSet<>();
        setT.addAll(list);
        newList.addAll(setT);
        newList.stream().forEach(p -> System.out.println(p.getName()+" " + p.getAge()));
    }

    public static void testObjectList(){
        List<Person> list = new ArrayList<>();
        for (int i = 0; i < 10 ; i++) {
            Person p = new Person();
            p.setName("Name"+i);
            p.setAge(String.valueOf(i+10));
            p.setSex("man");
            list.add(p);
        }

        for (int i = 0; i < 10 ; i++) {
            Person p = new Person();
            p.setName("Name"+i);
            p.setAge(String.valueOf(i+10));
            p.setSex("man");
            list.add(p);
        }

        List<Person> newList = new ArrayList<>();
        Set<Person> set = new HashSet(list);
        newList.addAll(set);
        list.stream().forEach(p -> System.out.println(p.getName()+" " + p.getAge()));
    }
}
