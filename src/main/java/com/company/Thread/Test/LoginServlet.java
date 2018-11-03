package com.company.Thread.Test;

/**
 * Created by atomic on 1/12/2018. t4_threadSafe
 */
public class LoginServlet {

    private static String userNameRef;
    private static String passwordRef;
    //增加synchronized关键字可以保证方法调用的同步性
    public synchronized static void doPost(String userName, String password) {
        try {
            userNameRef = userName;
            if (userName.equals("a")) {
                Thread.sleep(5000);
            }
            passwordRef = password;
            System.out.println("userName=" + userNameRef + " password=" + password);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ALogin A = new ALogin();
        A.start();

        BLogin B = new BLogin();
        B.start();
    }
}

class ALogin extends Thread{

    @Override
    public void run(){
        LoginServlet.doPost("a", "ana");
    }
}

class BLogin extends Thread{

    @Override
    public void run(){
        LoginServlet.doPost("b", "bnb");
    }
}

class CLogin extends Thread{
    @Override
    public void run(){
        LoginServlet.doPost("c", "cnc");
    }
}




