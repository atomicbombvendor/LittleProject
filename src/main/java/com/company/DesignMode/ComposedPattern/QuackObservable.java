package com.company.DesignMode.ComposedPattern;

public interface QuackObservable { //被观察者

    public void registerObserver(Observer observer);

    public void notifyObservers();
}
