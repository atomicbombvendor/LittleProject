package com.morningstar.Dao;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

/**
 * Created by eli9 on 3/31/2017.
 */
public class TestATest {
    @Test
    public void testSpring() throws Exception {
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:application.xml");
        System.out.println(ac == null);
        TestA A = (TestA)ac.getBean("TestA");
    }

}