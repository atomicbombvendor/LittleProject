package com.company.Collections;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eli9 on 4/4/2017.
 */
public class TestList {
    public List copyList(List list){
        List<Integer> listC = new ArrayList<>();
        listC.addAll(list);
        return listC;
    }

    public List copyListByForeach(List list){
        List<Integer> listC = new ArrayList<>();
        for (Object o:list) {
            listC.add((Integer)o);
        }
        return listC;
    }

    public void printList(List l){
        System.out.println("List>");
        for (Object i:l) {
            System.out.println(">" + i.toString());
        }
    }

    public static void main(String[] args) {
        TestList t1 = new TestList();
        List<Integer> list1 = new ArrayList<>();
        Integer i1 = 3;
        Integer i2 = 4;
        Integer i3 = 5;
        Integer i4 = 6;
        Integer i5 = 7;
        Integer i6 = 8;
        list1.add(i1);list1.add(i2); list1.add(i3); list1.add(i4);
        list1.add(i5);list1.add(i6);
        List<Integer> list2 = t1.copyListByForeach(list1);
        list2.remove(0);
        t1.printList(list2);
        t1.printList(list1);

        list2 = t1.copyList(list1);
        list2.remove(0);
        t1.printList(list2);
        t1.printList(list1);
    }
}
