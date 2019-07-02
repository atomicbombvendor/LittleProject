package com.company.DesignMode.Consumer_Producer2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by eli9 on 8/28/2017.
 */
public class Storage {
    Queue<Product> queues = new LinkedList<>();
    private static final Integer MAX_CAPACITY = 5;

    public void push(Product p) throws InterruptedException {
        queues.offer(p);
    }

    public Product pop() throws InterruptedException {
        return queues.poll();
    }

    public boolean isEmpty(){
        return queues.isEmpty();
    }

    public boolean isFull(){
        return queues.size() == MAX_CAPACITY;
    }
}
