package com.company.Thread.suspend_resume_test;

/**
 * Created by atomic on 2/28/2018./
 */
public class MyThread extends Thread {
    private long i = 0;
    public long getI(){
        return i;
    }

    public void setI(){
        this.i = i;
    }

    public void run(){
        while (true){
            i++;
        }
    }
}

class Run1{
    public static void main(String[] args) {
        try{
            MyThread thread = new MyThread();
            thread.start();
            Thread.sleep(5000);

            //A段
            thread.suspend();
            System.out.println("A= " + System.currentTimeMillis() + " i= " + thread.getI());
            System.out.println("A= " + System.currentTimeMillis() + " i= " + thread.getI());

            //B段
            thread.resume();//重新启动
            Thread.sleep(5000);

            //C段
            thread.suspend();
            System.out.println("B= " + System.currentTimeMillis() + " i= " + thread.getI());
            Thread.sleep(5000);//这个sleep好像没有用
            System.out.println("B= " + System.currentTimeMillis() + " i= " + thread.getI());
        }catch (InterruptedException ie){
            ie.printStackTrace();
        }
    }
}

class SynchTestObject{
    synchronized public void printString(){
        System.out.println("begin");
        if(Thread.currentThread().getName().equalsIgnoreCase("a")){
            System.out.println("A 线程永远被suspend了！");
            Thread.currentThread().suspend();//因为这个对象的方法有锁，所以这个方法只能一个进来
        }
    }
}

class Run2{
    public static void main(String[] args) {
        try{
            final SynchTestObject object = new SynchTestObject();
            Thread thread1 = new Thread(){
                public void run(){
                    object.printString();
                }
            };

            thread1.setName("a");
            thread1.start();
            Thread.sleep(1000);
            Thread thread2 = new Thread(){
                public void run(){
                    System.out.println("thread2启动了，但进入不了printString()方法，只打印了一个begin");
                    System.out.println("因为printString()方法被a线程锁定并且永远suspend暂停了");
                    object.printString();
                }
            };
            thread2.start();
        }catch (InterruptedException ie){
            ie.printStackTrace();
        }
    }
}