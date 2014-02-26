package com.computerdatabase.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.computerdatabase.classes.Company;
import com.computerdatabase.classes.Computer;
import com.computerdatabase.jdbc.ConnectionMySql;


public class ComputerDAO extends DAO<Computer> {

	private ComputerDAO()
	{}
	
	private static class ComputerDAOHolder
	{
		private final static ComputerDAO instance = new ComputerDAO();
	}
	
	public static ComputerDAO getInstance() {
		return ComputerDAOHolder.instance;
	}
	@Override
	public Computer find(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Computer create(Computer obj) {
		java.sql.Date introduced = new java.sql.Date(obj.getIntroduced().getTime());
		java.sql.Date discontinued = new java.sql.Date(obj.getDiscontinued().getTime());
		String query = "INSERT INTO computer (name,introduced,discontinued,company_id) VALUES ('"+obj.getName()+"','"+introduced+"','"+discontinued+"','"+obj.getCompany().getId()+"')";
		Connection connect = ConnectionMySql.getInstance();
	
			Statement stmt;
			try {
				stmt = connect.createStatement();
				stmt.executeUpdate(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		return null;
	}

	@Override
	public Computer update(Computer obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Computer obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Computer> findAll() {
		// TODO Auto-generated method stub
		List<Computer> computers = new ArrayList<Computer>();
		String query = "SELECT * FROM computer";
		ResultSet results = null;
		
		Connection connect = ConnectionMySql.getInstance();
		
		try {
			Statement stmt = connect.createStatement();
			results = stmt.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			while (results.next()) {
				Computer computer = new Computer();
				computer.setId(results.getLong("id"));
				computer.setName(results.getString("name"));
				computer.setIntroduced(results.getDate("introduced"));
				computer.setDiscontinued(results.getDate("discontinued"));
				
				long company_id = results.getLong("company_id");
				if (company_id!=0) {
					Company company = CompanyDAO.getInstance().find(company_id);
					computer.setCompany(company);
				}				
				computers.add(computer);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return computers;
	}

}
