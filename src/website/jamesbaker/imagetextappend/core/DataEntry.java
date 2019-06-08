/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package website.jamesbaker.imagetextappend.core;

/**
 *
 * @author James
 */
public class DataEntry {
    
    public String sku;
    public String name;
    public String image;
    public String imageLabel;
    public int sourceRow;
    
    @Override
    public String toString() {
        return "Entry for row: " + sourceRow + ": " + sku + ", " + name + ", "
                + image + ", " + imageLabel;
    }
    
}
