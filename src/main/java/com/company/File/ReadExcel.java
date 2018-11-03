package com.company.File;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by atomic on 7/20/2017.
 */
public class ReadExcel {

    public static List<List<String>> readXls(String path) throws Exception{
        InputStream is = new FileInputStream(path);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        List<List<String>> result = new ArrayList<>();

        for(int numSheet=0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);

            if (hssfSheet == null) {
                continue;
            }

            //遍历当前页，循环读取每一行
            List<List<String>> sheetResult = readHSSFSheet(hssfSheet);
            result.addAll(sheetResult);
        }
        return result;
    }

    public static List<List<String>> readHSSFSheet(HSSFSheet hssfSheet){
        List<List<String>> result = new ArrayList<>();
        //遍历当前页，循环读取每一行
        for(int rowNum=1; rowNum <= hssfSheet.getLastRowNum(); rowNum++){
            HSSFRow hssfRow = hssfSheet.getRow(rowNum);

            int minColIx = hssfRow.getFirstCellNum();//表示开始的列
            int maxColIx = hssfRow.getLastCellNum();//表示结束的列

            List<String> rowList = new ArrayList();

            HSSFCell ExchangeIdCell = hssfRow.getCell(1);
            String ExchangeId = getStringVal(ExchangeIdCell);
            rowList.add(ExchangeId);

            HSSFCell ExchangeGlobalIdCell = hssfRow.getCell(2);
            String ExchangeGlobalId = getStringVal(ExchangeGlobalIdCell);
            rowList.add(ExchangeGlobalId);

            HSSFCell MICCell = hssfRow.getCell(3);
            String MIC = getStringVal(MICCell);
            rowList.add(MIC);

            HSSFCell ExchangeNameCell = hssfRow.getCell(4);
            String ExchangeName = getStringVal(ExchangeNameCell);
            rowList.add(ExchangeName);

            HSSFCell CountryIdCell = hssfRow.getCell(5);
            String CountryId = getStringVal(CountryIdCell);
            rowList.add(CountryId);

            HSSFCell CountryNameCell = hssfRow.getCell(6);
            String CountryName = getStringVal(CountryNameCell);
            rowList.add(CountryName);

            HSSFCell RegionIdCell = hssfRow.getCell(7);
            String RegionId = getStringVal(RegionIdCell);
            rowList.add(RegionId);

            HSSFCell RegionNameCell = hssfRow.getCell(8);
            String RegionName = getStringVal(RegionNameCell);
            rowList.add(RegionName);

            HSSFCell ExchangeGroupIdCell = hssfRow.getCell(9);
            String ExchangeGroupId = getStringVal(ExchangeGroupIdCell);
            rowList.add(ExchangeGroupId);

            HSSFCell ExchangeGroupNameCell = hssfRow.getCell(10);
            String ExchangeGroupName = getStringVal(ExchangeGroupNameCell);
            rowList.add(ExchangeGroupName);

            HSSFCell BourseCodeCell = hssfRow.getCell(11);
            String BourseCode = getStringVal(BourseCodeCell);
            rowList.add(BourseCode);

            HSSFCell IsXOIReadyCell = hssfRow.getCell(12);
            String IsXOIReady = getStringVal(IsXOIReadyCell);
            rowList.add(IsXOIReady);

            HSSFCell ProductionExchangeIdCell = hssfRow.getCell(13);
            String ProductionExchangeId = getStringVal(ProductionExchangeIdCell);
            rowList.add(ProductionExchangeId);

            result.add(rowList);
        }
        return result;
    }

    public static List<List<String>> readXSSFSheet(XSSFSheet xssfSheet){
        List<List<String>> result = new ArrayList<>();
        //遍历当前页，循环读取每一行
        for(int rowNum=1; rowNum <= xssfSheet.getLastRowNum(); rowNum++){
            XSSFRow xssfRow = xssfSheet.getRow(rowNum);

            int minColIx = xssfRow.getFirstCellNum();//表示开始的列
            int maxColIx = xssfRow.getLastCellNum();//表示结束的列

            List<String> rowList = new ArrayList();

            XSSFCell ExchangeIdCell = xssfRow.getCell(0);
            String ExchangeId = getStringVal(ExchangeIdCell);
            rowList.add(ExchangeId);

            //从0开始。
            XSSFCell ExchangeGlobalIdCell = xssfRow.getCell(1);
            String ExchangeGlobalId = getStringVal(ExchangeGlobalIdCell);
            rowList.add(ExchangeGlobalId);

            XSSFCell MICCell = xssfRow.getCell(2);
            String MIC = getStringVal(MICCell);
            rowList.add(MIC);

            XSSFCell ExchangeNameCell = xssfRow.getCell(3);
            String ExchangeName = getStringVal(ExchangeNameCell);
            rowList.add(ExchangeName);

            XSSFCell CountryIdCell = xssfRow.getCell(4);
            String CountryId = getStringVal(CountryIdCell);
            rowList.add(CountryId);

            XSSFCell CountryNameCell = xssfRow.getCell(5);
            String CountryName = getStringVal(CountryNameCell);
            rowList.add(CountryName);

            XSSFCell RegionIdCell = xssfRow.getCell(6);
            String RegionId = getStringVal(RegionIdCell);
            rowList.add(RegionId);

            XSSFCell RegionNameCell = xssfRow.getCell(7);
            String RegionName = getStringVal(RegionNameCell);
            rowList.add(RegionName);

            XSSFCell ExchangeGroupIdCell = xssfRow.getCell(8);
            String ExchangeGroupId = getStringVal(ExchangeGroupIdCell);
            rowList.add(ExchangeGroupId);

            XSSFCell ExchangeGroupNameCell = xssfRow.getCell(9);
            String ExchangeGroupName = getStringVal(ExchangeGroupNameCell);
            rowList.add(ExchangeGroupName);

            XSSFCell BourseCodeCell = xssfRow.getCell(10);
            String BourseCode = getStringVal(BourseCodeCell);
            rowList.add(BourseCode);

            XSSFCell IsXOIReadyCell = xssfRow.getCell(11);
            String IsXOIReady = getStringVal(IsXOIReadyCell);
            rowList.add(IsXOIReady);

            XSSFCell ProductionExchangeIdCell = xssfRow.getCell(12);
            String ProductionExchangeId = getStringVal(ProductionExchangeIdCell);
            rowList.add(ProductionExchangeId);

            result.add(rowList);
        }
        return result;
    }

    public static List<List<String>> readXlxs(String path) throws Exception{
        InputStream is = new FileInputStream(path);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        List<List<String>> result = new ArrayList<>();

        for(int numSheet=0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);

            if (xssfSheet == null) {
                continue;
            }

            //遍历当前页，循环读取每一行
            List<List<String>> sheetResult = readXSSFSheet(xssfSheet);
            result.addAll(sheetResult);
        }
        return result;
    }

    public static List<XSSFSheet> getXSSFSheet(String path) throws IOException {
        InputStream is = new FileInputStream(path);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        List<XSSFSheet> result = new ArrayList<>();

        for(int numSheet=0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);

            if (xssfSheet == null) {
                continue;
            }
            result.add(xssfSheet);
        }
        return result;
    }

    public static List<HSSFSheet> getHSSFSheet(String path) throws IOException {
        InputStream is = new FileInputStream(path);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        List<HSSFSheet> result = new ArrayList<>();

        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);

            if (hssfSheet == null) {
                continue;
            }
            result.add(hssfSheet);
        }
        return result;
    }

    public static List<List<String>> getTaxRate(XSSFSheet sheet){
        List<List<String>> result = new ArrayList<>();
        //遍历当前页，循环读取每一行; 从1 <= sheetNum
        for(int rowNum=1; rowNum <= sheet.getLastRowNum(); rowNum++){
            XSSFRow xssfRow = sheet.getRow(rowNum);

            int minColIx = xssfRow.getFirstCellNum();//表示开始的列
            int maxColIx = xssfRow.getLastCellNum();//表示结束的列

            List<String> rowList = new ArrayList();

            //从0开始。
            XSSFCell CountryIdCell = xssfRow.getCell(0);
            String CountryId = getStringVal(CountryIdCell);
            rowList.add(CountryId);

            XSSFCell EffectiveDateCell = xssfRow.getCell(1);
            String EffectiveDate = getStringVal(EffectiveDateCell);
            rowList.add(EffectiveDate);

            XSSFCell TaxRateCell = xssfRow.getCell(2);
            String TaxRate = getStringVal(TaxRateCell);
            rowList.add(TaxRate);

            result.add(rowList);
        }
        return result;
    }

    public static void printList(List<List<String>> result){
        System.out.println("Print Count: "+result.size());
        for(int i=0;i<result.size();i++){
            System.out.println(result.get(i));
        }
    }

    public static String getStringVal(Cell cell){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        if(cell != null) {
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_BOOLEAN:
                    return cell.getBooleanCellValue() ? "TRUE" : "FALSE";
                case Cell.CELL_TYPE_FORMULA:
                    return cell.getCellFormula();
                case Cell.CELL_TYPE_NUMERIC:
                    if(DateUtil.isCellDateFormatted(cell)){
                        return "-"+sdf.format(cell.getDateCellValue())+"-";
                    }else{
                        return "-"+String.format("%.6f",cell.getNumericCellValue())+"-";
                    }
                case Cell.CELL_TYPE_STRING:
                    return "-"+cell.getStringCellValue()+"-";
                default:
                    return "-null-";
            }
        }else {
            return "-null-";
        }
    }

    public static void main(String[] args) throws Exception {
        String fileName = "ExchangeList.xlsx";
        String path = ReadExcel.class.getClassLoader().getResource(fileName).getPath();
        List<List<String>> result = readXlxs(path);
        printList(result);
    }
}
