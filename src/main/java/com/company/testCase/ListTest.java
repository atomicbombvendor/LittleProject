package com.company.testCase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by atomic on 3/1/2017.
 */
public class ListTest implements Cloneable{
    private String Name;
    private int Value;

    public ListTest(String Name, int Value){
        this.Name = Name;
        this.Value = Value;
    }

    public ListTest(){
        this.Name = Name;
        this.Value = Value;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getValue() {
        return Value;
    }

    public void setValue(int value) {
        Value = value;
    }

    public List<ListTest> getListTest(){
        List<ListTest> L = new ArrayList<ListTest>();
        for (int i = 0; i < 5; i++) {
            ListTest t = new ListTest("a",i);
            L.add(t);
        }
        return L;
    }

    public static void main(String[] args) {
        ListTest t1 = new ListTest();
        List<ListTest> L1 = t1.getListTest();
        System.out.println(L1.size());
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        ListTest o = null;
        o =(ListTest)super.clone();
        o.Name = this.Name;//String在内存中是不可以被改变的对象，所以克隆相当于一个String
        //空间里有两个引用，当修改其中一个值的时候，会新分配一块内存用来保存新的值，这个引用指向新的
        //内存空间。在修改的时候，并不会修改被复制的对象。
        return o;
    }
}
