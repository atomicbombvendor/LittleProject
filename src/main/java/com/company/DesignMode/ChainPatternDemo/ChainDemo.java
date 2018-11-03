package com.company.DesignMode.ChainPatternDemo;

/**
 * Created by atomic on 8/29/2017.
 */
public class ChainDemo {
    public static void main(String[] args) {
        AbstractLogger errorLogger = new ErrorLogger(AbstractLogger.ERROR);
        AbstractLogger fileLogger = new FileLogger(AbstractLogger.DEBUG);
        AbstractLogger consoleLogger = new ConsoleLogger(AbstractLogger.INFO);

        consoleLogger.setSupertLogger(fileLogger);
        fileLogger.setSupertLogger(errorLogger);

        consoleLogger.logMsg(AbstractLogger.INFO,
                "This is an information.");

        consoleLogger.logMsg(AbstractLogger.DEBUG,
                "This is an debug level information.");

        consoleLogger.logMsg(AbstractLogger.ERROR,
                "This is an error information.");
    }
}
//通过附中主动拥有自己的对象，设置子类之间的层级关系。
