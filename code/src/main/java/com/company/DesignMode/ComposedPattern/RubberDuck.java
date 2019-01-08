package com.company.DesignMode.ComposedPattern;

public class RubberDuck implements Quackable {
    Observable observable;

    public RubberDuck(){
        this.observable = new Observable(this);
    }

    @Override
    public void Quack() {
        System.out.println("Squeak");
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
