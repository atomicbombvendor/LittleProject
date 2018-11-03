package com.company.testCase;

/**
 * Created by atomic on 9/11/2017.
 */
interface IObject{

}

class Foo implements IObject{

}

class TestTTTT2 extends Foo{

}

public class InstanceOfTest{
    public static void main(String args[]){
        test();
    }
    public static void test(){
        IObject f = new TestTTTT2();
        if(f instanceof java.lang.Object) System.out.println("true");
        if(f instanceof Foo) System.out.println("true");
        if(f instanceof TestTTTT2) System.out.println("true");
        if(f instanceof IObject) System.out.println("true");
    }
}