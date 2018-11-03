package com.company.DesignMode.Consumer_Producer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by atomic on 8/28/2017.
 */
public class ProducerConsumer {
    public static void main(String[] args) throws InterruptedException {
        ProducerConsumer pc = new ProducerConsumer();
        Storage s = new Storage();
        ExecutorService service = Executors.newCachedThreadPool();
        Producer p = new Producer("张三", s);
        Producer p2 = new Producer("李四", s);
        Producer p3 = new Producer("小明", s);
        Consumer c = new Consumer("王五", s);
        Consumer c2 = new Consumer("老刘", s);
        Consumer c3 = new Consumer("老林", s);

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
