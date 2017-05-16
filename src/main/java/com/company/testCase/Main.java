package com.company.testCase;

public class Main {

    public static void main(String[] args) {
	// write your code here
        String URL_DATALOAD = "http://price.xoi.morningstar.com/DataPlatform/DataOutput" +
                ".aspx?Package=HistoricalData&ContentType=MarketPrice&IdType=PerformanceId&Id=%s&Dates=%s";
        System.out.println(String.format(URL_DATALOAD, "idTest", String.format("%s,%s","2014","2015")));
        //http://price.xoi.morningstar.com/DataPlatform/DataOutput
        // .aspx?Package=HistoricalData&ContentType=MarketPrice&IdType=PerformanceId
        // &Id=0P0000O31M&Dates=2013,2017

        double f = 62.0099983215332;
        System.out.println(String.format("%16.16f",f));
    }
}
