package com.company.Spring.TestAOP;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by eli9 on 8/24/2017.
 */
public class TestAOP {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("application.xml");
        Calculation impl = (Calculation)ctx.getBean("calculationImpl");

        int result = impl.add(5,5);
        System.out.println(result);
    }
}
