package com.company.ClassExtends;

/**
 * Created by eli9 on 6/29/2017.
 */
public class ClassB {
    public static void main(String[] args) {
        System.out.println(new B().getValue());
    }
    static class A{
        protected int value;
        public A(int v){
            //由于子类覆盖了父类的setValue方法，所以这个setValue会调用子类的setValue
            setValue(v);
        }
        public void setValue(int v){
            this.value = v;
        }
        public int getValue(){
            try{
                value++;
                //返回的value会先保存在临时栈中，执行finally后返回临时栈中的value.
                return value;
            }finally {
                //this指调用的B的对象
                this.setValue(value);
                System.out.println(value);
            }
        }
    }
    static class B extends A{
        public B(){
            super(5);
            //由于子类覆盖了父类的setValue方法，
            setValue(getValue() -3);
        }
        public void setValue(int value){
            //返回调用父类的setValue
            super.setValue(2*value);
        }
    }
}
