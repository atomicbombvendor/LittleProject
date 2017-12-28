package com.company.DesignMode.CommonPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eli9 on 11/1/2017.
 */
public class Macro {

    private final List<Action> actions;

    public Macro(){
        actions = new ArrayList<>();
    }

    public void record(Action action){
        actions.add(action);
    }

    public void run(){
        actions.forEach(Action::perform);
    }

    public static void main(String[] args) {
        Editor editor = new EditorText();
        Macro m = new Macro();
        m.record(editor::open); //这是匿名类
        m.record(editor::save);
        m.record(editor::close);
        m.run();
    }
}
