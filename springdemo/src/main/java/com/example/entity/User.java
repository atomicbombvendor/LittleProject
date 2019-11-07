package com.example.entity;

import com.example.annotation.MyAnnotation;

import java.lang.reflect.Method;

public class User {

    @MyAnnotation(name = "吴磊", arrays = {"2", "3"})
    public void aMethod(){}

    public void bMethod(){}

    public static void main(String[] args) throws ClassNotFoundException {

        Class<?> forName = Class.forName("com.example.entity.User");

        Method[] declaredMethods = forName.getDeclaredMethods();

        for (Method method : declaredMethods){
            System.out.println("方法名称：" + method.getName());

            // 得到方法上面的注解
            MyAnnotation annotation = method.getDeclaredAnnotation(MyAnnotation.class);

            if (annotation == null){
                System.out.println("方法上没有注解");
            }else{
                System.out.println("Id:" + annotation.id());
                System.out.println("Name:" + annotation.name());
                System.out.println("Arrays:" + annotation.arrays());
                System.out.println("Title:" + annotation.title());
            }
            System.out.println("-------------------");
        }
    }
}
