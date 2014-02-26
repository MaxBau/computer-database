package com.computerdatabase.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionMySql {
	/*private static String url = "jdbc:mysql://localhost:3306/computer-database-db"; 
	private static String user = "root";
	private static String password = "root";*/
	private static Connection connect;
	
	public static Connection getInstance(){
		
		DataSource ds = null;
		if(connect == null){
			try {
					Context ctx = new InitialContext();
					ds = (DataSource)ctx.lookup("java:/comp/env/database");
					connect = ds.getConnection();
			} catch (SQLException | NamingException e) {
				System.out.println("pb getInstance connectionmysql "+e.getMessage());
				
			}
		}		
		return connect;	
	}

}
