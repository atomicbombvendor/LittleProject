package com.company.DesignMode.ChainPatternDemo;

/**
 * Created by eli9 on 8/29/2017.
 */
public abstract class AbstractLogger {
    public static int INFO = 1;
    public static int DEBUG = 2;
    public static int ERROR = 3;
    protected int level;
    protected AbstractLogger logger;

    public void setSupertLogger(AbstractLogger superLogger){
        this.logger = superLogger;
    }

    public void logMsg(int level, String msg){
        if(this.level <= level){
            writeMsg(msg);
        }
        if(logger != null){
            logger.logMsg(level, msg);
        }
    }

    abstract protected void writeMsg(String msg);
}
