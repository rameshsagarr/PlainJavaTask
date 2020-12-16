package com.mars;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtility {
	
	public static void main(String a[]) throws SQLException 
    { 
	 
   System.out.println( getDBConnection()); 
   createPersonTable();
 
}

	public static Connection getDBConnection() {
		String url = "jdbc:h2:mem:plainjavatestdb"; 
		String user = "sa"; 
		String pass = ""; 
		 Connection con=null;
		try {
			
			 DriverManager.registerDriver(new org.h2.Driver()); 
			    
			    con = DriverManager.getConnection(url,user,pass); 

			   
		}
		catch(Exception ex) 
		{ 
		    System.err.println("jdbc connection exception:"+ex); 
		}
		return con;
	}
	public static void createPersonTable() throws SQLException {
		 Connection con=null; 
		 Statement st =null;
		 con=DatabaseUtility.getDBConnection();
		 st = con.createStatement();
		 String sql = "CREATE TABLE PERSONS " +
                 "(pid INTEGER AUTO_INCREMENT  PRIMARY KEY, " +
                 " firstname VARCHAR(250) NOT NULL," + 
                 " surname VARCHAR(250) NOT NULL)" ; 
		 st.executeUpdate(sql);
		 System.out.println("Created table in given database...");
	}
}


