package com.company.Thread.ObjectPool;

public class TestConnection {

    private String value;
    private String id;

    public TestConnection(){

        //生成一个97-122之间的int类型整数--为了生成小写字母
        int intValL = (int)(Math.random()*26+97);
        //生成一个65-90之间的int类型整数--为了生成大写字母
        int intValU = (int)(Math.random()*26+65);

        StringBuilder sb = new StringBuilder();
        this.id = sb.append((char)intValL)
                .append((char)intValU)
                .append((int)(Math.random()*10)).toString();
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString(){

        return this.id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
