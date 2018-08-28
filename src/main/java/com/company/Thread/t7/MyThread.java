package com.company.Thread.t7;

/**
 * Created by eli9 on 2/9/2018./
 */
public class MyThread extends Thread {

    @Override
    public void run(){
        System.out.println("run="+this.isAlive());
    }
}

class Run{
    public static void main(String[] args) throws InterruptedException {
        MyThread thread = new MyThread();
        System.out.println("Begin="+ thread.isAlive());
        thread.start();
        Thread.sleep(1000);
        System.out.println("end="+thread.isAlive());
    }
}
