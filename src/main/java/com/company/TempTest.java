package com.company;

import com.IM.Process.IMCompanyTest;
import com.IM.entry.IMCompanyTestEntry;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by ZZ on 2017/6/18.
 */
public class TempTest {
    public static int findSum(int m,int n){
        int sum=0;
        for(int i=m;i<=n;i++){
            sum+=i;
        }
        return sum;
    }

    public static int test2(){
        return (~10);
    }

    /**
     * short and byte
     */
    public static void test3(){
        short a =128;
        byte b =(byte) a;
        System.out.println(a + ""+b);
    }

    public static void doSomething(Integer integer){
        integer=new Integer(2);
    }

    public static void main(String[] args) {
        Integer var1=new Integer(1);
        Integer var2=var1;
        doSomething(var2);
        System.out.print(var1.intValue());
        System.out.print(var1==var2);
        test3();

        System.out.println(String.format("%.2f", (40 / 13 * 100.0)));

        JmeterTestIMCompanyTest();

    }

    public static void JmeterTestIMCompanyTest(){
        String json2 =
                "{'CompanyId': '0C00000ECR','PeriodEndDate': '2016-09-30'," +
                        "'NumberOfMonth': '3','FiscalYearEnd': '2016-12-31'," +
                        "'IsCalcOrCopy': '0','Period': 'A','Currency': 'USD'," +
                        "'UnitOfReport': '1000000','RawEndDate': '2016-12-31'," +
                        "'FormType': 'Quarterly','FileDate': '2016-12-31'," +
                        "'AccessionNumber': '0001193125-13-108628'," +
                        "'PeriodLength': '3','PeriodLengthType': 'Months'," +
                        "'ReportType': 'A','TemplateCode': 'B'," +
                        "'DwFilingId': '58184451','AvgParDate': '2016-12-31','IsBestKnown': '1'," +
                        "'TypeOfPeriod': '2'," +
                        "'BRRSpot': {'EquityBRRSpot_Groups': 'Bank Spot','OMBSBK0002': '1162614000.000000'," +
                        "'OMBSBK0002CalcMark': '0','OMBSBK0003': '2737979000.000000'," +
                        "'OMBSBK0003CalcMark': '0','OMBSBK0004': '566443000.000000'," +
                        "'OMBSBK0004CalcMark': '0','OMBSBK0005': '3304422000.000000'," +
                        "'OMBSBK0005CalcMark': '0','OMBSBK0006': '25580597000.000000'," +
                        "'OMBSBK0006CalcMark': '0','OMBSBK0007': 'NULL','OMBSBK0007CalcMark': '0'," +
                        "'OMBSBK0009': 'NULL','OMBSBK0009CalcMark': '0','OMBSBK0010': '0.110000'," +
                        "'OMBSBK0010CalcMark': '0','OMBSBK0011': '0.13','OMBSBK0011CalcMark': '0'," +
                        "'OMBSBK0012': 'NULL','OMBSBK0012CalcMark': '0','OMBSBK0013': 'NULL'," +
                        "'OMBSBK0013CalcMark': '0','OMBSBK0014': '0.0223','OMBSBK0014CalcMark': '0'}," +
                        "'FFODuration': {'EquityFFODuration_Groups': 'REIT/Investment Duration'," +
                        "'OMCF000002': '68298000.000000','OMCF000002CalcMark': '0'," +
                        "'OMCF000003': '69139000.000000','OMCF000003CalcMark': '0'," +
                        "'OMCF000006': 'NULL','OMCF000006CalcMark': '0','OMCF000007': 'NULL'," +
                        "'OMCF000007CalcMark': '0'}," +
                        "'BRRDuration': {'EquityBRRDuration_Groups': 'Bank Duration','OMISBK0001': '0.0128','OMISBK0001CalcMark': '0','OMISBK0002': '0.0095','OMISBK0002CalcMark': '0'}," +
                        "'NAVSpot': {'EquityNAVSpot_Groups': 'REIT/Investment Duration','IFBS003139': '58884000000.000000','IFBS003139CalcMark': '0','IFBS003141': '7982931000.000000','IFBS003141CalcMark': '0'}," +
                        "'ZipFilePath':'D:\\QAData\\off\\data\\atlasfeed\\GEDF2.0\\Monthly\\USA\\Fundamental','Monthly_IndustryMetricsBestKnown': 'Monthly_IndustryMetricsBestKnown_2018-05.zip','Monthly_IndustryMetricsFinalFirstKnown':'Monthly_IndustryMetricsFinalFirstKnown_2018-05.zip','Monthly_IndustryMetricsFinalLastKnown': 'Monthly_IndustryMetricsFinalLastKnown_2018-05.zip','Monthly_IndustryMetricsPrelimFirstKnown':'Monthly_IndustryMetricsPrelimFirstKnown_2018-05.zip','Monthly_IndustryMetricsPrelimLastKnown':'Monthly_IndustryMetricsPrelimLastKnown_2018-05.zip','ResultPath': 'D:\\QA\\PBFeed\\IM\\Result'}";
        IMCompanyTest imCompanyTest = new IMCompanyTest(json2);
        String result = imCompanyTest.verifyContent();
        System.out.println(result);
    }
}
