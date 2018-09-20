/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Date;

/**
 *
 * @author Rajakumari Chinthoti
 */
public class Bill {
	
	private String args[]=null;
	private ArrayList<ItemVO> itemList = new ArrayList<ItemVO>();
        private float nonGroceryAmount;
        private float groceryAmount;
        private float nonGrocDiscountamount;
        private float netAmount;
        private int discountPercentage=0;
        private float nonGrocAmtafterdiscount = 0;


    Bill(String[] args) {
        this.args = args;
    }


    void generateBill() {
        ItemDAOImpl itemsUtil = new ItemDAOImpl();
        //System.out.println("No.of Items : "+(args.length-1)); 
		
        for(int  i=0;i<args.length-1;i++){
        
            ItemVO item =itemsUtil.getItemDetails(args[i]);
            itemList.add(item);
            if(item.IsGrocessoryItem()){
               this.groceryAmount = this.groceryAmount + item.getPrice();
            }else{
                this.nonGroceryAmount = this.nonGroceryAmount+item.getPrice();
            }          
           
        }

	netAmount = calculateNetAmount(args[args.length-1]);
        
        printBill();
        
    }
   
    float calculateNetAmount(String cardNo){
      		
        CustomerDAO customerDAO= new CustomerDAOImpl(cardNo);
        
        EmployeeVO employee = customerDAO.getEmployeeDetails();
        
        // if customer is an employee or an affiliate
        if(employee != null){
            if(employee.getIsAffiliate() == false)
                discountPercentage = 30;
            else 
                discountPercentage = 10;
        } 
        // if customer neither employee nor affiliate
        else 
        {
            CustomerVO customer = customerDAO.getCustomerDetails();
            if(customer !=null && "2YEARSOLD".equals(customer.getCustomerType()))
            {
                discountPercentage = 5;
            }
            else {
                discountPercentage=0;
            }
	}	
		
        // if one of the discount applied
        if( !(discountPercentage == 0 )) 
        {
                nonGrocDiscountamount = nonGroceryAmount*discountPercentage/100;
                           
        }
        else if (discountPercentage == 0	&& nonGroceryAmount >= 100)	// if none of the above conditions apply, then if the total bill is above 100 then 5% discount for each 100
        {				   
                nonGrocDiscountamount = ((long)(nonGroceryAmount/100)) * 5;
                
        }

            
        nonGrocAmtafterdiscount = nonGroceryAmount- nonGrocDiscountamount;
        
           
        float totalamounttopay =nonGrocAmtafterdiscount+groceryAmount;
                    
        return totalamounttopay;  
    }
	
    
    void printBill() {
        // code to be written to send data to printer to print bill
        // As of now printing using sop
        
        SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date currentDate=new Date();
        System.out.println("\n\n              ABC Retail Store LLC");
        System.out.println("              "+formatter.format(currentDate));
        System.out.println("\n         -----------------------------------");
        System.out.println("         No    Item                    Price");
        System.out.println("         -----------------------------------");
        
        int i=0;
        
        for(ItemVO item:itemList) {
            System.out.print("         "+ (++i) + "    ");
            if(item.getItemName().length() > 20)
                System.out.print(item.getItemName().substring(20));
            else
                System.out.print(item.getItemName());
                
            for(int j=0; j < 25-(item.getItemName().length());j++) {
                System.out.print(" ");
            }
            System.out.println(item.getPrice());
            
        }
        
        System.out.println("\n         Discount :"+nonGrocDiscountamount);
        System.out.println("\n         Net amount to pay :"+netAmount);
    }
    
}
