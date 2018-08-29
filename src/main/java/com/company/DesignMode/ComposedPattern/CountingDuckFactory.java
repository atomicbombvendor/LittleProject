package com.company.DesignMode.ComposedPattern;

public class CountingDuckFactory extends AbstractDuckFactory {
    @Override
    public Quackable createMallardDuck() {
        return new QuckCounter(new MallardDuck());
    }

    @Override
    public Quackable createRedheadDuck() {
        return new QuckCounter(new RedheadDuck());
    }

    @Override
    public Quackable createDuckCall() {
        return new QuckCounter(new DuckCall());
    }

    @Override
    public Quackable createRubberDuck() {
        return new QuckCounter(new RubberDuck());
    }
}
