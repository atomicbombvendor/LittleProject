package com.company.ReflectMethod;

import com.company.Dao.TestC;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CallMethod {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {

        // 获取TestClass的Class对象
        Class<TestClass> testClass = (Class<TestClass>) Class.forName(TestClass.class.getName());

        // 使用默认构造方法创建实例
        TestClass objectA = testClass.newInstance();
        System.out.println("Class的newInstance() 方法创建默认TestClass实例: "
                + objectA.toString());

        // 使用构造方法创建实例
        Constructor[] cons = testClass.getDeclaredConstructors();
        System.out.println("testClass有 " + cons.length + " 个构造方法");
        Constructor con;

        for (int i = 0; i < cons.length; i++) {
            con = cons[i];
            if (con.getParameterTypes().length == 0){
                objectA = (TestClass)con.newInstance();
                System.out.println("Constructor 的 newInstance() 方法创建默认TestClass实例: "
                                + objectA.toString());
            }else{
                objectA = (TestClass)con.newInstance(new Object[]{
                        new Integer(55), new Integer(88)
                });
                System.out.println("Constructor 的 newInstance() 方法创建带参数的TestClass实例: "
                                + objectA.toString());
            }
        }

        // 获取所有方法
        Method[] methods = testClass.getMethods();

        // 获取某个特定的无参数的方法
        Method saddMethod1 = testClass.getMethod("sadd", null);
        Method addMethod1 = testClass.getMethod("add", null);

        // 获取某个特定的有参数的方法
        Method saddMethod2 = testClass.getMethod("sadd", int.class, int.class);
        Method addMethod2 = testClass.getMethod("add", int.class, int.class);

        // 调用不带参数的静态方法
        int result = (Integer) saddMethod1.invoke(null, null);
        System.out.println("调用不带参数的静态方法sadd: " + result);

        // 调用带参数的静态方法
        int result2 = (Integer) saddMethod2.invoke(null, 30, 50);
        System.out.println("调用带参数30, 50的静态方法sadd: " + result2);

        objectA = (TestClass) testClass.newInstance();
        // 调用不带参数的实例方法
        int result3 = (Integer) addMethod1.invoke(objectA, null);
        System.out.println("调用不带参数的实例方法add: " + result3);

        // 调用带参数的实例方法
        int result4 = (Integer) addMethod2.invoke(objectA, 40, 80);
        System.out.println("调用带参数40, 80的实例方法add: " + result4);
    }

    static class TestClass{

        static int sa = 100;
        static int sb = 50;

        int a;
        int b;

        public TestClass(){

        }

        public TestClass(int a, int b){
            this.a = a;
            this.b = b;
        }

        public static int sadd(){
            return sa + sb;
        }

        public static int sadd(int a, int b){
            return a + b;
        }

        public int add(){
            return a + b;
        }

        public int add(int a, int b){
            return a + b;
        }

        public String toString(){
            return "a = " + this.a + "; b = " + this.b;
        }

        private int sub(){
            return this.a - b;
        }
    }
}
