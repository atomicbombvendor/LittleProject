package com.company.DesignMode.Consumer_Producer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by atomic on 8/28/2017.
 */
public class Storage {
    BlockingQueue<Product> queues = new LinkedBlockingDeque<>(10);

    public void push(Product p) throws InterruptedException {
        queues.put(p);
    }

    public Product pop() throws InterruptedException {
        return queues.take();
    }
}
