package com.example.aop;

import com.example.annotation.MyAnnotation;
import com.example.transaction.MyTransaction;
import com.example.transaction.MyTransaction2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class AopTransaction {

    private MyTransaction myTransaction;

    @Autowired
    public AopTransaction(MyTransaction myTransaction){
        this.myTransaction = myTransaction;
        this.myTransaction.setName("AopTransaction ");
    }

    @Before("execution(* com.example.service.UserService.add(..))")
    public void before() {
        System.out.println("AopTransaction 前置方法：在方法执行之前执行。。。");
    }

    @After("execution(* com.example.service.UserService.add(..))")
    public void after() {
        System.out.println("AopTransaction 后置通知：在方法之后执行。。。");
    }

    @AfterReturning("execution( * com.example.service.UserService.add(..))")
    public void afterReturning() {
        System.out.println("AopTransaction 返回通知。。。");
    }

    @AfterThrowing("execution( * com.example.service.UserService.add(..))")
    public void afterThrowing() {
        System.out.println("AopTransaction 异常通知：异常抛出后执行。。。");
        rollback();
    }

    @Around("execution(* com.example.service.UserService.add(..))")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        MyAnnotation myAnnotation = this.getMethodAnnotation(proceedingJoinPoint);
        begin(myAnnotation);

        System.out.println("AopTransaction 环绕通知：调用方法之前执行。。。");
        // 如果这里抛出异常，之后的代码不会被执行
        proceedingJoinPoint.proceed();
        System.out.println("AopTransaction 环绕通知：调用方法之后执行。。。");
        commit(myAnnotation);
    }

    private MyAnnotation getMethodAnnotation(ProceedingJoinPoint pjp) throws Exception {

        // 获取代理对象的方法
        String methodName = pjp.getSignature().getName();

        // 获取目标对象
        Class<?> classTarget = pjp.getTarget().getClass();

        // 获取目标对象类型
        Class<?>[] par = ((MethodSignature) pjp.getSignature()).getParameterTypes();

        // 获取目标对象方法
        Method objMethod = classTarget.getMethod(methodName, par);

        //获取该方法上的注解
        return objMethod.getDeclaredAnnotation(MyAnnotation.class);
    }

    /**
     * 开启事务
     */
    private void begin(MyAnnotation annotation) {
        if (annotation != null) {
            myTransaction.begin();
        }
    }

    public void commit(MyAnnotation annotation){
        if (annotation != null){
            myTransaction.commit();
        }
    }

    public void rollback(){
        if (myTransaction != null){
            myTransaction.rollback();
        }
    }
}
