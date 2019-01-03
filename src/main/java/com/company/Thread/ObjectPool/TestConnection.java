package com.company.Thread.ObjectPool;

public class TestConnection {

    private String value;

    public TestConnection(){

    }

    public void connect(){

        value = "connect";
    }

    public String getValue(String input) throws Exception {

        if (!isConnect()){
            System.out.println("Object is not connect");
            throw new Exception("Object is not connect");
        }

        if (input == null || input.length() <= 2){
            return null;
        }
        return input;
    }

    public void disConnect(){

        value = null;
    }

    public boolean isConnect(){

        return value != null;
    }
}
