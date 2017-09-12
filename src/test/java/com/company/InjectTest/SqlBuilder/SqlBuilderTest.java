package com.company.InjectTest.SqlBuilder;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by eli9 on 9/12/2017.
 */
public class SqlBuilderTest {
    @Test
    public void buildUpdate() throws Exception {
        List<CalcFinancialDataBalanceSheet> list = getBSList();

        SqlBuilder builder = new SqlBuilder();
        String result = builder.buildUpdate(list);
        System.out.println(result);
    }

    private List<CalcFinancialDataBalanceSheet> getBSList(){
        List<CalcFinancialDataBalanceSheet> list = new ArrayList<>();

        CalcFinancialDataBalanceSheet bs1 = new CalcFinancialDataBalanceSheet();
        bs1.setPerformanceId("0P000002RH");
        bs1.setReportKey(123454543L);
        bs1.setoRBSZ00001(0.1d);
        bs1.setORBSZ00002(0.2d);
        bs1.setORBSZ00003(0.3d);
        bs1.setORBSZ00004(0.4d);
        bs1.setORBSZ00006(0.6d);

        CalcFinancialDataBalanceSheet bs2 = new CalcFinancialDataBalanceSheet();
        bs2.setPerformanceId("0P000002RH");
        bs2.setReportKey(123454543L);
        bs2.setoRBSZ00001(0.1d);
        bs2.setORBSZ00002(0.2d);
        bs2.setORBSZ00003(0.3d);
        bs2.setORBSZ00004(0.4d);
        bs2.setORBSZ00006(0.6d);

        list.add(bs1); list.add(bs2);

        return list;
    }
}