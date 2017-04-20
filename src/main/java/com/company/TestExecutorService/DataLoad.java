package com.company.TestExecutorService;

import com.company.Entity.Person;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by eli9 on 4/20/2017.
 * 测试线程池的程序。这里有四种线程池
 */
public class DataLoad {
    private final ExecutorService pool = Executors.newFixedThreadPool(10);
    private final Queue<Person<String>> dataContentQueue;// new ArrayList<>();

    public DataLoad(Queue<Person<String>> person) {
        this.dataContentQueue = person;
    }

    private static void run(ExecutorService threadPool) {
        for (int i = 1; i < 5; i++) {
            final int taskId = 1;
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    for (int i = 1; i < 5; i++) {
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(String.format("TaskId:%d have run %d times", taskId, i));
                    }
                }
            });
        }
        threadPool.shutdown();
    }

    public static void main(String[] args) {
        // 创建可以容纳3个线程的线程池
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);

        // 线程池的大小会根据执行的任务数动态分配
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        // 创建单个线程的线程池，如果当前线程在执行任务时突然中断，则会创建一个新的线程替代它继续执行任务
        ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();

        // 效果类似于Timer定时器
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);

        run(fixedThreadPool);
        //run(cachedThreadPool);
        //run(singleThreadPool);
        //run(scheduledThreadPool);
    }
}
