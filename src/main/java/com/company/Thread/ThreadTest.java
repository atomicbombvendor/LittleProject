package com.company.Thread;

/**
 * Created by ZZ on 2017/6/18.
 */
public class ThreadTest {
    public static void main(String[] args) throws InterruptedException {
        threadJoin();
    }

    public static void waitForSignal() {
        Object obj = new Object();
//        synchronized(Thread.currentThread())
//        {
//            obj.wait();//wait must have try-catch to get exception
//            obj.notify();
//        }
        synchronized (obj) {
            try {
                obj.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            obj.notify();
        }
    }

    public static void threadJoin() throws InterruptedException {
        Thread t=new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.print("2");
            }
        });
        t.start();

        t.join();//Waits for this thread to die.
        //等待子线程结束才可以结束t.join
        System.out.print("1");
    }
}
