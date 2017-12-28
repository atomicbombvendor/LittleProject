package com.company.DesignMode.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eli9 on 8/28/2017.
 * 被观察的对象，他要依赖所有观察者，当这个被观察者发生变化后，自己通知观察者。
 */
public class Subject {
    private List<Observer> observers = new ArrayList<>();
    private int state;

    public int getState(){
        return state;
    }

    public void setState(int state){
        this.state = state;
        notifyAllObserver();
    }

    public void attach(Observer observer){
        observers.add(observer);
    }

    public void remove(Observer observer){
        observers.remove(observer);
    }

    public void notifyAllObserver(){
        observers.stream().forEach(o -> o.update());
    }
}
