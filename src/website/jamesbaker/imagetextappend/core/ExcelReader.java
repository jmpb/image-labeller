/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package website.jamesbaker.imagetextappend.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author James
 */
public class ExcelReader {
    
    private Map<Integer, String> rowMap;
    
    ExcelReader() {
        rowMap = new HashMap<>();
    }
    
    /**
     * Reads in the supplied XLSX file and returns a map containing the
     * resulting contents with the row number as key and the cell contents array
     * as value.
     * @param filename The filename to be read in, must not be empty.
     * @return An unmodifiable copy of the map with the file data.
     * @throws FileNotFoundException If the supplied file can not be found.
     * @throws IOException If the supplied file can not be read.
     */
    Map<Integer, String> readExcelXlSX(String filename) throws FileNotFoundException, IOException {
        assert(!filename.isEmpty());
        File excelFile = new File(filename);
        // we create an XSSF Workbook object for our XLSX Excel File
        try (FileInputStream fis = new FileInputStream(excelFile); 
                XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
            
            // we get first sheet
            XSSFSheet sheet = workbook.getSheetAt(0);

            // we iterate on rows
            Iterator<Row> rowIterator = sheet.iterator();
            while(rowIterator.hasNext()) {
                Row row = rowIterator.next();
                
                int rowKey = row.getRowNum();
                String rowValue = "";

                // iterate on cells for the current row
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    rowValue += cell.toString().concat(";");
                }

                rowMap.put(rowKey, rowValue);
                rowKey++;
            }
        }
        return Collections.unmodifiableMap(rowMap);
    }
}
    
