package com.company.Spring.TestAOP;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Arrays;
import java.util.List;

/**
 * Created by eli9 on 8/24/2017.
 */
@Aspect //声明注解
public class CalculationAnnotation {

    //@Before("execution(* cn.itcast.e_aop_anno.*.*(..))")
    //每个方法需要写相同的引用，所以将相同的部分抽取到一个空的方法中pointCut_(),
    @Pointcut("execution(* com.company.Spring.TestAOP.CalculationImpl.*(..))")
    public void pointCut_(){}

    /**
     * 定义前置通知
     * execution(* biz.UserBiz.*(..)) 表示  所有修饰符的所有返回值类型  biz.UserBiz 包下的所有方法
     * 在调用biz包下UserBiz类下的所有方法前自动调用before方法，实现前置通知。也就是说，当我符合调用规则的时候，
     * 先执行before方法，等before方法执行结束后执行目标方法，而且在before方法内，我可以用过JoinPoint拿到目标方法
     * 的所有参数，来进行方法的加强
     * 在方法执行之前执行
     * */
    @Before("pointCut_()")
    public void before(JoinPoint join){
        //get function name
        String mathName = join.getSignature().getName();
        //get args list
        List<Object> args = Arrays.asList(join.getArgs());

        System.out.println("front notify -> before function name is:"+mathName+"\targs is:"+args);
    }

    /**
     * 后置通知
     * 在方法返回后执行，无论是否发生异常
     * 不能访问到返回值
     *
     * */
    @After("pointCut_()")
    public void after(){
        System.out.println("back notify -> after....");
    }
}
