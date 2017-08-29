package com.company.DesignMode.ChainPatternDemo;

/**
 * Created by eli9 on 8/29/2017.
 */
public class FileLogger extends AbstractLogger {

    public FileLogger(int level){
        this.level = level;
    }

    @Override
    protected void writeMsg(String msg) {
        System.out.println("File::Logger: " + msg);
    }
}
