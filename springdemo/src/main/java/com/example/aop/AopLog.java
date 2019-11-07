package com.example.aop;

import com.example.transaction.MyTransaction;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AopLog {

    private MyTransaction myTransaction;

    @Autowired
    public AopLog(MyTransaction myTransaction){
        this.myTransaction = myTransaction;
        this.myTransaction.setName("AopLog ");
    }

    @Before("execution(* com.example.service.UserService.add(..))")
    public void before(){
        System.out.println("AopLog 前置方法：在方法执行之前执行。。。");
    }

    @After("execution(* com.example.service.UserService.add(..))")
    public void after(){
        System.out.println("AopLog 后置通知：在方法之后执行。。。");
    }

    @AfterReturning("execution( * com.example.service.UserService.add(..))")
    public void afterReturning(){
        System.out.println("返回通知。。。");
    }

    @AfterThrowing("execution( * com.example.service.UserService.add(..))")
    public void afterThrowing(){
        System.out.println("AopLog 异常通知：异常抛出后执行。。。");
        myTransaction.rollback();
    }

    @Around("execution(* com.example.service.UserService.add(..))")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        myTransaction.begin();
        System.out.println("AopLog 环绕通知：调用方法之前执行。。。");
        // 如果这里抛出异常，之后的代码不会被执行
        proceedingJoinPoint.proceed();
        System.out.println("AopLog 环绕通知：调用方法之后执行。。。");
        myTransaction.commit();
    }
}
