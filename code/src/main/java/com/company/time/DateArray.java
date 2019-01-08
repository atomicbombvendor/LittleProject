package com.company.time;

/**
 * Created by eli9 on 4/3/2017.
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;


public class DateArray {
    private Date start;

    private Date end;

    public DateArray(Date s, Date e){
        this.start = s;
        this.end = e;
    }

    public DateArray(){}
    /**
     * 得到在某个时间段的日期数组
     * 日期为8位整型20170401
     */
    public int[] getArray() throws ParseException {
        int numOfDays = getPeriodDaysFromDateMark(start);
        int[] datesArr = new int[numOfDays];

        Calendar cal = getDateWithHMSIsZero(start);
        for(int i=0; i<numOfDays; i++){
            datesArr[i] = parseDateToInt(cal.getTime());
            cal.add(Calendar.DAY_OF_MONTH,1);
        }
        return datesArr;
    }

    /**
     * Get days from dates to current date
     * @param dates
     * @return
     * @throws ParseException
     */
    public int getPeriodDaysFromDateMark(Date dates) throws ParseException {
        Calendar calst = getDateWithHMSIsZero(dates);
        Calendar caled = getDateWithHMSIsZero(new Date());

        return ((int)(caled.getTime().getTime()/1000) - (int)(calst.getTime().getTime()/1000))/3600/24 + 1;
    }

    public int parseDateToInt(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Integer value = Integer.parseInt(format.format(date));
        return value;
    }

    /**
     * Get calendar object which Hour Minute Second is zero
     * Example: 2017-04-04 00:00:00
     * @param date
     * @return
     */
    public Calendar getDateWithHMSIsZero(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);

        return calendar;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public static void main(String[] args) {
        DateArray a = new DateArray();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String s = "2017-03-26";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(a.getDateWithHMSIsZero(new Date()).getTime());
        System.out.println("Current Date: " + format.format(calendar.getTime()));
        try {
            Date sd = format.parse(s);
            a.setStart(sd);
            int[] array = a.getArray();
            System.out.println("size: " + array.length);
            Arrays.asList(array).forEach(t -> System.out.println(">"+t));
            for (int t:
                 array) {
                System.out.println(">" + t);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
