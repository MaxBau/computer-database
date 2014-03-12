package springapp.repository;

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

import springapp.domain.Company;

@Repository
@Transactional
public class JdbcCompanyDao {
	@Autowired
	Company company;
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
	
//	@Override
//	public Company create(Company obj) {
//		// TODO Auto-generated method stub
//		
//		return null;
//	}
//
//	@Override
//	public Company update(Company obj) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void delete(Company obj) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public List<Company> findAll() {
//		String query = "SELECT * FROM company ";
//		List<Company> companies = new ArrayList<Company>();
//		ResultSet results = null;
//		
//		Connection connect = ConnectionMySql.getInstance();
//		
//		try (Statement stmt = connect.createStatement()){
//			
//			results = stmt.executeQuery(query);
//			
//			while (results.next())
//			{
//				Company company = new Company();
//				company.setId(results.getLong("id"));
//				company.setName(results.getString("name"));
//				companies.add(company);
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			if (connect!=null) {
//				try {
//					connect.close();
//				} catch (SQLException e) {}
//			}
//			
//			if (results!=null) {
//				try {
//					results.close();
//				} catch (SQLException e) {}
//			}
//		}
//		
//		return companies;
//	}
//
//	@Override
//	public List<Company> findAll(int limitMin,int limitMax,String search,String order,String sens) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public int count() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public void delete(long id) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public Computer create(Connection connect, Computer obj) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void init() {
//		getInstance();
//	}
//
//	@Override
//	public void destroy() {
//		// TODO Auto-generated method stub
//		
//	}
}
