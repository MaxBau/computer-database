package com.computerdatabase.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.computerdatabase.classes.Company;


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
		// TODO Auto-generated method stub
		String query = "SELECT * FROM company WHERE id="+id;
		ResultSet results = null;
		
		try {
			Statement stmt = connect.createStatement();
			results = stmt.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Company company = new Company();
		try {
			company.setId(results.getLong("id"));
			company.setName(results.getString("name"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		// TODO Auto-generated method stub
		return null;
	}

}
