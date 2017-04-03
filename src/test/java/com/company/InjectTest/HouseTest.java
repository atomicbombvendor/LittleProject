package com.company.InjectTest;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by eli9 on 4/3/2017.
 */
public class HouseTest {
    @Test
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:application.xml");
        House house = (House) ac.getBean("house"); //默认的名字是小写的类名
//        System.out.println("Null? " + house.getPerson().getName().equals(null));
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:application.xml");

        //House house = (House)ac.getBean("house");
        Person p = house.getPerson();
        p.setName("张三");
        System.out.println(house.getPerson().getName());
        House house2 = (House) ac.getBean("House");
    }
}