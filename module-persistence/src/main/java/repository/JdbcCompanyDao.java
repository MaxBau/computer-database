package repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import domain.Company;

@Repository
@Transactional
public class JdbcCompanyDao {
	@Autowired
	DataSource dataSource;
	
	public JdbcCompanyDao()
	{
		super();
	}
	
	private static class CompanyDAOHolder
	{
		private final static JdbcCompanyDao instance = new JdbcCompanyDao();
	}
	
	public static JdbcCompanyDao getInstance() {
		return CompanyDAOHolder.instance;
	}

	public Company find(long id) {
		String query = "SELECT * FROM company WHERE id="+id;
		ResultSet results = null;
		Connection connect = null;
		Company company = null;
		
		try {
			connect = dataSource.getConnection();
			Statement stmt = connect.createStatement();
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

	public Company create(Connection connect,Company obj) {
		// TODO Auto-generated method stub
		return null;
	}

	public Company update(Company obj) {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}

	public List<Company> findAll(int limitMin, int limitMax,
			String search, String order, String sens) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Company> findAll() {
		String query = "SELECT * FROM company ";
		List<Company> companies = new ArrayList<Company>();
		ResultSet results = null;
		Statement stmt = null;
		Connection connect = null;
		
		try {
			
			connect  = dataSource.getConnection();
			stmt = connect.createStatement();
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
			if (stmt!=null) {
				try {
					stmt.close();
				} catch (SQLException e) {}
			}
		}
		
		return companies;
	}

	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}
	public Company create() {
		
		return new Company();
	}
	public Company create(long id) {
		
		return new Company(id);
	}
}
	
