package com.company.TestExecutorService;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.commons.lang3.RandomUtils;

import java.util.concurrent.*;

public class TestShutdownExecutors {

    private static final Integer INTERVAL = 8;
    private ExecutorService pool;
    private static final Integer THREAD_NUM = 10;

    public TestShutdownExecutors() {

        this.pool = new ThreadPoolExecutor(THREAD_NUM, THREAD_NUM, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024),
                new ThreadFactoryBuilder().setNameFormat("Delivery-Task-%d").build(),
                new ThreadPoolExecutor.DiscardPolicy());
    }

    public void testShutdown() {

        System.out.println("Main start");
        for (int i=0; i< THREAD_NUM; i++){
            this.pool.submit(new Task());
        }

        this.pool.shutdown();
        while (!this.pool.isTerminated()){
            this.pool.submit(new Task());
            System.out.println("pool is not shut down");
        }
        System.out.println("Main End");
    }

    public static void main(String[] args) {

        TestShutdownExecutors test = new TestShutdownExecutors();
        test.testShutdown();
    }

    public static class Task implements Runnable {

        @Override
        public void run(){
            System.out.println("Callable execute");
            try {
                TimeUnit.SECONDS.sleep(RandomUtils.nextInt(1, 4));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
