package com.company.DesignMode.ComposedPattern;

import headfirst.designpatterns.strategy.Quack;

public class DuckSimulator {

    public static void main(String[] args) {
        DuckSimulator simulator = new DuckSimulator();
        AbstractDuckFactory duckFactory = new CountingDuckFactory();

        simulator.simulate(duckFactory);

    }

    void simulate(AbstractDuckFactory duckFactory){
        Quackable mallardDuck = duckFactory.createMallardDuck();
        Quackable redheadDuck = duckFactory.createRedheadDuck();
        Quackable duckcall = duckFactory.createDuckCall();
        Quackable rubberDuck = duckFactory.createRubberDuck();
        Quackable gooseDuck = new GooseAdapter(new Goose());

        Flock flockOfDucks = new Flock();
        flockOfDucks.add(redheadDuck);
        flockOfDucks.add(duckcall);
        flockOfDucks.add(rubberDuck);
        flockOfDucks.add(gooseDuck);
        flockOfDucks.add(mallardDuck);

        Flock flockOfMallards = new Flock();
        Quackable mallardsOne = duckFactory.createMallardDuck();
        Quackable mallardsTwo = duckFactory.createMallardDuck();
        Quackable mallardsThree = duckFactory.createMallardDuck();
        Quackable mallardsFour = duckFactory.createMallardDuck();
        flockOfMallards.add(mallardsOne);
        flockOfMallards.add(mallardsTwo);
        flockOfMallards.add(mallardsThree);
        flockOfMallards.add(mallardsFour);

        flockOfDucks.add(flockOfMallards);
        System.out.println("\nDuck Simulator: whole Flock Simulation");
        simulate(flockOfDucks);

        System.out.println("\nDuck Simulator: Mallard Flock Simulation");
        simulate(flockOfMallards);

        System.out.println(QuckCounter.getDuck_count());

        System.out.println("\nDuck Simulator: With Observer");
        QuackLogist logist = new QuackLogist();
        flockOfDucks.registerObserver(logist);
        simulate(flockOfDucks);
    }

    void simulate(Quackable duck){
        duck.Quack();
    }
}
