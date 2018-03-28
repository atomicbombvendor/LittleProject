package com.company.Thread.t11;

/**
 * Created by eli9 on/ 2/27/2018.
 */
public class MyThread extends Thread {
    public void run(){
        super.run();
        for (int i = 0; i < 500000; i++) {
            System.out.println("i=" + (i+1));
        }
    }

}

class Run{
    public static void main(String[] args) {
        try {
            MyThread thread = new MyThread();
            thread.start();
            Thread.sleep(2000);
            thread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
