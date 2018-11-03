package com.company.multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by atomic on 5/17/2017.
 * 我的一个List<String>中有10W条记录，要把这个List的的内容拼接起来。
 * 我启动5个线程同时处理，每个线程处理2w条记录。然后把5个线程处理的内容拼接起来。
 * 请大家给一个简单的demo。
 * URL: http://www.iteye.com/problems/100706
 */
public class MultiThreadList {
    public static void main(String[] args) {
        try {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                list.add(i + ",");
            }

            System.out.println(new MultiThreadList().list2Str(list, 5));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String list2Str(List<String> list, final int nThreads) throws Exception {
        if (list == null || list.isEmpty()) {
            return null;
        }

        int len = 0;
        for (String str : list) {
            len += str.length();
        }
        StringBuffer ret = new StringBuffer(len);//计算得到list所有元素的总长度，用来初始化ret

        final int size = list.size();//list元素的个数
        ExecutorService executorService = Executors.newFixedThreadPool(nThreads);
        List<Future<String>> futures = new ArrayList<>(nThreads);

        try {
            for (int i = 0; i < nThreads; i++) {
                final int j = i;
                Callable<String> task = () -> {
                    int len1 = 0;
                    //将List分成5段，计算每段的元素总长度len1
                    for (int n = size / nThreads * j; n < size / nThreads * (j + 1); n++) {
                        len1 += list.get(n).length();
                    }

                    //用len1初始化sb
                    StringBuffer sb = new StringBuffer(len1);
                    //将这一段的元素连接起来
                    for (int n = size / nThreads * j; n < size / nThreads * (j + 1); n++) {
                        sb.append(list.get(n));
                    }
                    //返回sb
                    return sb.toString();
                };
                futures.add(executorService.submit(task));
            }

            for (Future<String> future : futures) {
                //按照顺序从future中得到 Callable<String>的返回值
                ret.append(future.get());
            }
        } finally {
            executorService.shutdown();
        }

        return ret.toString();
    }
}
