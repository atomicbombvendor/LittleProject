package com.company.Thread.TestFuture;

public class FunctionService {

    public String getResult(String input){
        try {
            RetryPolicy.get(3, true)
                    .execute(() -> getResultFunction(input), null);
        } catch (Exception e) {
            System.out.println("send sftp fail after 3 time retries. input="+ input);
            return null;
        }
        return input;
    }

    private boolean getResultFunction(String input){
        if (input.length() <= 2){
            System.out.println("process " + input + " fail");
            return false;
        }
        System.out.println("process " + input + " success");
        return true;
    }
}
