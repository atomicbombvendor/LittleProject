package com.company.DesignMode.ChainPatternDemo;

/**
 * Created by eli9 on 8/29/2017.
 */
public class ErrorLogger extends AbstractLogger {

    public ErrorLogger(int level){
        this.level = level;
    }

    @Override
    protected void writeMsg(String msg) {
        System.out.println("Error Console::Logger: " + msg);
    }
}
