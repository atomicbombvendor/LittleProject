package com.company.Thread.t3;

/**
 * Created by atomic on 2/9/2018.\非线程安全
 */
public class MyThread extends Thread{
    private int count = 5; //count is not shared variable
    public MyThread(String name){
        super();
        this.setName(name);//设置线程名称
    }

    public void run(){
        super.run();
        while(count > 0){
            count--;
            System.out.println("由"+this.currentThread().getName()+" Calc, count = " + count);
        }
    }
}

class Run{
    public static void main(String[] args) {
        MyThread a = new MyThread("A");
        MyThread b = new MyThread("B");
        MyThread c = new MyThread("C");
        a.start();
        b.start();
        c.start();
    }
}
