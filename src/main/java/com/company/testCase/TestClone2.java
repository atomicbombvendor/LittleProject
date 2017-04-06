package com.company.testCase;

/**
 * Created by eli9 on 4/6/2017.
 */
public class TestClone2 implements Cloneable {
    private ListTest l1;

    public ListTest getL1() {
        return l1;
    }

    public void setL1(ListTest l1) {
        this.l1 = l1;
    }

    public Object clone() {
        TestClone2 o = null;
        try {
            o = (TestClone2) super.clone();
            o.l1 = (ListTest)l1.clone(); //需要l1中有clone方法才可以进行Clone
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return o;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        ListTest l1 = new ListTest();
        l1.setName("L1");
        l1.setValue(123);
        TestClone2 tc1 = new TestClone2();
        tc1.setL1(l1);
        TestClone2 tc2 = (TestClone2)tc1.clone();
        System.out.println("tc1: " + tc1.hashCode() + " tc2:" + tc2.hashCode());
        System.out.println("tc1: " + tc1.getL1().getName().hashCode() + " tc2:" + tc2.getL1().getName().hashCode());
    }
}
