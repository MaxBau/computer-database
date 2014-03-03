package com.computerdatabase.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.computerdatabase.classes.Computer;
import com.computerdatabase.dao.ComputerDAO;
import com.computerdatabase.dao.LogDAO;
import com.computerdatabase.jdbc.ConnectionMySql;


public class ComputerService {
	private ComputerDAO computerDAO= ComputerDAO.getInstance();
	private LogDAO logDAO = LogDAO.getInstance();
	private ComputerService()
	{}
	
	private static class ComputerServiceHolder
	{
		private final static ComputerService instance = new ComputerService();
	}
	
	public static ComputerService getInstance() {
		return ComputerServiceHolder.instance;
	}
	
	public List<Computer> getAllComputers(int limitMin,int limitMax, String search,String order,String sens)
	{
		return computerDAO.findAll(limitMin,limitMax,search,order,sens);
	}
	
	public List<Computer> getAllComputers()
	{
		return computerDAO.findAll();
	}
	
	public void createComputer(Computer obj) {
		Connection connect = ConnectionMySql.getInstance();
		try {
			connect.setAutoCommit(false);
			computerDAO.create(connect,obj);
			logDAO.addLog(connect, 0, "Computer added");
			connect.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				connect.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				connect.close();
			} catch (SQLException e) {}
		}
		
		
		
	
		
	}
	
	public Computer getComputerById(long id) {
		return computerDAO.find(id);
	}

	public Computer updateComputer(Computer computer) {
		// TODO Auto-generated method stub
		return computerDAO.update(computer);
	}
	
	public void deleteComputer(long id) {
		computerDAO.delete(id);
	}
	
	public int countComputer() {
		return computerDAO.count();
	}
}
