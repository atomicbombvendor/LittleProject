package com.company.Thread.t3;

/**
 * Created by atomic on 2/9/2018.\
 */
public class LoginServlet {
    private static String usernameRef;
    private static String passwordRef;
    //这两个变量是共享变量；存在同步的问题；解决方案，使用synchronize关键字
    public synchronized static void doPost(String username, String password){
        try{
            usernameRef = username;
            if(username.equalsIgnoreCase("A")){
                Thread.sleep(5000);
            }

            passwordRef = password;
            System.out.println("username="+ usernameRef + " password=" + password);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
class ALogin extends Thread{
    public void run(){
        LoginServlet.doPost("A", "aa");
    }
}
class BLogin extends Thread{
    public void run(){
        LoginServlet.doPost("b", "BB");
    }
}

class Run3{
    public static void main(String[] args) {
        ALogin a = new ALogin();
        a.start();
        BLogin b = new BLogin();
        b.start();
    }
}
