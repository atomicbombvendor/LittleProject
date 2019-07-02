package com.company.DesignMode.Consumer_Producer2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by eli9 on 8/28/2017.
 */
public class Consumer implements Runnable {

    private String name;
    private Storage storage = null;
    private Lock lock;
    private Condition fullCondition;
    private Condition emptyCondition;

    public Consumer(String name, Storage storage, Lock lock, Condition fullCondition, Condition emptyCondition){
        this.name = name;
        this.storage = storage;
        this.lock = lock;
        this.fullCondition = fullCondition;
        this.emptyCondition = emptyCondition;
    }

    @Override
    public void run() {
        while(true){
            lock.lock();
            try {
                if (storage.isEmpty()){
                    System.out.println("Queue is full");
                    emptyCondition.await();
                }
                System.out.println(name + " ready to use product");
                Product product = storage.pop();
                System.out.println(name + " had consume "+ product.toString());
                System.out.println("=======");

                fullCondition.signalAll();
                emptyCondition.signalAll();
                lock.unlock();
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
