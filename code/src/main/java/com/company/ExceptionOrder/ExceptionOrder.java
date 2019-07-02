package com.company.ExceptionOrder;

import com.company.Entity.Person;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.dom4j.Document;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;

/**
 * Created by ZZ on 2017/3/26.
 */
public class ExceptionOrder {
    public static String file = "cdctables.properties";
    public static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ExceptionOrder.class);

    public static void main(String[] args) {
        testTestThrows();
    }

    public static String test1(){
        try{
            //顺序：没有异常-> 先执行try块内的内容，遇到return直接返回。
            //在try catch块外面的返回，属于公共的return。当try或者catch内没有返回时，调用最外面的返回。
            //所以正确的写法应该吧return放在最后。
            InputStream in = new FileInputStream("a.txt");
            System.out.println("In Try");
            //return "In Try";
        } catch (FileNotFoundException e) {
            System.out.println("Exception Start");
            e.printStackTrace();
            System.out.println("Exception End");
            return "In catch";
        }
        return null;
    }

    public static String test2(){
        try{
            InputStream in = new FileInputStream(file);
            System.out.println("In Try");
            //return "No Exception";
        } catch (FileNotFoundException e) {
            System.out.println("Exception Start");
            e.printStackTrace();
            System.out.println("Exception End");
            return "In catch";
        }
        return null;
    }

    /**
     * 会先返回return，再抛出异常
     * @return
     * return应该在语句最后和catch块里面
     */
    public static InputStream test3(){
        InputStream in;
        try{
            in = new FileInputStream("a.txt");
            //return in;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return in;
    }

    public static InputStream test4(){
        InputStream in;
        try{
            in = new FileInputStream(file);
            //return in;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return in;
    }

    // catch块中没有return,会使用最后的return;而且程序不会中断
    //有异常也不会中断
    public static boolean test5(){
        InputStream in;
        try{
            in = new FileInputStream(file);
        }catch (Exception e){
            e.printStackTrace();
            //return false;
        }
        System.out.println("end");
        return true;
    }

    public static int test6(){
        InputStream in;
        try {
            in = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return 2;
        }
        System.out.println("End");
        return 1;
    }

    public static void test6_1(){
        if(test6() == 2){
            System.out.println("GET");
        }
    }

    /**
     * 测试打印出异常的stack信息
     */
    public static void  testPrintStack(){
        try {
            System.out.println(11);
            throw new Exception("aaa");
        } catch (Exception e) {
            System.out.println(Arrays.toString(ExceptionUtils
                    .getRootCauseStackTrace(e)));
        }
    }

    /**
     * 测试当异常发生时，不在catch语句写明return，抛出的是空值吗？
     */
    public static Person testThrows() {
        Person p1 = new Person();
        Document doc = null;
        if(doc == null){
            log.error("doc is null");
            throw new RuntimeException("doc is null");
        }
        return p1;
    }

    /**
     * 测试testThrows方法的测试方法
     * 测试结果：异常发生后，抛出的结果为空值
     */
    public static void testTestThrows(){
        Person p = null;
        int attempts = 0;
        while (++attempts <= 3) {
            try {
                p = testThrows();
                if(p != null){break;}
            } catch (Exception e) {
                //重试的等待200毫秒，为了等待服务器响应。
                //发送请求后，等待服务器响应。服务器可能由于请求太多或资源不够，不能在不间断的请求，
                //所以需要间隔一段时间
                try {
                    Thread.sleep((long)(200*Math.pow(attempts,2)));
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }

                if (attempts == 3) {
                    System.out.println("Tried " + attempts + " times to getPerformanceIdList " + "\n " + e);
                }
            }
        }
        if(p == null){
            System.out.println("p is null");
        }else{
            System.out.println("P is not null");
        }
    }

}
