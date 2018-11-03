package com.company.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;

/**
 * Created by atomic on 8/22/2017.
 */
public class DateTimeUtils {
    public static DateTimeFormatter yyyy_MM_dd = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static DateTimeFormatter yyyy_MM_dd_HH_mm_ss = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static DateTimeFormatter yyyy_MM_dd_HH_mm_ss_S = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
    public static DateTimeFormatter yyyy_MM_dd_HH_mm_ss_SSS = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    public static DateTimeFormatter EEE_dd_MMM_yyyy_HH_mm_ss_GMT = DateTimeFormatter.ofPattern("EEE, dd-MMM-yyyy HH:mm:ss 'GMT'", Locale.US);

    public static LocalDateTime getDate(Date date){
        return getDate(date, ZoneId.systemDefault());
    }

    public static LocalDateTime getDate(Date date, ZoneId zoneId){
        return date.toInstant().atZone(zoneId).toLocalDateTime();
    }

    public static String format(Date date, DateTimeFormatter format, ZoneId zoneId){
        return getDate(date, zoneId).format(format);
    }

    public static String format(Date date, DateTimeFormatter format){
        return format(date, format, ZoneId.systemDefault());
    }

    public static Date parse(String string, DateTimeFormatter format){
        return parse(string, format, ZoneId.systemDefault());
    }

    public static Date parse(String string, DateTimeFormatter format, ZoneId zoneId){
        return Date.from(LocalDateTime.parse(string, format).atZone(zoneId).toInstant());
    }

    public static LocalDateTime getNow()
    {
        return LocalDateTime.now();
    }

    public static Integer getNowDateNumber()
    {
        LocalDateTime now = getNow();
        return getDateNumber(now.toLocalDate());
    }

    public static Long getNowNumber()
    {
        LocalDateTime now = getNow();
        return getDateTimeNumber(now);
    }

    public static LocalDateTime getUtcNow()
    {
        return ZonedDateTime.now(ZoneOffset.UTC).toLocalDateTime();
    }

    public static Long getUtcNowNumber()
    {
        LocalDateTime now = getUtcNow();
        return getDateTimeNumber(now);
    }

    public static Integer getDateNumber(LocalDate dt)
    {
        return dt.getYear() * 10000 + dt.getMonthValue() * 100 + dt.getDayOfMonth();
    }

