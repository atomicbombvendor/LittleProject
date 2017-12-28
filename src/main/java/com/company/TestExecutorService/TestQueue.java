package com.company.TestExecutorService;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by ZZ on 2017/4/20.
 * 简单的测试Queue的方法
 */
public class TestQueue {
    private Queue<String> queue;

    public TestQueue(Queue<String> queue){
        this.queue = queue;
    }

    public void Add(String s){
        queue.add(s);
    }

    public String Remove(){
        return queue.remove();
    }

    public String Element(){
        return queue.element();
    }

    public boolean Offer(String s){
        return queue.offer(s);
    }

    public String Poll(){
        return queue.poll();
    }

    public String Peek(){
        return queue.peek();
    }

    public static void main(String[] args) {
        Queue<String> q1 = new LinkedList<>();
        q1.add("qq1");
        q1.add("qq2");
        q1.add("qq3");
        q1.add("qq4");
        q1.add("qq5");
        q1.add("qq6");

        q1.forEach(q -> System.out.println("Queue: "+q));

        System.out.println("Peek: "+ q1.peek());
    }
}
