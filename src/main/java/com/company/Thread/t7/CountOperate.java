package com.company.Thread.t7;

/**
 * Created by eli9 on 2/9/2018./
 * Thread.currentThread() 和 this有差异
 * 附链接 https://www.cnblogs.com/huangyichun/p/6071625.html
 * this是实例的对象的线程，Thread.currentThread是Thread线程，调用实例对象的线程
 */
public class CountOperate extends Thread {
    public CountOperate(){
        System.out.println("CountOperate---begin");
        System.out.println("Thread.currentThread().getName()="+Thread.currentThread().getName());//调用这个方法的线程是Main
        System.out.println("Thread.currentThread().isAlive()="+Thread.currentThread().isAlive());
        System.out.println("this.getName()=" + this.getName());
        System.out.println("this.isAlive()=" + this.isAlive());//this代表的是CountOperate对象实例
        System.out.println("CountOperate---end");
    }

    public void run(){
        System.out.println("run---begin");
        System.out.println("Thread.currentThread().getName()="+Thread.currentThread().getName());
        System.out.println("Thread.currentThread().isAlive()="+Thread.currentThread().isAlive());
        System.out.println("this.getName()="+this.getName());
        System.out.println("this.isAlive()=" + this.isAlive());//this指向的还是new CountOperate()对象实例
        System.out.println("run---end");
        //实际上new Thread(thread)会将thread应用的对象绑定到一个private变量target上，
        //在t1被执行的时候即t1.run()被调用的时候，它会调用target.run()方法，也就是说它是直接调用thread对象的run方法，
        //再确切的说，在run方法被执行的时候，this.getName()实际上返回的是target.getName()，而Thread.currentThread().getName()实际上是t1.getName()。
    }
}

class Run2{
    public static void main(String[] args) {
        CountOperate c = new CountOperate();
        Thread t1 = new Thread(c);
        System.out.println("main begin t1 isAlive=" + t1.isAlive());
        t1.setName("A");
        t1.start();
        System.out.println("main end t1 isAlive=" + t1.isAlive());
    }
}