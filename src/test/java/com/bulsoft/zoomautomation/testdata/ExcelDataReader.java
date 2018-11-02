package com.bulsoft.zoomautomation.testdata;

import com.bulsoft.zoomautomation.configuration.DriverConfig;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
// Log4j;

public class ExcelDataReader {


    private String fileName = DriverConfig.getPropertyValue("testdata.excel.path");

    Logger logger = Logger.getLogger("ExcelDataLogger");

    private Workbook workbook;

    public ExcelDataReader(){
        try {

            InputStream inputStream = new FileInputStream(fileName);
            workbook = new XSSFWorkbook(inputStream);

        }catch (IOException ex){
            ex.printStackTrace();
        }

    }
    /**
     * This method will read excel file
     * and will return object[] by converting all cells in a row
     *  Name  Id
     *  Atin  123
     *  Vivek 420
     *  == >   {"Atin", "123"},{"Vivek", 420}
     * @return
     */
    public List<Object[]> readExcelData(String sheetName, boolean skipHeader){
        List<Object[]> dataSet = new ArrayList<>();
        // Get The sheet read
        Sheet sheet = workbook.getSheet(sheetName);
        if(sheet==null){
            logger.log(Level.WARNING,"Could not find the excel sheet with name "+ sheetName );
            logger.log(Level.WARNING,"Selecting sheet with index 0 " );
            sheet = workbook.getSheetAt(0);
        }
        Iterator<Row> rowIterator = sheet.rowIterator();
        /*
            Loop through the rows using iterator
            hasNext() will return false, if there no populated row
         */
        if(skipHeader){
            rowIterator.next(); // This mean I am skipping the header
        }
        while (rowIterator.hasNext()){
            //next() will get data from iterator
            Row row = rowIterator.next();

            // Now I need to loop through cell
            // get Celliterator from row
            Iterator<Cell> cellIterator =  row.cellIterator();
            // Declare a Array list to put the data;
            List<Object> cellData = new ArrayList<>();
            while (cellIterator.hasNext()){
                Cell cell = cellIterator.next();
                cellData.add(cell.getStringCellValue());

            }
            dataSet.add(cellData.toArray());
        }
        return dataSet;
        //
    }
}
