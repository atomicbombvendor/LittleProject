package com.company.Entity;

/**
 * Created by eli9 on 9/27/2017.
 */
public class Son extends Person<String> {
    //子类继承了父类的方法，但是没有继承属性；子类必须重写（覆盖）父类的方法
    //子类因为没有继承父类的私有属性，如果要使用父类的属性，就要使用父类的Getter Setter方法
    public String getName() {
        return super.getName();
    }

    public void setName(String name) {
        super.setName(name);
    }

}
