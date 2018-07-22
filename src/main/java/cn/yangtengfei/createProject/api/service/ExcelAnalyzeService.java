package cn.yangtengfei.createProject.api.service;


import cn.yangtengfei.createProject.api.util.excel.Attribute;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

@Slf4j
@Service
public class ExcelAnalyzeService {



    public  Workbook readExcel(InputStream is,String extString){
        Workbook wb = null;
        try {
            if(".xls".equals(extString)){
                return wb = new HSSFWorkbook(is);
            }else if(".xlsx".equals(extString)){
                return wb = new XSSFWorkbook(is);
            }else{
                return wb = null;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }



    //读取excel
    /*public static Workbook readExcel(String filePath){
        Workbook wb = null;
        if(filePath==null){
            return null;
        }
        String extString = filePath.substring(filePath.lastIndexOf("."));
        InputStream is = null;
        try {
            is = new FileInputStream(filePath);
            if(".xls".equals(extString)){
                return wb = new HSSFWorkbook(is);
            }else if(".xlsx".equals(extString)){
                return wb = new XSSFWorkbook(is);
            }else{
                return wb = null;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }

    public static void readSheet(Sheet sheet){
        int rownum = sheet.getPhysicalNumberOfRows();
        System.out.println("rownum:"+rownum);
        for(int i=1; i<rownum; i++){
            Row row = sheet.getRow(i);
            readRow(row);
            System.out.println();
        }
    }

    public static void readRow(Row row){
        int colnum = row.getPhysicalNumberOfCells();
        System.out.println("colnum:"+colnum);
        Attribute attribute = new Attribute();
        attribute.setName(row.getCell(0).toString());
        String type = row.getCell(1).toString();
        attribute.setType(type);
        if("int".equals(type)){
            DecimalFormat df = new DecimalFormat("0");
            attribute.setValue(df.format(row.getCell(2).getNumericCellValue()));
        }
        System.out.println(attribute.toString());
    }*/
}
