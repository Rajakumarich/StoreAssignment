/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Rajakumari Chinthoti
 */
public class ConnectionManager {

    private static Connection con = null;    
	
	private ConnectionManager() 
	{
	}
	
	public static Connection getConnection()
	{
		if(con != null )
			return con;
		else
		{
			try{
				Class.forName("oracle.jdbc.driver.OracleDriver");  
				con=DriverManager.getConnection("jdbc:oracle:thin:@10.2.15.24:1541:ESDEV","apps","apps");  
			}
			catch(Exception e){
				System.out.println("Exception"+e);
			}
			return con;
		}
    }

}
