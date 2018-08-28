package com.company.Thread.Test;

/**
 * Created by eli9 on 1/12/2018.
 */
public class MyThread extends Thread {
    private int i = 5;

    @Override
    public void run(){
        //i--的操作是在进入Println之前发生的，所以会发生不同步的问题
        System.out.println("i=" + (i--) + " threadName=" + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        MyThread thread = new MyThread();
        Thread t1 = new Thread(thread);
        Thread t2 = new Thread(thread);
        Thread t3 = new Thread(thread);
        Thread t4 = new Thread(thread);
        Thread t5 = new Thread(thread);
        Thread t6 = new Thread(thread);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();

    }
}
