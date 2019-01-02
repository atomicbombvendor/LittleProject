package com.company.Thread.TestFuture;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

public class CompleteServiceTest {

    private FunctionService service = new FunctionService();

    public static void main(String[] args) {
        CompleteServiceTest t = new CompleteServiceTest();
        List<String> result = t.getResult(mockInputs());

        for (String str :
                result) {
            System.out.println(str);
        }
    }

    private static List<String> mockInputs() {
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

    public List<String> getResult(List<String> inputs) {
        ExecutorService executor = Executors.newFixedThreadPool(100);
        CompletionService<String> completionService =
                new ExecutorCompletionService<>(executor);
        for (String input : inputs) {
            completionService.submit(getTask(input));
        }

        List<String> result = Collections.synchronizedList(new ArrayList<>());

        for (int i = 0; i < inputs.size(); i++) {
            try {
                result.add(completionService.take().get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();
        return result;
    }

    private Callable<String> getTask(String input) {
        return () -> {
            Thread.sleep(10000);
            return service.getResult(input);
        };
    }
}
