package com.company.DesignMode.observer;

/**
 * Created by eli9 on 8/28/2017.
 */
public class HexaObserver extends Observer {

    public HexaObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("HexaObserver: "+this.subject.getState());
    }
}
//Observer have Subject, and Subject contains a list of Observer
//the two class have coupling relationship
