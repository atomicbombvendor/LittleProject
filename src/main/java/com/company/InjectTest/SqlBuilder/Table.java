package com.company.InjectTest.SqlBuilder;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by eli9 on 9/8/2017.
 */
//在注解类A上加上注解B，这个注解B只为这个注解类A服务，B称为元注解。元注解有Rentention和Target
//对注解类的注解可以理解为类的属性
@Target(ElementType.TYPE)
//Target定义注解的作用目标；表示注解类应该在什么位置，对哪一块的数据有效。
//ElemenType.Type 接口 类 枚举 注解
@Retention(RetentionPolicy.RUNTIME)
//注解会在class字节码文件中存在，在与撇愚蠢可以通过反射获取到
//Rentention的3种取值意味让注解保留到哪个阶段，RententionPolicy.SOURCE、RententionPolicy.CLASS(默认值)、RententionPolicy.RUNTIME。
public @interface Table {
    String value();
    String[] primaryKey();
}
