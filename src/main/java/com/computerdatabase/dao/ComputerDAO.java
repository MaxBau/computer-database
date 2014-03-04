package com.computerdatabase.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.computerdatabase.classes.Company;
import com.computerdatabase.classes.Computer;
import com.computerdatabase.jdbc.ConnectionMySql;


public class ComputerDAO extends DAO<Computer> {
	private static Logger logger = LoggerFactory.getLogger(ComputerDAO.class);

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
		String query = "SELECT * FROM computer WHERE id=?";
		ResultSet results = null;
		
		Connection connect = ConnectionMySql.getInstance();
		Computer computer = null;
		try (PreparedStatement stmt = connect.prepareStatement(query)){
			stmt.setLong(1, id);
			
			results = stmt.executeQuery();
			computer = new Computer();
			
			while (results.next()) {
				computer.setId(results.getLong("id"));
				computer.setName(results.getString("name"));
				computer.setIntroduced(results.getDate("introduced"));
				computer.setDiscontinued(results.getDate("discontinued"));
				
				long company_id = results.getLong("company_id");
				if (company_id!=0) {
					Company company = CompanyDAO.getInstance().find(company_id);
					computer.setCompany(company);
				}				
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
		
		
		return computer;
	}

	@Override
	public Computer create(Connection connect, Computer obj) throws SQLException {
		java.sql.Date introduced = new java.sql.Date(obj.getIntroduced().getTime());
		java.sql.Date discontinued = new java.sql.Date(obj.getDiscontinued().getTime());
		String query = "INSERT INTO computer (name,introduced,discontinued,company_id) VALUES (?,?,?,?)";
	
		PreparedStatement stmt = connect.prepareStatement(query);
		stmt.setString(1, obj.getName());
		stmt.setDate(2, introduced);
		stmt.setDate(3, discontinued);
				
		if (obj.getCompany().getId()!=0) {
			stmt.setLong(4,obj.getCompany().getId());
		}
		else {
			stmt.setNull(4, java.sql.Types.INTEGER);
		}
				
		stmt.executeUpdate(); 
		
		if (stmt!=null) {
			try {
				stmt.close();
			} catch (SQLException e) {}
		}
			
		return null;
	}

	@Override
	public Computer update(Computer obj) {
		java.sql.Date introduced = new java.sql.Date(obj.getIntroduced().getTime());
		java.sql.Date discontinued = new java.sql.Date(obj.getDiscontinued().getTime());
		String query = "UPDATE computer SET name=?,introduced=?,discontinued=?,company_id=? WHERE id=?";
		Connection connect = ConnectionMySql.getInstance();
	
			try (PreparedStatement stmt = connect.prepareStatement(query)){
				stmt.setString(1,obj.getName());
				stmt.setDate(2, introduced);
				stmt.setDate(3, discontinued);
				stmt.setLong(4,obj.getCompany().getId());
				stmt.setLong(5, obj.getId());
				stmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (connect!=null) {
					try {
						connect.close();
					} catch (SQLException e) {}
				}
			}
			
		return obj;
	}

	@Override
	public void delete(long id) {
		String query = "DELETE FROM computer WHERE id=?";
		Connection connect = ConnectionMySql.getInstance();
		
		try (PreparedStatement stmt = connect.prepareStatement(query)){
			stmt.setLong(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (connect!=null) {
				try {
					connect.close();
				} catch (SQLException e) {}
			}
		}
		
	}

	@Override
	public List<Computer> findAll(int limitMin,int limitMax,String search,String order,String sens) {
		List<Computer> computers = new ArrayList<Computer>();
		String query = "SELECT computer.id,computer.name,computer.introduced,computer.discontinued,computer.company_id FROM computer LEFT JOIN company ON computer.company_id=company.id WHERE computer.name LIKE '%"+search+"%' OR company.name LIKE '%"+search+"%' ORDER BY "+order+" "+sens+" LIMIT "+limitMin+","+limitMax;
		ResultSet results = null;
		
		Connection connect = ConnectionMySql.getInstance();
		
		try (Statement stmt = connect.createStatement()){
			results = stmt.executeQuery(query);
		    logger.info("Displaying computers query");
		    
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
			
			e.printStackTrace();
		} finally {
			if (connect!=null) {
				try {
					connect.close();
				} catch (SQLException e) {}
			}
		}
		
		return computers;
	}
	
	@Override
	public List<Computer> findAll() {
		List<Computer> computers = new ArrayList<Computer>();
		String query = "SELECT * FROM computer";
		ResultSet results = null;
		
		Connection connect = ConnectionMySql.getInstance();
		
		try (Statement stmt = connect.createStatement()){
			
			results = stmt.executeQuery(query);
			
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
		
		return computers;
	}
	@Override
	public void delete(Computer obj) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public int count() {
		String query = "SELECT COUNT(id) AS nb FROM computer";
		ResultSet results = null;
		int count = 0;
		Connection connect = ConnectionMySql.getInstance();
		
		try (Statement stmt = connect.createStatement()){
			
			results = stmt.executeQuery(query);
			
			while (results.next()) {
				count = results.getInt("nb");
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
		
		return count;
	}
	@Override
	public Computer create(Computer obj) {
		// TODO Auto-generated method stub
		return null;
	}


}
