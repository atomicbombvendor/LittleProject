package com.company.TestExecutorService.ListenableFutureTest;

import com.google.common.util.concurrent.*;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.lang3.RandomUtils;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAdder;

public class TestListenableFuture {

    private static LongAdder collect = new LongAdder();

    public static ListeningExecutorService getListenableFuture(){

        return MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
    }

    public static class MyCallable implements Callable<Long>{

        @Override
        public Long call() throws Exception {
            System.out.println("Callable execute");
            collect.add(RandomUtils.nextLong(1, 10));
//            TimeUnit.SECONDS.sleep(RandomUtils.nextInt(1, 4));
            return collect.sumThenReset();
        }
    }

    public static class ProcessCallResult implements FutureCallback<Long> {

        @Override
        public void onSuccess(@Nullable Long l) {
            System.out.println("current result: " + l);
        }

        @Override
        public void onFailure(Throwable throwable) {

            System.out.println(ExceptionUtils.getFullStackTrace(throwable));
        }
    }

    public static void main(String[] args) {

        ListeningExecutorService les = getListenableFuture();
        MyCallable task = new MyCallable();
        ListenableFuture<Long> listenableFuture = les.submit(task);
        Futures.addCallback(listenableFuture, new ProcessCallResult(), les);
        System.out.println("main end");
    }
}
