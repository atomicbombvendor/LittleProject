package com.company;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by ZZ on 2017/6/18.
 */
public class TempTest {

    public static void main(String[] args) {
        String r = String.format("%d-%d",
                LocalDate.now().getYear(),
                LocalDate.now().minusMonths(1L).getMonthValue());
        System.out.println(r);

        String fileName = "Monthly_AverageCurrencyExchangeRate_YYYY-M";
        String r2 =
                LocalDate.now().minusMonths(1L).format(DateTimeFormatter.ofPattern("YYYY-M"));
        System.out.println(fileName.replace("YYYY-M", r2));
    }

}
