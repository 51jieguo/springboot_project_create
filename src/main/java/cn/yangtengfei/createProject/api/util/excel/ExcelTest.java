package cn.yangtengfei.createProject.api.util.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

public class ExcelTest {

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
        for (int i = 0; i<colnum; i++) {

            if(i<=1){
                Cell cell  = row.getCell(i);
                System.out.print(cell.toString());
                System.out.print("\t");
            }else{
                Cell cell  = row.getCell(i);
                System.out.print(cell.toString());
                System.out.print("\t");
            }
        }
    }


    public static void main(String[] args) {
        Workbook workbook = readExcel("/home/soaryang/Desktop/param.xls");
        System.out.println(workbook);
        if(workbook != null){
            int sheetCount = workbook.getNumberOfSheets();
            System.out.println("sheetCount:"+sheetCount);
            for(int i=0; i<sheetCount; i++){
                readSheet(workbook.getSheetAt(0));
            }
        }
    }*/


}

 /*public static void main(String[] args) {
        Workbook wb =null;
        Sheet sheet = null;
        Row row = null;
        List<Map<String,String>> list = null;
        String cellData = null;
        String filePath = "D:\\test.xlsx";
        String columns[] = {"name","age","score"};
        wb = readExcel(filePath);
        if(wb != null){
            //用来存放表中数据
            list = new ArrayList<Map<String,String>>();
            //获取第一个sheet
            sheet = wb.getSheetAt(0);
            //获取最大行数
            int rownum = sheet.getPhysicalNumberOfRows();
            //获取第一行
            row = sheet.getRow(0);
            //获取最大列数
            int colnum = row.getPhysicalNumberOfCells();
            for (int i = 1; i<rownum; i++) {
                Map<String,String> map = new LinkedHashMap<String,String>();
                row = sheet.getRow(i);
                if(row !=null){
                    for (int j=0;j<colnum;j++){
                        cellData = (String) getCellFormatValue(row.getCell(j));
                        map.put(columns[j], cellData);
                    }
                }else{
                    break;
                }
                list.add(map);
            }
        }
        //遍历解析出来的list
        for (Map<String,String> map : list) {
            for (Entry<String,String> entry : map.entrySet()) {
                System.out.print(entry.getKey()+":"+entry.getValue()+",");
            }
            System.out.println();
        }

    }*/
    /*public static Object getCellFormatValue(Cell cell){
        Object cellValue = null;
        if(cell!=null){
            //判断cell类型
            switch(cell.getCellType()){
                case Cell.CELL_TYPE_NUMERIC:{
                    cellValue = String.valueOf(cell.getNumericCellValue());
                    break;
                }
                case Cell.CELL_TYPE_FORMULA:{
                    //判断cell是否为日期格式
                    if(DateUtil.isCellDateFormatted(cell)){
                        //转换为日期格式YYYY-mm-dd
                        cellValue = cell.getDateCellValue();
                    }else{
                        //数字
                        cellValue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                case Cell.CELL_TYPE_STRING:{
                    cellValue = cell.getRichStringCellValue().getString();
                    break;
                }
                default:
                    cellValue = "";
            }
        }else{
            cellValue = "";
        }
        return cellValue;
    }*/
