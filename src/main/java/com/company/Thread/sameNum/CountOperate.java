package com.company.Thread.sameNum;

/**
 * Created by atomic on 2/9/2018. \
 */
public class CountOperate extends Thread{

    public CountOperate(){
        System.out.println("CountOperate---begin");
        System.out.println("Thread.currentThread.getName()="+Thread.currentThread().getName());
        System.out.println("this.getName()="+this.getName());
        System.out.println("CountOperate----end");
    }

    public void run(){
        System.out.println("run---begin");
        System.out.println("Thread.currentThread().getName()="+Thread.currentThread().getName());
        System.out.println("this.getName()="+this.getName());
        System.out.println("run----end");
    }
}

class Run2{
    public static void main(String[] args) {
        CountOperate c = new CountOperate();
        Thread t1 = new Thread(c);
        t1.setName("A");
        t1.start();

        Thread t2 = new Thread(c);
        t2.setName("B");
        t2.start();
    }
}
