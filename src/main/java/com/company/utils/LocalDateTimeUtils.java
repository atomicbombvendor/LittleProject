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
    }
}
