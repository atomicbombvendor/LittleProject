package com.company.time;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by eli9 on 4/3/2017.
 */
public class CalendarTest {
    public static void getDiffTime(){
        System.out.println("getDiffTime");
        Calendar c = Calendar.getInstance();
        System.out.println("c.getTime(): " + c.getTime());
        System.out.println("getTimeInMills: " + c.getTimeInMillis());
        System.out.println("getTimeZone: " + c.getTimeZone());
    }

    public static void getDiffTimeWithTimeZone(){
        System.out.println("\ngetDiffTimeWithTimeZone");
        Calendar c = Calendar.getInstance();
        TimeZone chicago = TimeZone.getTimeZone("GMT-6");
        TimeZone beijing = TimeZone.getTimeZone("GMT+8");
        TimeZone london = TimeZone.getTimeZone("GMT");
        TimeZone.setDefault(london);

        System.out.println("c.getTime(): " + c.getTime());//print default timezone
        System.out.println("getTimeZone: " + c.getTimeZone());//print calendar timezone,
                                                            // default is system timezone
        System.out.println("Date: " + (c.get(Calendar.YEAR)*10000 + (c.get(Calendar.MONTH)+1)*100 + c.get
                (Calendar.DAY_OF_MONTH))+ " " + c.get(Calendar.HOUR)+":"+c.get(Calendar.MINUTE));
        //print calendar timezone
        // timezone

        c.setTimeZone(chicago);
        System.out.println("SetTimeZone c.getTime(): " + c.getTime());
        System.out.println("SetTimeZone getTimeZone: " + c.getTimeZone());
        System.out.println("Date: " + (c.get(Calendar.YEAR)*10000 + (c.get(Calendar.MONTH)+1)*100 + c.get
                (Calendar.DAY_OF_MONTH))+ " " + c.get(Calendar.HOUR)+":"+c.get(Calendar.MINUTE));
    }

    /**
     * getTime 得到的是默认时区的时间
     * getTimeZone 得到的是calendar的时区时间
     * Calendar 得到的也是Calendar的时区时间
     * 设置默认时区不会影响Calendar的时区时间
     * 要想得到某个时区的时间，可以通过设置defaultTimeZone,然后 getTime
     * 或者 (Calendar)c.setTimeZone(),然后 c.get(Calendar.HOUR)
     * @param args
     */
    public static void main(String[] args) {
        //CalendarTest.getDiffTime();
        CalendarTest.getDiffTimeWithTimeZone();
    }
}
