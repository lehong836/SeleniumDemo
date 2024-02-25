package main.helpers;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ExcelHelpers {
    private FileInputStream fileIn;
    private FileOutputStream fileOut;
    private Workbook wb;
    private Sheet sheet;
    private Row row;
    private Cell cell;
    private CellStyle cellStyle;
    private String excelFilePath;
    private Map<String, Integer> columns = new HashMap<>();

    public void setExcelFile(String excelPath, String sheetName) throws Exception {
        try {
            File f = new File(excelPath);
            if (!f.exists()){
                System.out.println("File not found!");
                return;
            }
            fileIn = new FileInputStream(excelPath);
            wb = WorkbookFactory.create(fileIn);
            sheet = wb.getSheet(sheetName);
            if (sheet == null){
                System.out.println("Sheet not found!");
                return;
            }
            this.excelFilePath = excelPath;

            //Map header with column index
            sheet.getRow(0).forEach(cell ->{
                columns.put(cell.getStringCellValue(), cell.getColumnIndex());
            });

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    public String getCellData(int rowNum, int colNum) throws Exception {
        try {
            cell = sheet.getRow(rowNum).getCell(colNum);
            String cellData = null;
            switch (cell.getCellType()) {
                case STRING:
                    cellData = cell.getStringCellValue();
                    break;
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        cellData = String.valueOf(cell.getDateCellValue());
                    } else {
                        cellData = String.valueOf((long)cell.getNumericCellValue());
                    }
                    break;
                case BOOLEAN:
                    cellData = Boolean.toString(cell.getBooleanCellValue());
                    break;
                case BLANK:
                    cellData = "";
                    break;
            }
            return cellData;
        } catch (Exception e) {
            return "";
        }
    }

    public String getCellDataByColumName(String columnName, int rowNum) throws Exception{
        return getCellData(rowNum,columns.get(columnName));
    }

    public void setCellData(String text, int rowNum, int colNum) throws Exception{
        try{
            row = sheet.getRow(rowNum);
            if(row == null){
                row = sheet.createRow(rowNum);
            }
            cell = row.getCell(colNum);
            if(cell == null){
                cell = row.createCell(colNum);
            }
            cell.setCellValue(text);

            fileOut = new FileOutputStream(excelFilePath);
            wb.write(fileOut);
            fileOut.flush();
            fileOut.close();
        }catch (Exception e){
            throw (e);
        }
    }
}
