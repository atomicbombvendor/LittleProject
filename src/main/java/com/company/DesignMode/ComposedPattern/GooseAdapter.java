package com.company.DesignMode.ComposedPattern;

public class GooseAdapter implements Quackable {
    private Goose goose;
    Observable observable;

    public GooseAdapter(Goose goose){
        this.goose = goose;
        this.observable = new Observable(this);
    }

    @Override
    public void Quack() {
        goose.honk();
    }

    @Override
    public void registerObserver(Observer observer) {
        this.observable.registerObserver(observer);
    }

    @Override
    public void notifyObservers() {
        this.observable.notifyObservers();
    }
}
