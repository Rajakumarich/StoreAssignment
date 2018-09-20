/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Rajakumari Chinthoti
 */
public class CustomerDAOImpl implements CustomerDAO{

    private String cardNo=null;
    
    CustomerDAOImpl(String cardNo) {
        this.cardNo=cardNo;
    }
	// To check whether user is an employee
		
        public EmployeeVO getEmployeeDetails()
        {
            EmployeeVO employeeVO = null;

            try{
                Connection con=ConnectionManager.getConnection();  
                String query = "Select * from Employee where empcardno  = ?";
                PreparedStatement pstmt=con.prepareStatement(query); 
                pstmt.setString(1,cardNo);
                ResultSet rs=pstmt.executeQuery();        
    
                if(rs.next()){
                    employeeVO = new EmployeeVO();
                    employeeVO.setEmpName(rs.getString("empname"));
                    employeeVO.setEmpId(Integer.parseInt(rs.getString("empid")));
                    employeeVO.setPhoneNo(rs.getString("phoneNo"));
                    employeeVO.setCardNo(rs.getString("empcardno"));
                    if("Y".equals(rs.getString("isAffiliate")))
                    {
                        employeeVO.setIsAffiliate(true);
                    }
                }
            }
            catch(SQLException e) {
                e.printStackTrace();
            }
            
            return employeeVO;
        }
                
		
    public CustomerVO getCustomerDetails(){
       CustomerVO customer=null;
        try{
                Connection con=ConnectionManager.getConnection();  
                String query = "Select * from Customer where StartDate <= SYSDATE-730  AND ecardno  = ?";
                PreparedStatement pstmt=con.prepareStatement(query); 
                pstmt.setString(1,cardNo);
                ResultSet rs=pstmt.executeQuery();        
      
                if(rs.next()){
                    customer = new CustomerVO();
                    customer.setCustomerName(rs.getString("cname"));
                    customer.setCustomerType("2YEARSOLD");
                    customer.setPhoneNo(rs.getString("phoneNo"));
                    customer.setCardNo(rs.getString("ecardno"));
                }
                else
                {
                    query = "Select * from Customer where StartDate > SYSDATE-730  AND ecardno  = ?";
                    pstmt=con.prepareStatement(query); 
                    pstmt.setString(1,cardNo);
                    rs=pstmt.executeQuery();    
                    
                    if(rs.next()){
                        customer = new CustomerVO();
                        customer.setCustomerName(rs.getString("cname"));
                        customer.setCustomerType("RECENT");
                        customer.setPhoneNo(rs.getString("phoneNo"));
                        customer.setCardNo(rs.getString("ecardno"));
                    }

                }
            }catch(Exception e){
                 e.printStackTrace();;
            }
            return customer;
            }
    
}
