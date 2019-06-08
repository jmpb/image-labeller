/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package website.jamesbaker.imagetextappend.core;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author James
 */
public class ImageTextAppend {
    
    private String workingFile;
    
    private ExcelReader excelReader;
    private ExcelWriter excelWriter;
    private DataEntryDistributor dataEntryDist;
    
    public ImageTextAppend() {
        excelReader = new ExcelReader();
        excelWriter = new ExcelWriter();
    }
    
    public void readFile(String filename) {
        workingFile = filename;
        try {
            dataEntryDist = new DataEntryDistributor(
                    excelReader.readExcelXlSX(workingFile));
        } catch (IOException ex) {
            Logger.getLogger(ImageTextAppend.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean hasData() {
        if (dataEntryDist != null) {
            return dataEntryDist.hasData();
        }
        return false;
    }
    
    public void writeFile() {
        excelWriter.writeToFile(workingFile, dataEntryDist);
    }
    
    public String[] getEntryRepList() {
        return dataEntryDist.getRowsAndImages();
    }
    
    public DataEntry getDataEntryByRow(int row) {
        return dataEntryDist.getRowData(row+1);
    }
    
}
