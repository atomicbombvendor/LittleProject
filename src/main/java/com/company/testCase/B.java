package com.company.testCase;

/**
 * Created by atomic on 8/25/2017.
 */
public class B {
    {
        System.out.println("构造块");
    }
    private static int x=0;
    private static int y=0;
    static {
        System.out.println(x+y +'a'+(x+y)+y);
        System.out.println("静态块");
    }
    public static B t1 = new B();
    public static B t2 = new B();

    public static void testStatic(){
        System.out.println("静态方法");
    }

    public static void main(String[] args) {
        B t = new B();
    }
}
