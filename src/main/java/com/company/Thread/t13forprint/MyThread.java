package com.company.Thread.t13forprint;

/**
 * Created by atomic on 2/27/2018./
 */
public class MyThread extends Thread {

    public void run(){
        super.run();
        try {
            for (int i = 0; i < 500000; i++) {
                if(this.interrupted()){//只是interrupted不能中断线程，只是中断了for
                    System.out.println("已经是停滞状态了，我要退出了");
                    //break;
                    throw new InterruptedException();
                    //用抛异常的方式来
                }
                System.out.println("i= " + (i+1));
            }
            System.out.println("我被输出，则此代码是for又继续运行，线程并未停止");
        } catch (InterruptedException e) {
            System.out.println("进MyThread.java类run方法中的catch了");
            e.printStackTrace();
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
        System.out.println("End");
    }
}
