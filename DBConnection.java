package com.tap.connection;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	public static String url="jdbc:mysql://localhost:3306/foodapplication";
	public static String Username="root";
	public static String password="root";
		
	
	static Connection con;
		public static Connection connect() {
			try {
				// Load the Driver
				Class.forName("com.mysql.cj.jdbc.Driver");
				// Established the Connection
				con=DriverManager.getConnection(url, Username,password);
			}
			catch(ClassNotFoundException e) {
				e.printStackTrace();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			return con;
		}
		
	
		
		
		
		
		
		
		
		
		
	

	

}
