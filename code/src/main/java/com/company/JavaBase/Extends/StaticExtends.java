package com.company.JavaBase.Extends;

import java.util.*;

/**
 * Created by eli9 on 9/6/2017.
 */
public class StaticExtends {

    //这是静态分派的过程，在编译时已经决定了使用super的方法，
    // 因为subToSuper 是指super对象，可是为什么会选择collection呢，
    // for循环出来他们实际上指的是collection对象表示的，即类似于Collection col = new  HashSet<>();
    // 这样传入方法getType（）中的参数就是col，左边是静态类型，右边是实际类型。
    // 由于重载实际上是使用静态分派的，重载时是通过参数的静态类型而不是实际类型作为判定依据的。
    // 详细参考深入理解java虚拟机248页解释。
    public static void main(String[] args) {
        Collection<?>[] collections =
                {new HashSet<String>(), new ArrayList<String>(), new HashMap<String, String>().values()};
        Super subToSuper = new Sub();//实际的对象时父类的对象，subToSuper不可使用子类的方法；
        //调用的是父类的方法，说明子类没有覆写父类的getType静态方法
        for(Collection<?> collection : collections){
            System.out.println(subToSuper.getType(collection));
        }
        //subToSuper.getSon(); 不能使用子类的方法
    }

    abstract static class Super{
        public static String getType(Collection<?> collection){
            return "Super:collection";
        }

        public static String getType(List<?> list){
            return "Super:list";
        }

        public static String getType(ArrayList<?> list){
            return "Super:arrayList";
        }

        public static String getType(Set<?> set){
            return "Super:set";
        }

        public static String getType(HashSet<?> set){
            return "Super:hashSet";
        }
    }
    static class Sub extends Super{
        public static String getType(Collection<?> collection){
            return "Sub";
        }
        public String getSon(){
            return "Sub Son";
        }
    }
}
