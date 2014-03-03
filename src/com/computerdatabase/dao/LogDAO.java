package com.computerdatabase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LogDAO {
	private LogDAO()
	{}
	
	private static class LogDAOHolder
	{
		private final static LogDAO instance = new LogDAO();
	}
	
	public static LogDAO getInstance() {
		return LogDAOHolder.instance;
	}
	
	public void addLog(Connection connect, int codeError,String message) 
	{
		String query = "INSERT INTO logs (message,error_code) VALUES (?,?)";
		try (PreparedStatement stmt = connect.prepareStatement(query)) {
			stmt.setString(1, message);
			stmt.setInt(2, codeError);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	

}
