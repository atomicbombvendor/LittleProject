package com.example;

import com.example.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class SpringdemoApplication {

    public static void main(String[] args) {
//        SpringApplication.run(SpringdemoApplication.class, args);

        SpringApplication application = new SpringApplication(SpringdemoApplication.class);
        ConfigurableApplicationContext context = application.run(args);
        UserService userService = context.getBean(UserService.class);
        userService.add();
    }

}
