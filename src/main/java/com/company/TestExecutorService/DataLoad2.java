package com.company.TestExecutorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by eli9 on 4/21/2017.
 * 内部类实现Runnable接口，通过成员线程池来实现调度。这里主要考虑是不是应该使用static的成员线程池
 * 1.通过调用一个对象方法可以实现线程池管理(静态的线程池成员）
 * 2.在循环体中不断的New对象，可以实现线程池管理(静态的线程池成员)
 * 3.在循环体中不断的New对象，不可以实现线程池管理(非静态的线程池成员)
 * 4.通过调用一个对象方法可以实现线程池管理(非静态的线程池成员）
 */
public class DataLoad2 {
    public static void main(String[] args) {
        DataLoad2 dataLoad2 = new DataLoad2();
        for(int i=0;i<20;i++){
            dataLoad2.sumbitWorker(i);
        }

        dataLoad2.shutdown();
    }

    private final ExecutorService pool = Executors.newFixedThreadPool(10);

    public void sumbitWorker(int i){
        pool.submit(new DeltaDataPackageWorker(i));
    }

    public void shutdown()
    {
        try{
            pool.shutdown();
            if (!pool.awaitTermination(30, TimeUnit.MINUTES)) {
                pool.shutdownNow();
                System.out.println();
            }
        } catch (InterruptedException e) {
            pool.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    class DeltaDataPackageWorker implements Runnable{
        private int n;

        DeltaDataPackageWorker(int n){
            this.n = n;
        }

        @Override
        public void run() {
            System.out.println("PID: "+Thread.currentThread().getId()+" Hello Main: "+n);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
