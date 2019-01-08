package com.company.DesignMode.ComposedPattern;

import java.util.ArrayList;
import java.util.Iterator;

public class Flock implements Quackable {
    ArrayList ducks = new ArrayList();

    public void add(Quackable duck) {
        ducks.add(duck);
    }

    @Override
    public void Quack() {
        Iterator iterator = ducks.iterator();
        while(iterator.hasNext()){
            Quackable quacker = (Quackable) iterator.next();
            quacker.Quack();
        }
    }

    @Override
    public void registerObserver(Observer observer) {
        Iterator iterator = ducks.iterator();
        while(iterator.hasNext()){
            Quackable quck = (Quackable) iterator.next();
            quck.registerObserver(observer);
        }
    }

    @Override
    public void notifyObservers() {

    }
}
