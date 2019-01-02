package com.company.Thread.ObjectPool;

public class TestConnection {

    private String value;

    public TestConnection(){

    }

    public void connect(){
        value = "";
    }

    public String getValue(String input){
        return value;
    }

    public void disConnect(){
        value = null;
    }

    public boolean isConnect(){
        return value != null;
    }
}
