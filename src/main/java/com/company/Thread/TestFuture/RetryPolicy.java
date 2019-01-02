package com.company.Thread.TestFuture;

import java.util.function.BiFunction;
import java.util.function.Supplier;

public class RetryPolicy {
    private int retryTimes;
    private int retryInterval;
    private boolean interrupt = false;

    public RetryPolicy() {
    }

    public RetryPolicy(int retryTimes) {
        this.retryTimes = retryTimes;
    }

    public RetryPolicy(int retryTimes, int retryInterval) {
        this.retryTimes = retryTimes;
        this.retryInterval = retryInterval;
    }

    public RetryPolicy(int retryTimes, boolean interrupt) {
        this.retryTimes = retryTimes;
        this.interrupt = interrupt;
    }

    public static RetryPolicy get(int retryTimes) {
        return new RetryPolicy(retryTimes);
    }

    public static RetryPolicy get(int retryTimes, int retryInterval) {
        return new RetryPolicy(retryTimes, retryInterval);
    }

    public static RetryPolicy get(int retryTimes, boolean interrupt) {
        return new RetryPolicy(retryTimes, interrupt);
    }

    private BiFunction<Integer, Throwable, Integer> exceptionFunction = (retry, e) -> {
        retry++;
        if (retry < retryTimes) {
            if (retryInterval == 0) {
                retryInterval = 1000;
            }
            try {
                Thread.sleep(retryInterval);
            } catch (InterruptedException e1) {

            }
        } else if (retry == retryTimes) {
            System.out.println("Retry policy to " + retryTimes + " times");
        }
        return retry;
    };

    public <T> T execute(Supplier<T> supplier, Runnable finallyCall, Supplier<T> callback) {
        int retry = 0;
        try {
            while (retry < retryTimes) {
                try {
                    return supplier.get();
                } catch (RetriableException e) {
                    retry = exceptionFunction.apply(retry, e);
                    if (retry >= retryTimes && interrupt) {
                        throw e;
                    }
                }
            }
            return callback.get();
        } catch (Exception e) {
            throw e;
        } finally {
            if (finallyCall != null) {
                finallyCall.run();
            }
        }
    }

    public void execute(Runnable supplier, Runnable finallyCall) {
        int retry = 0;
        try {
            while (retry < retryTimes) {
                try {
                    supplier.run();
                    break;
                } catch (RetriableException e) {
                    retry = exceptionFunction.apply(retry, e);
                    if (retry >= retryTimes && interrupt) {
                        throw e;
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (finallyCall != null) {
                finallyCall.run();
            }
        }
    }

}
