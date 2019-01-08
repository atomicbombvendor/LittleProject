package com.company.File;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by eli9 on 7/20/2017.
 */
public class ReadExcelTest {
    ReadExcel readExcel = new ReadExcel();
    @Test
    public void readXls() throws Exception {
    }

    @Test
    public void readXlxs() throws Exception {
            String fileName = "ExchangeList.xlsx";
            String path = ReadExcel.class.getClassLoader().getResource(fileName).getPath();
            List<List<String>> result = readExcel.readXlxs(path);
            readExcel.printList(result);
    }

    @Test
    public void printTaxRate(){
        String fileName = "TaxRate.xlsx";
        String path = ReadExcel.class.getClassLoader().getResource(fileName).getPath();
        try {
            XSSFSheet xssfSheet = readExcel.getXSSFSheet(path).get(0);
            List<List<String>> result = readExcel.getTaxRate(xssfSheet);
            readExcel.printList(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}