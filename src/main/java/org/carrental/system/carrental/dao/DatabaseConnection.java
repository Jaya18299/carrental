package org.carrental.system.carrental.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	
	public Connection getConnection(){
		try{
			
			System.out.println("Inside getConnection /");
			Class.forName("com.mysql.jdbc.Driver");
		    Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/mycardb","root","apple12345");
			System.out.println("conn:"+con);
			
			return con;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public void closeConnection(Connection con){
		try {
			con.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

}
