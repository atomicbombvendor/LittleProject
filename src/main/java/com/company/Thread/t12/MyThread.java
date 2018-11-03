package com.company.Thread.t12;

/**
 * Created by atomic on 2/27/2018./
 */
public class MyThread extends Thread {
    public void run(){
        super.run();
        for (int i = 0; i < 50000; i++) {
            System.out.println("i=" + (i+1));
        }
    }
}

class Run{
//    public static void main(String[] args) {
//        try {
//            MyThread thread = new MyThread();
//            thread.start();
//            Thread.sleep(1000);
//            thread.interrupt();
//            System.out.println("是否停止1？=" + thread.interrupted());
//            System.out.println("是否停止2？=" + thread.interrupted());//这样是不能结束线程的
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("End");
//    }

    public static void main(String[] args) {
        Thread.currentThread().interrupt();//指的是Main线程
        System.out.println("是否停止1？=" + Thread.interrupted());
        System.out.println("是否停止2？=" + Thread.interrupted());//interrupted会清空线程状态，第二次调用时，状态已经被清空了,并被设置为false。
    }
}

class Run2{
    public static void main(String[] args) {
       try {
            MyThread thread = new MyThread();
            thread.start();
            Thread.sleep(1000);
            thread.interrupt();
            System.out.println("是否停止1？=" + thread.isInterrupted());
            System.out.println("是否停止2？=" + thread.isInterrupted());//不喬状态的判断
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("End");
    }
}
