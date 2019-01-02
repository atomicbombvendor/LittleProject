package com.company.Thread.TestFuture;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestFuture {

    private FunctionService service = new FunctionService();

    public static void main(String[] args) {
        TestFuture t = new TestFuture();
        List<String> result = t.getUploadResult(mockInputs());

        for (String str: result) {
            System.out.println(str);
        }
    }

    private static List<String> mockInputs(){
        List<String> inputs = new ArrayList<>();
        inputs.add("ad");
        inputs.add("ad");
        inputs.add("ad");
        inputs.add("ad");
        inputs.add("adadfa");
        inputs.add("adadfa");
        inputs.add("adadfa");
        inputs.add("adadfa");
        inputs.add("adadfa");
        inputs.add("adadfa");

        return inputs;
    }

    public List<String> getUploadResult(List<String> inputs){
        List<Future<String>> futureList = new ArrayList<>();
        submitTask(inputs, futureList);

        return getSubmitResult(futureList);
    }

    private void submitTask(List<String> inputs, List<Future<String>> futureList){
        ExecutorService service = Executors.newFixedThreadPool(inputs.size() > 100 ? 10: 6);
        for (String input: inputs) {
            Future<String> f = service.submit(getTask(input));
            futureList.add(f);
        }
        service.shutdown();
    }

    private List<String> getSubmitResult(List<Future<String>> futureList){
        List<String> result = Collections.synchronizedList(new ArrayList<>());
//        List<String> result = new ArrayList<>();
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(getCollectJob(futureList, result));
        service.shutdown();
        // 返回时还没有完全的shutdown，所以result为空。需要等待service完全的关闭才可以。
        while (!service.isTerminated()){
            System.out.println("service is not terminated");
        }
        return result;
    }

    private Runnable getCollectJob(List<Future<String>> futureList, List<String> collect){
        return () -> {
            for (Future<String> future : futureList){
                try{
                    while(true){
                        if (future.isDone() && !future.isCancelled()){
                            String temp = future.get();
                            System.out.println(">>>" + temp);
                            collect.add(temp);
                            break;
                        }else{
                            // 添加线程等待 直到结束
                            Thread.sleep(1000);
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
    }

    private Callable<String> getTask(String input){
        return () -> service.getResult(input);
    }
}
