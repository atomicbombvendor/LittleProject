package com.company.Collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by eli9 on 4/4/2017.
 */
public class TestStringList {
    public List copyList(List list){

        List<String> listC = new ArrayList<>();
        listC.addAll(list);
        return listC;
    }

    public List copyListByForeach(List list){

        List<String> listC = new ArrayList<>();
        for (Object o:list) {
            listC.add(o.toString());
        }
        return listC;
    }

    public void printList(List l){
        System.out.println("List>");
        for (Object i:l) {
            System.out.println(">" + i.toString());
        }
    }

    public void printHash(List l){
        System.out.println("List>");
        for (Object i:l) {
            System.out.println(">" + i.hashCode());
        }
    }

    public static void main(String[] args) {

        TestStringList t1 = new TestStringList();
        List<String> list1 = new ArrayList<>();
        list1.add("AAA1");list1.add("AAA2"); list1.add("AAA3"); list1.add("AAA4");
        list1.add("AAA5");list1.add("AAA6");
        List<String> list2 = t1.copyListByForeach(list1);
        System.out.println("print hash");
        t1.printHash(list2);
        t1.printHash(list1);

        list2 = t1.copyList(list1);
        System.out.println("print hash");
        t1.printHash(list2);
        t1.printHash(list1);
        System.out.println("浅复制");

        System.out.println("print hash");
        list1.set(0, "BDD");
        t1.printHash(list2);
        t1.printHash(list1);
        System.out.println("修改对象不影响");

        Map map = new HashMap();

        map.put("apple", "新鲜的苹果");     //向列表中添加数据

        map.put("computer", "配置优良的计算机");   //向列表中添加数据

        map.put("book", "堆积成山的图书");     //向列表中添加数据

        System.out.println("Map集合大小为："+map.size());
    }
}
