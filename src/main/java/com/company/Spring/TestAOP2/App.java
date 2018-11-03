package com.company.Spring.TestAOP2;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by atomic on 8/24/2017.
 */
public class App {

    ApplicationContext ac = new ClassPathXmlApplicationContext("TestAPO2.xml");

    @Test
    public void testAPP(){
        //IUserDao userDao = (IUserDao) ac.getBean("UserDao"); Error:No bean named 'UserDao' available
        //似乎会默认有相应的bean，id为类名的首字母小写
        IUserDao userDao = (IUserDao) ac.getBean("userDao");
        System.out.println(userDao.getClass());
        userDao.save();
    }

    @Test
    public void testCglib(){
        OrderDao orderDao = (OrderDao) ac.getBean("orderDao");
        System.out.println(orderDao.getClass());
        orderDao.save();
    }
}
