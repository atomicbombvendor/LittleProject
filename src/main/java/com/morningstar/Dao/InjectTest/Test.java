//package com.morningstar.Dao.InjectTest;
//
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
///**
// * Created by eli9 on 4/3/2017.
// */
//public class Test {
//    public static void main(String[] args) {
//        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:application.xml");
//
//        House house = (House)context.getBean("house");
//        Person p = house.getPerson();
//        p.setName("张三");
//        System.out.println(house.getPerson().getName());
//    }
//}
