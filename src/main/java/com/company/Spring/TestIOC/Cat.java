package com.company.Spring.TestIOC;

import com.company.Entity.Father;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by atomic on 8/24/2017.
 */
@Component
public class Cat implements Animal {
    private String name;
    @Resource//（使用注解的方式）使用注解从配置文件中读取bean id为father的类
    private Father father;//（如果使用纯配置的方式）依赖于类；如果需要子类，在这里换成子类

    public void say() {
        System.out.println("I am " + name + "!");
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setFa(Father o){
        this.father = o;
    }

    public void getFather(){
        System.out.println(father.getClass());
    }
}
