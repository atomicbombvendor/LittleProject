package com.company.testCase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by eli9 on 4/11/2017.
 */
public class DateTrans {
    public static void getDate() throws ParseException {
        String s = "2017-03-12";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        Date date = sdf.parse(s);
        int year = date.getYear();
        int month = date.getMonth();
        int day = date.getDate();
        System.out.println(year+"-"+month+"-"+day);
        System.out.println("不能直接使用get方法打印");
        System.out.println(sdf.format(date));
        System.out.println("使用 SimpleDateFormat 打印");
    }

    public static void getDateYear() throws ParseException {
        String s = "2017-03-12";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(s);
        System.out.println(date);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        System.out.println(c.get(Calendar.YEAR)+"-"+(c.get(Calendar.MONTH)+1)+"-"+c.get(Calendar.DAY_OF_MONTH));
    }

    public static void dateToLocalDate() throws ParseException {
        String s = "2017-03-12";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = sdf.parse(s);
        System.out.println(sdf2.format(date));

        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant,zone);
        LocalDate localDate = localDateTime.toLocalDate();
        System.out.println(localDate);
    }

    public static LocalDateTime getDateTimeFromNumber(Long num)
    {
        Long datePart = num / 1000000L;
        Long timePart = num % 1000000L;
        Integer year = Math.toIntExact(datePart / 10000L);
        datePart = datePart % 10000;
        Integer month = Math.toIntExact(datePart / 100);
        Integer day = Math.toIntExact(datePart % 100);
        Integer hr = Math.toIntExact(timePart / 10000L);
        timePart = timePart % 10000;
        Integer min = Math.toIntExact(timePart / 100);
        Integer sec = Math.toIntExact(timePart % 100);
        return LocalDateTime.of(year, month, day, hr, min, sec);
    }

    public static LocalDate getDateFromNumber(Integer num)
    {
        Integer year = Math.toIntExact(num / 10000L);
        num = num % 10000;
        Integer month = Math.toIntExact(num / 100);
        Integer day = Math.toIntExact(num % 100);
        return LocalDate.of(year, month, day);
    }

    public static void printLocalDate(){
        int date = 20170315;
        LocalDate localDate = getDateFromNumber(date);
        System.out.println(localDate.getYear()+"-"+localDate.getMonthValue()+"-"+localDate.getDayOfMonth());
    }
    public static void main(String[] args) throws ParseException {
        //getDate();
        //getDateYear();
        //dateToLocalDate();
        printLocalDate();
    }
}
