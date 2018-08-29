package com.company.DesignMode.ComposedPattern;

public class RedheadDuck implements Quackable{
    Observable observable;

    public RedheadDuck(){
        this.observable = new Observable(this);
    }

    @Override
    public void Quack() {
        System.out.println("Quack");
        notifyObservers();
    }

    @Override
    public void registerObserver(Observer observer) {
        observable.registerObserver(observer);
    }

    @Override
    public void notifyObservers() {
        observable.notifyObservers();
    }
}
