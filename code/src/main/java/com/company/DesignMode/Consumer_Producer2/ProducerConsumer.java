package com.company.DesignMode.Consumer_Producer2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by eli9 on 8/28/2017.
 */
public class ProducerConsumer {

    private static Lock lock = new ReentrantLock();
    private static Condition fullCondition = lock.newCondition();
    private static Condition emptyCondition = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        ProducerConsumer pc = new ProducerConsumer();
        Storage s = new Storage();

        ExecutorService service = Executors.newCachedThreadPool();
        Producer p = new Producer("张三", s, lock, fullCondition, emptyCondition);
        Producer p2 = new Producer("李四", s, lock, fullCondition, emptyCondition);
        Producer p3 = new Producer("小明", s, lock, fullCondition, emptyCondition);
        Consumer c = new Consumer("王五", s, lock, fullCondition, emptyCondition);
        Consumer c2 = new Consumer("老刘", s, lock, fullCondition, emptyCondition);
        Consumer c3 = new Consumer("老林", s, lock, fullCondition, emptyCondition);

        service.execute(p);
        service.execute(p2);
        service.execute(p3);
        service.execute(c);
        service.execute(c2);
        service.execute(c3);

        Thread.sleep(10 * 1000);
        service.shutdown();
    }
}
