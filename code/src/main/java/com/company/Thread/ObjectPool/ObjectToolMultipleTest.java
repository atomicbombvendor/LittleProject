package com.company.Thread.ObjectPool;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class ObjectToolMultipleTest {

    public static void main(String[] args) {
        ObjectToolMultipleTest test = new ObjectToolMultipleTest();
        List<String> result = test.getResult(mockInputs());
        result = result.stream().filter(Objects::nonNull)
                .distinct().collect(Collectors.toList());
        for (String r:
             result) {
            System.out.println("r: " + r);
        }
    }

    public List<String> getResult(List<String> inputs) {

        int threadNum = 6;
        ExecutorService executor = Executors.newFixedThreadPool(threadNum);
        CompletionService<String> service = new ExecutorCompletionService<>(executor);
        GenericObjectPool<TestConnection> objectPool = getObjectPool(threadNum);

        for (String input :
                inputs) {
            service.submit(getTask(objectPool, input));
        }

        executor.shutdown();

        return collectResult(inputs, service);
    }

    private Callable<String> getTask(GenericObjectPool<TestConnection> objectPool, String input) {
        return () -> {
            TestConnection test = objectPool.borrowObject();
            String result = test.getValue(input);
            objectPool.returnObject(test);
            return result;
        };
    }

    private GenericObjectPool<TestConnection> getObjectPool(int threadNum) {
        MyPoolableObjectFactory factory = new MyPoolableObjectFactory();

        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(threadNum-3);
        config.setMaxIdle(threadNum-3);
        config.setMinIdle(threadNum-3);

        return new GenericObjectPool<>(
                factory, config
        );
    }

    private List<String> collectResult(List<String> inputs, CompletionService<String> service) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < inputs.size(); i++) {
            try {
                result.add(service.take().get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private static List<String> mockInputs() {
        List<String> inputs = new ArrayList<>();
        inputs.add("ad");
        inputs.add("ad");
        inputs.add("ad");
        inputs.add("ad");
        inputs.add("ad2");
        inputs.add("ad1");
        inputs.add("ad3");
        inputs.add("add");
        inputs.add("ad4");
        inputs.add("ad5");
        inputs.add("ad6");

        return inputs;
    }
}
