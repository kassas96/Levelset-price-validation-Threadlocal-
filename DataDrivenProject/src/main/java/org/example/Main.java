package org.example;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String excelFilePath=".\\data\\data driven.xlsx";
        FileInputStream inputstream=new FileInputStream(excelFilePath);

        XSSFWorkbook workbook=new XSSFWorkbook(inputstream);
        XSSFSheet sheet=workbook.getSheetAt(0); //XSSFSheet sheet=workbook.getSheet("Sheet1");

//// USING FOR LOOP

        int rows=sheet.getLastRowNum();
        int cols=sheet.getRow(1).getLastCellNum();

        for(int r=0;r < rows;r++){

            //   try {


            XSSFRow row=sheet.getRow(r);
            if (row==null){
                continue;
            }
            else {
                System.out.println(r+"                ");
                for(int c=0;c<cols;c++){

                    XSSFCell cell=row.getCell(c);

                    switch(cell.getCellType()){

                        case STRING: System.out.println(cell.getStringCellValue()); break;
                        case NUMERIC: System.out.println(cell.getNumericCellValue());break;
                        case BOOLEAN: System.out.println(cell.getBooleanCellValue()); break;
                        default:
                            System.out.println("Unknown cell type");
                    }}

            }
//                }catch (Exception e){
//                    System.out.println("row is null");
//                }
        }

    }
}