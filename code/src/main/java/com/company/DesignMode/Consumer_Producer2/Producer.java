package com.company.DesignMode.Consumer_Producer2;

import org.apache.commons.lang3.RandomUtils;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by eli9 on 8/28/2017.
 */
public class Producer implements Runnable {
    private String name;
    private Storage storage;
    private Lock lock;
    private Condition fullCondition;
    private Condition emptyCondition;

    public Producer(String name, Storage storage, Lock lock, Condition fullCondition, Condition emptyCondition){
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
                if (storage.isFull()){
                    System.out.println("Queue is full");
                    fullCondition.await();
                }
                System.out.println(name + " ready to create product");
                Product p = new Product(RandomUtils.nextInt(1, 100));
                storage.push(p);
                System.out.println(name + " had created "+ p.toString());
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
