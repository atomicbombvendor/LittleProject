package com.company.Collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by atomic on 4/4/2017.
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

        Map map = new HashMap();

        map.put("apple", "新鲜的苹果");     //向列表中添加数据

        map.put("computer", "配置优良的计算机");   //向列表中添加数据

        map.put("book", "堆积成山的图书");     //向列表中添加数据

        System.out.println("Map集合大小为："+map.size());
    }
}
