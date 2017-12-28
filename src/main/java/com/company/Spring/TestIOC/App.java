package com.company.Spring.TestIOC;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by eli9 on 8/24/2017.
 */
public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "application.xml");
        Animal animal = (Animal) context.getBean("cat");
        animal.say();
        ((Cat)animal).getFather();
    }
}
