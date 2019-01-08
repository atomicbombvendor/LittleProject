package com.company.JavaBase;

/**
 * Created by eli9 on 9/7/2017.
 */
abstract class AnonymousClass {
    private String name;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public abstract int fly();
}

public class AnonymousTest{
    public void test(AnonymousClass a){
        System.out.println(a.getName()+" || " + a.fly());
    }

    public static void main(String[] args){
        AnonymousTest test = new AnonymousTest();
        test.test(new AnonymousClass() {
            @Override
            public int fly() {
                return 233;
            }

            public String getName(){
                return "bird";
            }
        });

        new OuterClass().dispaly("xiaoming", "xiaoli");
    }
}

class OuterClass{
    public void dispaly(String name, String age){
        class InnerClass{
            void display(){
                System.out.println(name);
            }
        }
        new InnerClass().display();
    }
}

