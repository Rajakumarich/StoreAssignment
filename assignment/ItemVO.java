/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment;

/**
 *
 * @author Venkateswarlu
 */
public class ItemVO {
    private String itemName;
    private String barcode;
    private float price;
    private boolean isGrocessoryItem=false;
    
    public ItemVO(){        
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean IsGrocessoryItem() {
        return isGrocessoryItem;
    }

    public void setIsGrocessoryItem(boolean isGrocessoryItem) {
        this.isGrocessoryItem = isGrocessoryItem;
    }
    
    
       
    
}
