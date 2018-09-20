/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author Rajakumari Chinthoti
 */
public class ItemDAOImpl {
    HashMap hmObj= new HashMap();
	

    // To fetch Item Details
    
    public ItemVO getItemDetails(String itembarcode){
               ItemVO dtoObj= new ItemVO();
               //System.out.println(itembarcode);
               try{
                       Connection con=ConnectionManager.getConnection();  
                       String query = "Select *from Item where barcode = ?";
                       PreparedStatement pstmt=con.prepareStatement(query);  
                       pstmt.setString(1,itembarcode);
                       ResultSet rs=pstmt.executeQuery();  
                       
                       if(rs.next()){                
                           dtoObj.setBarcode(rs.getString("barcode"));
                           dtoObj.setItemName(rs.getString("itemname"));
                           dtoObj.setPrice(rs.getInt("price"));
                            if("G".equals(rs.getString("ItemType")))
                                dtoObj.setIsGrocessoryItem(true);
                        }
                
                }
                catch(Exception e){
                           System.out.println("Exception in getItemsDetails"+e);
                }
            return dtoObj;
        }

    
}
