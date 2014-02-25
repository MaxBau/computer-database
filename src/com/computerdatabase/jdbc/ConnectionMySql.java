package com.computerdatabase.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMySql {
	private static String url = "jdbc:mysql://localhost:3306/computer-database-db"; 
	private static String user = "root";
	private static String password = "root";
	private static Connection connect;
	
	public static Connection getInstance(){
		
		if(connect == null){
			try {
				connect = DriverManager.getConnection(url, user, password);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return connect;	
	}

}