    public static Long getDateTimeNumber(LocalDateTime dt)
    {
        Long datePart = dt.getYear() * 10000L + dt.getMonthValue() * 100 + dt.getDayOfMonth();
        Long timePart = dt.getHour() * 10000L + dt.getMinute() * 100 + dt.getSecond();
        return datePart * 1000000 + timePart;
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

    /**
     * Seconds of dt2 - dt1
     * @param dt1
     * @param dt2
     * @return
     */
    public static Long getSecondsDiff(Long dt1, Long dt2)
    {
        LocalDateTime t1 = getDateTimeFromNumber(dt1);
        LocalDateTime t2 = getDateTimeFromNumber(dt2);

        return ChronoUnit.SECONDS.between(t1, t2);
    }

    /**
     * Minutes of dt2 - dt1
     * @param dt1
     * @param dt2
     * @return
     */
    public static Long getMinutesDiff(Long dt1, Long dt2)
    {
        LocalDateTime t1 = getDateTimeFromNumber(dt1);
        LocalDateTime t2 = getDateTimeFromNumber(dt2);

        return ChronoUnit.MINUTES.between(t1, t2);
    }

    /**
     * Hours of dt2 - dt1
     * @param dt1
     * @param dt2
     * @return
     */
    public static Long getHoursDiff(Long dt1, Long dt2)
    {
        LocalDateTime t1 = getDateTimeFromNumber(dt1);
        LocalDateTime t2 = getDateTimeFromNumber(dt2);

        return ChronoUnit.HOURS.between(t1, t2);
    }

    /**
     * Days of dt2 - dt1
     * @param dt1
     * @param dt2
     * @return
     */
    public static Integer getDaysDiff(Long dt1, Long dt2)
    {
        LocalDateTime t1 = getDateTimeFromNumber(dt1);
        LocalDateTime t2 = getDateTimeFromNumber(dt2);

        return Math.toIntExact(ChronoUnit.DAYS.between(t1, t2));
    }

    /**
     * Months of dt2 - dt1
     * @param dt1
     * @param dt2
     * @return
     */
    public static Integer getMonthsDiff(Long dt1, Long dt2)
    {
        LocalDateTime t1 = getDateTimeFromNumber(dt1);
        LocalDateTime t2 = getDateTimeFromNumber(dt2);

        return Math.toIntExact(ChronoUnit.MONTHS.between(t1, t2));
    }

    /**
     * Years of dt2 - dt1
     * @param dt1
     * @param dt2
     * @return
     */
    public static Integer getYearsDiff(Long dt1, Long dt2)
    {
        LocalDateTime t1 = getDateTimeFromNumber(dt1);
        LocalDateTime t2 = getDateTimeFromNumber(dt2);

        return Math.toIntExact(ChronoUnit.YEARS.between(t1, t2));
    }


    /**
     * Days of d2 - d1
     * @param d1
     * @param d2
     * @return
     */
    public static Integer getDaysDiff(Integer d1, Integer d2)
    {
        LocalDate t1 = getDateFromNumber(d1);
        LocalDate t2 = getDateFromNumber(d2);

        return Math.toIntExact(ChronoUnit.DAYS.between(t1, t2));
    }

    /**
     * Months of d2 - d1
     * @param d1
     * @param d2
     * @return
     */
    public static Integer getMonthsDiff(Integer d1, Integer d2)
    {
        LocalDate t1 = getDateFromNumber(d1);
        LocalDate t2 = getDateFromNumber(d2);

        return Math.toIntExact(ChronoUnit.MONTHS.between(t1, t2));
    }

    /**
     * Years of d2 - d1
     * @param d1
     * @param d2
     * @return
     */
    public static Integer getYearsDiff(Integer d1, Integer d2)
    {
        LocalDate t1 = getDateFromNumber(d1);
        LocalDate t2 = getDateFromNumber(d2);

        return Math.toIntExact(ChronoUnit.YEARS.between(t1, t2));
    }

    /**
     * Calc new datetime with adding seconds
     * @param dt
     * @param seconds
     * @return
     */
    public static Long addSeconds(Long dt, Long seconds)
    {
        LocalDateTime t = getDateTimeFromNumber(dt);
        t = t.plusSeconds(seconds);
        return getDateTimeNumber(t);
    }

    /**
     * Calc new datetime with adding seconds
     * @param dt
     * @param seconds
     * @return
     */
    public static Long addSeconds(Long dt, Integer seconds)
    {
        LocalDateTime t = getDateTimeFromNumber(dt);
        t = t.plusSeconds(seconds);
        return getDateTimeNumber(t);
    }

    /**
     * Calc new datetime with adding minutes
     * @param dt
     * @param minutes
     * @return
     */
    public static Long addMinutes(Long dt, Long minutes)
    {
        LocalDateTime t = getDateTimeFromNumber(dt);
        t = t.plusMinutes(minutes);
        return getDateTimeNumber(t);
    }

    /**
     * Calc new datetime with adding minutes
     * @param dt
     * @param minutes
     * @return
     */
    public static Long addMinutes(Long dt, Integer minutes)
    {
        LocalDateTime t = getDateTimeFromNumber(dt);
        t = t.plusMinutes(minutes);
        return getDateTimeNumber(t);
    }

    /**
     * Calc new datetime with adding hours
     * @param dt
     * @param hours
     * @return
     */
    public static Long addHours(Long dt, Long hours)
    {
        LocalDateTime t = getDateTimeFromNumber(dt);
        t = t.plusHours(hours);
        return getDateTimeNumber(t);
    }

    /**
     * Calc new datetime with adding hours
     * @param dt
     * @param hours
     * @return
     */
    public static Long addHours(Long dt, Integer hours)
    {
        LocalDateTime t = getDateTimeFromNumber(dt);
        t = t.plusHours(hours);
        return getDateTimeNumber(t);
    }

    /**
     * Calc new datetime with adding days
     * @param dt
     * @param days
     * @return
     */
    public static Long addDays(Long dt, Integer days)
    {
        LocalDateTime t = getDateTimeFromNumber(dt);
        t = t.plusDays(days);
        return getDateTimeNumber(t);
    }

    /**
     * Calc new datetime with adding months
     * @param dt
     * @param months
     * @return
     */
    public static Long addMonths(Long dt, Integer months)
    {
        LocalDateTime t = getDateTimeFromNumber(dt);
        t = t.plusMonths(months);
        return getDateTimeNumber(t);

    }

    /**
     * Calc new datetime with adding years
     * @param dt
     * @param years
     * @return
     */
    public static Long addYears(Long dt, Integer years)
    {
        LocalDateTime t = getDateTimeFromNumber(dt);
        t = t.plusYears(years);
        return getDateTimeNumber(t);
    }

    /**
     * Calc new date with adding days
     * @param d
     * @param days
     * @return
     */
    public static Integer addDays(Integer d, Integer days)
    {
        LocalDate t = getDateFromNumber(d);
        t = t.plusDays(days);
        return getDateNumber(t);
    }

    /**
     * Calc new date with adding months
     * @param d
     * @param months
     * @return
     */
    public static Integer addMonths(Integer d, Integer months)
    {
        LocalDate t = getDateFromNumber(d);
        t = t.plusMonths(months);
        return getDateNumber(t);
    }

    /**
     * Calc new date with adding years
     * @param d
     * @param years
     * @return
     */
    public static Integer addYears(Integer d, Integer years)
    {
        LocalDate t = getDateFromNumber(d);
        t = t.plusYears(years);
        return getDateNumber(t);
    }

    /**
     * Get month end of the input
     * @param d
     * @return
     */
    public static Integer getMonthend(Integer d) {
        LocalDate t = getDateFromNumber(d);
        t = LocalDate.of(t.getYear(), t.getMonth(), 1).plusMonths(1).minusDays(1);
        return getDateNumber(t);
    }

    /**
     * Get month end of the input date after adjust with addMonths
     * @param d
     * @param addMonths
     * @return getMonthend(d.addMonths(addMonths))
     */
    public static Integer getMonthend(Integer d, Integer addMonths){
        d = addMonths(d, addMonths);
        return getMonthend(d);
    }

    /**
     * Parse LocalDate from string
     * @param string
     * @param format
     * @return
     */
    public static LocalDate getLocalDate(String string, DateTimeFormatter format)
    {
        LocalDate t = LocalDate.parse(string,format);
        return t;
    }

    /**
     * Parse LocalDate from string
     * @param string
     * @param format
     * @return
     */
    public static LocalDateTime getLocalDateTime(String string, DateTimeFormatter format)
    {
        LocalDateTime t = LocalDateTime.parse(string,format);
        return t;
    }

    /**
     * Format date to yyyy-MM-dd
     * @param date
     * @return
     */
    public static String toString(Integer date)
    {
        return getDateFromNumber(date).format(yyyy_MM_dd);
    }

    /**
     * Format date to yyyy-MM-dd
     * @param date
     * @return
     */
    public static String toString(LocalDate date)
    {
        return date.format(yyyy_MM_dd);
    }
}
