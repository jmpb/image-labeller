/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package website.jamesbaker.imagetextappend.core;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author James
 */
public class DataEntryDistributor {
    
    private Map<Integer, String> workingData;
    private List<DataEntry> parsedData;
    
    private boolean hasHeaders;
    private int lastRowDataKey;
    
    
    DataEntryDistributor(Map<Integer, String> dataMap) {
        workingData = dataMap;
        parsedData = new ArrayList<>();
        
        hasHeaders = detectHeaderRow();
        preProcessData();
    }
    
    DataEntry getRowData(int rowNumber) {
        if (hasHeaders && rowNumber == 0) {
            HeaderEntry header = new HeaderEntry();
            return header;
        } else {
            for(DataEntry data : parsedData) {
                if (rowNumber == data.sourceRow) {
                    return data;
                }
            }
        }
        return null;
    }
    
    boolean hasData() {
        return !workingData.isEmpty();
    }
    
    DataEntry getLastRowData() {
        if (lastRowDataKey >= 0 && lastRowDataKey < parsedData.size()) {
            return getRowData(lastRowDataKey);
        }
        return null;
    }
    
    String[] getRowsAndImages() {
        String[] result = new String[parsedData.size()];
        
        int count = 0;
        for(DataEntry entry : parsedData) {
            if (entry.getClass() == HeaderEntry.class) continue;
            result[count] = entry.sourceRow + ": " + entry.sku;
            count++;
        }
        
        return result;
    }
    
    List<DataEntry> getData() {
        return Collections.unmodifiableList(parsedData);
    }
    
    int getDataSize() {
        return parsedData.size();
    }
    
    private void preProcessData() {
        for (int key : workingData.keySet()) {
            if (hasHeaders && key == 0) continue;
            
            DataEntry newEntry = new DataEntry();
            String dataRow = workingData.get(key);
            String[] splitCells = dataRow.split(";");
            
            newEntry.sourceRow = key;
            newEntry.sku = splitCells.length > 0 ? splitCells[0] : "";
            newEntry.name = splitCells.length > 1 ? splitCells[1] : "";
            newEntry.image = splitCells.length > 2 ? splitCells[2] : "";
            newEntry.imageLabel = splitCells.length > 3 ? splitCells[3] : "";
            
            parsedData.add(newEntry);
        }
    }
    
    private boolean detectHeaderRow() {
        String firstRow = workingData.get(0);
        return (firstRow.contains("sku") && firstRow.contains("name")
            && firstRow.contains("image") && firstRow.contains("image_label"));
    }
    
}
