/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package website.jamesbaker.imagetextappend.core;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author James
 */
public class ExcelWriter {
    
    private final String[] columnHeaders = {"sku", "name", "image", "image_label"};
    
    void writeToFile(String filename, DataEntryDistributor ded) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("IMGTXTAPP");
        List<DataEntry> data = ded.getData();
        
        Row headerRow = sheet.createRow(0);
        
        for(int i=0; i < columnHeaders.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columnHeaders[i]);
        }
        
        int rowNum = 1;
        for (DataEntry dataRow : data) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(dataRow.sku);
            row.createCell(1).setCellValue(dataRow.name);
            row.createCell(2).setCellValue(dataRow.image);
            row.createCell(3).setCellValue(dataRow.imageLabel);
        }
        
        String newFilename = filename.replace(".xlsx", "");
        newFilename += "-new.xlsx";
        
        try (FileOutputStream fileOut = new FileOutputStream(newFilename)) {
            workbook.write(fileOut);
            workbook.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExcelWriter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ExcelWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
