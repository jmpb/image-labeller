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
public class HeaderEntry extends DataEntry {
    
    HeaderEntry() {
        sku = null;
        name = null;
        image = null;
        imageLabel = null;
        sourceRow = 0;
    }
    
    @Override
    public String toString() {
        return "Header entry: sku, name, image, image_label";
    }
}
