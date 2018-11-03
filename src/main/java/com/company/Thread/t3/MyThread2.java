package com.company.Thread.t3;

/**
 * Created by atomic on 2/9/2018.\
 */
public class MyThread2 extends Thread {
    private int count = 5;//同一个类中的变量，在多个实例对象中是共享的；
    //三个线程访问变量count的时刻是不确定的，所以可以得到两个变量输出的值是一样的
    //主要原因是因为，run方法不是原子性的。count--，需要分三步进行：取值，减一，赋值。
    //可能在中间某一步的时候，插入了别的线程
    //解决方案，在run方法上面加上synchronized关键字，可以让线程排队执行

    public void run() {
        super.run();
        count--;
        System.out.println("With " + this.currentThread().getName() + " Calc, count=" + count);
    }
}

class Run2{
    public static void main(String[] args) {
        MyThread2 myThread = new MyThread2();
        Thread a = new Thread(myThread, "A");
        Thread b = new Thread(myThread, "B");
        Thread c = new Thread(myThread, "C");
        Thread d = new Thread(myThread, "D");
        Thread e = new Thread(myThread, "E");
        a.start();b.start();c.start();d.start();e.start();

    }
}