package com.company.DesignMode.ProxyDesign;

/**
 * Created by eli9 on 8/29/2017.
 */
public class ProxyDemo {
    public static void main(String[] args) {
        Image image = new ProxyImage("proxy");
        image.display();
    }
}
//代理模式 要求代理类A拥有一个真实的被代理的对象B
//适配器模式，为了让A类可以使用B的类的方法，将A类转换为B类