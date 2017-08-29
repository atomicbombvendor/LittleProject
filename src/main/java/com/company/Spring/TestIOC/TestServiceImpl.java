package com.company.Spring.TestIOC;

import com.company.Dao.TestC;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * Created by eli9 on 8/24/2017.
 */
public class TestServiceImpl extends BaseServiceImpl {
    private TestC testc;

    public TestC getTestc() {
        return testc;
    }
    @Resource(name = "testDao")//注解
    public void setTestc(TestC testc) {
        this.testc = testc;
    }

    @PostConstruct
    //@PostConstruct是Java EE 5引入的注解，Spring允许开发者在受管Bean中使用它。当DI容器实例化当前受管Bean时，
    // @PostConstruct注解的方法会被自动触发，从而完成一些初始化工作
    public void initTestCBase(TestC testc){
        super.setTestC(testc);
    }
}
