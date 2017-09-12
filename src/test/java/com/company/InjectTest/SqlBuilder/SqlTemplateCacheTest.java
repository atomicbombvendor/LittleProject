package com.company.InjectTest.SqlBuilder;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by eli9 on 9/8/2017.
 */
public class SqlTemplateCacheTest {
    @Test
    public void getSqlTemplate() throws Exception {
        Class clazz = CalcFinancialDataBalanceSheet.class;
        SqlTemplateCache.getSqlTemplate(clazz);

    }

}