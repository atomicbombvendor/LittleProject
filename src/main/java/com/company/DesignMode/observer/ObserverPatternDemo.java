package com.company.DesignMode.observer;

/**
 * Created by eli9 on 8/28/2017.\
 */
public class ObserverPatternDemo {
    public static void main(String[] args) {
        Subject subject = new Subject();

        new HexaObserver(subject);
        new OctalObserver(subject);
        new BinaryObserver(subject);
        System.out.println("First State Change: 15");
        subject.setState(15);
        System.out.println("First State Change: 22");
        subject.setState(22);
    }
}
