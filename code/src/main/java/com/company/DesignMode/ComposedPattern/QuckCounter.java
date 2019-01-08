package com.company.DesignMode.ComposedPattern;

public class QuckCounter implements Quackable {

    private Quackable duck;
    private static int duck_count;

    public QuckCounter(Quackable duck){
        this.duck = duck;
    }

    @Override
    public void Quack() {
        duck.Quack();
        duck_count ++;
    }

    public static int getDuck_count(){
        return duck_count;
    }

    @Override
    public void registerObserver(Observer observer) {
        duck.registerObserver(observer);
    }

    @Override
    public void notifyObservers() {

    }
}
