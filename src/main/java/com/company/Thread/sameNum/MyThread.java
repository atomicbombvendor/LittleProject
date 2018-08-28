package com.company.Thread.sameNum;

/**
 * Created by eli9 on 2/9/2018.\
 */
public class MyThread extends Thread {
    private int i = 5;
    //  共享变量，会出现i的值为负数的情况。解决方案：使用synchronized关键字
    @Override
    public void run(){
        System.out.println("i=" + (i--) + " threadName=" + Thread.currentThread().getName());
    }
}

class Run{
    public static void main(String[] args) {
        MyThread thread = new MyThread();
        Thread t1 = new Thread(thread);
        Thread t2 = new Thread(thread);
        Thread t3 = new Thread(thread);
        Thread t4 = new Thread(thread);
        Thread t5 = new Thread(thread);
        t1.start();t2.start();t3.start();t4.start();t5.start();

    }
}