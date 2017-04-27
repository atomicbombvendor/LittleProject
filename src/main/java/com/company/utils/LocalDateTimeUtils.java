package com.company.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eli9 on 4/13/2017.
 */
public class LocalDateTimeUtils {
    public static List<Integer> getDateList(LocalDate st, LocalDate ed){
        long gaps = getDayNum(st,ed);
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<=gaps; i++){
            list.add(parseDateToInt(st.plusDays(i)));
        }
        return list;
    }

    public static long getDayNum(LocalDate st, LocalDate ed){
        if(!st.isBefore(ed)){
            throw new RuntimeException("ST IS LESS THAN ED");
        }
        long stDays = st.toEpochDay();
        long edDays = ed.toEpochDay();
        long gapDays = edDays - stDays;
        return gapDays;
    }

    /**
     * 包括数字格式化，数字不足自动补零。如：8->008
     * @param localDate
     * @return
     */
    public static Integer parseDateToInt(LocalDate localDate){
        return Integer.parseInt(String.format("%4d%02d%02d",localDate.getYear(),localDate.getMonthValue(),localDate
                .getDayOfMonth()));
    }

    public static void main(String[] args) {
        LocalDate st = LocalDate.of(2016,03,10);
        LocalDate ed1 = LocalDate.of(2016,04,10);
        LocalDate ed2 = LocalDate.of(2017,03,12);
        LocalDate ed3 = LocalDate.of(2016,03,21);
        long days =  LocalDateTimeUtils.getDayNum(st,ed1);
        System.out.println("Days1: " + days);
        long days2 =  LocalDateTimeUtils.getDayNum(st,ed2);
        System.out.println("Days2: " + days2);

        List<Integer> list = LocalDateTimeUtils.getDateList(st,ed3);
        System.out.println(list.size());
        list.stream().forEach(i -> System.out.println(i));

//        List<Integer> list2 = LocalDateTimeUtils.getDateList(ed1,st);
//        System.out.println(list2.size());
//        list2.stream().forEach(i -> System.out.println(i));

        split10years();
    }

    /**
     * 划分一个年的时间段，每10年为间隔，划分
     *
     */
    public static void split10years(){
        LocalDate st = LocalDate.of(2008,02,04);
        LocalDate ed = LocalDate.of(2017,10,2);
        List<String> list = new ArrayList<>();
        int stYear = st.getYear();
        int edYear = ed.getYear();
        int period = 10;
        int diff = edYear - stYear + 1;
        int interval = diff % period == 0 ? diff / period : (diff / period) + 1;
        for (int i = 0; i < interval; i++) {
            LocalDate stTmp = st.plusYears(i * period);
            LocalDate edTmp = st.plusYears(i * period + 9);
            if (edTmp.getYear() >= ed.getYear()) {
                edTmp = ed;
            }
            list.add(String.format("%s %s", stTmp.toString(), edTmp.toString()));
        }
        list.forEach(s -> System.out.println(s));

        LocalDate d1 = LocalDate.of(2012,12,29);
        LocalDate d2 = d1.plusDays(12);
        d2 = d1;
        System.out.println(d2.toString());
    }
}
