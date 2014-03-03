package com.computerdatabase.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.computerdatabase.classes.Company;
import com.computerdatabase.classes.Computer;
import com.computerdatabase.jdbc.ConnectionMySql;


public class CompanyDAO extends DAO<Company> {

	private CompanyDAO()
	{}
	
	private static class CompanyDAOHolder
	{
		private final static CompanyDAO instance = new CompanyDAO();
	}
	
	public static CompanyDAO getInstance() {
		return CompanyDAOHolder.instance;
	}
	
	@Override
	public Company find(long id) {
		
		String query = "SELECT * FROM company WHERE id="+id;
		ResultSet results = null;
		
		Connection connect = ConnectionMySql.getInstance();
		Company company = null;
		try (Statement stmt = connect.createStatement()){
			
			results = stmt.executeQuery(query);
			company = new Company();
			while (results.next())
			{
				company.setId(results.getLong("id"));
				company.setName(results.getString("name"));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			if (connect!=null) {
				try {
					connect.close();
				} catch (SQLException e) {}
			}
			
			if (results!=null) {
				try {
					results.close();
				} catch (SQLException e) {}
			}
		}
		
		

		
		return company;
	}

	@Override
	public Company create(Company obj) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public Company update(Company obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Company obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Company> findAll() {
		String query = "SELECT * FROM company ";
		List<Company> companies = new ArrayList<Company>();
		ResultSet results = null;
		
		Connection connect = ConnectionMySql.getInstance();
		
		try (Statement stmt = connect.createStatement()){
			
			results = stmt.executeQuery(query);
			
			while (results.next())
			{
				Company company = new Company();
				company.setId(results.getLong("id"));
				company.setName(results.getString("name"));
				companies.add(company);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (connect!=null) {
				try {
					connect.close();
				} catch (SQLException e) {}
			}
			
			if (results!=null) {
				try {
					results.close();
				} catch (SQLException e) {}
			}
		}
		
		return companies;
	}

	@Override
	public List<Company> findAll(int limitMin,int limitMax,String search,String order,String sens) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Computer create(Connection connect, Computer obj) {
		// TODO Auto-generated method stub
		return null;
	}
}
