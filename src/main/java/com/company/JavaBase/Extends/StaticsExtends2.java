package com.company.JavaBase.Extends;

import com.company.DesignMode.FactoryDesign.FatherFactory;
import com.company.DesignMode.ProxyDesign.Image;
import com.company.InjectTest.Person;

/**
 * Created by eli9 on 9/6/2017.
 */
public class StaticsExtends2 extends Person implements FatherFactory {

    public static void main(String[] args) {
        C c = new C();
        System.out.println(c.nonStaticStr);//B non-static field
        System.out.println(c.staticStr);//B static field
        c.staticMethod();//B static method
        //说明B类覆盖了父类的静态方法和静态属性，同时C类继承与B类，同时继承了B类的静态方法和属性
        System.out.println("-------------");

        A A = new C();
        System.out.println(A.nonStaticStr);//Father nonstatic field
        System.out.println(((C)A).nonStaticStr);//B non-static field
        System.out.println(A.staticStr);//Father static field
        A.staticMethod();//Father static method
        //说明用C类初始化原始父类，在调用静态属性和静态方法时，仍然调用的是原始父类的静态方法和静态属性
        //静态方法和静态属性不会因为子类的对象就调用子类的，而是和左边的类有关。
        //A的实例中包含 (A.nonStaticStr) 和 (nonStaticStr),如何调用见代码的17 18

        System.out.println("------------");
        B b = new B();
        System.out.println(b.nonStaticStr);//B non-static field
        System.out.println(b.staticStr);//B static field
        b.staticMethod();//B static method

        System.out.println("------------");
        A b1 = new B();
        System.out.println(b1.nonStaticStr);//A's nonstatic field
        System.out.println(b1.staticStr);//A's static field
        b1.staticMethod();//A's static method
        //b1.nonStaticStr  输出的是父类的非静态属性，说明非静态属性不可以被重写，不能实现多态
        //b1.staticStr 输出的是父类的静态属性，说明静态属性不可以被重写，不能实现多态
        //b1.staticMethod 输出的是父类的静态方法，不是子类B改写后的，所以没有实现多态

        //结论是 静态属性和静态放只是可以继承，没有表现出多态性
        //因为静态方法和静态属性没有采用动态绑定，具体的体现就是，
        //将子类实例向上转型则会调用基类中的静态方法和静态属性，
        //不转型就会调用子类字符的静态方法和静态属性，
        //编译器不推荐通过实例去调用静态方法和属性，因为这种调用方式容易混淆。

        //实际上在Java的规范中，Java对于类的方法和属性蚕蛹了完全两种不同的处理机制，
        //对于方法，使用了重载机制实现了多态性；对于属性，实现的是同名属性隐藏机制。
        //所谓的同名属性隐藏机制是指：在具有父子关系的两个类中，
        //子类中相同名字的属性会使得从父类中继承过来的同名属性变得不可见，
        //不管类型是否一致，名称一致的两个属性就是同名属性。
        //在子类中，无法简单的通过属性名称来获取父类中的属性。
        //而是必须通过父类名称加上属性名称（super.变量名）的方法才可以访问父类中的属性
        //一般而言，为了代码容易阅读，及其不建议在父类和子类中使用同名属性
        long i = 0xfff;
    }

    @Override
    public void build() {

    }
}

class A {
    public static String staticStr = "A's static field";
    public String nonStaticStr = "A's nonstatic field";
    public static void staticMethod(){
        System.out.println("A's static method");
    }
    public void nonStaticMethod() {
        System.out.println("A's nonstatic method");
    }
}

class B extends A{
    public static String staticStr = "B static field";
    public String nonStaticStr = "B non-static field";
    public static void staticMethod(){
        System.out.println("B static method");
    }
}
class C extends B{}


