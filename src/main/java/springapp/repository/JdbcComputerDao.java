package springapp.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import springapp.domain.Company;
import springapp.domain.Computer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class JdbcComputerDao {

	
	public JdbcComputerDao() {
		super();
	}

	@Autowired
	DataSource dataSource;

	public Computer find(long id) {
		String query = "SELECT computer.id,computer.name,computer.introduced,computer.discontinued,company.id,company.name "
				+ "FROM computer INNER JOIN company ON company.id=computer.company_id WHERE computer.id=?";
		ResultSet results = null;
		
		Connection connect = null;
		Computer computer = null;
		PreparedStatement stmt = null;
		
		try {
			connect = dataSource.getConnection();
			stmt =  connect.prepareStatement(query);
			stmt.setLong(1, id);
			
			results = stmt.executeQuery();
			computer = new Computer();
			
			while (results.next()) {
				computer.setId(results.getLong("id"));
				computer.setName(results.getString("name"));
				computer.setIntroduced(results.getDate("introduced"));
				computer.setDiscontinued(results.getDate("discontinued"));
				Company company = new Company();
				company.setId(results.getLong("company.id"));
				company.setName(results.getString("company.name"));
				computer.setCompany(company);			
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
		
		
		return computer;
	}

	public Computer update(Computer obj) {
		String query = "UPDATE computer SET name=?,introduced=?,discontinued=?,company_id=? WHERE id=?";
		
		Connection connect = null;
		PreparedStatement stmt = null;

		try {
			connect = dataSource.getConnection();
			stmt =  connect.prepareStatement(query);
			stmt.setString(1, obj.getName());
			stmt.setDate(2, new java.sql.Date(obj.getIntroduced().getTime()));
			stmt.setDate(3, new java.sql.Date(obj.getDiscontinued().getTime()));
			
			if (obj.getCompany().getId()!=0) {
				stmt.setLong(4,obj.getCompany().getId());
			}
			else {
				stmt.setNull(4, java.sql.Types.INTEGER);
			}
			
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

			if (stmt!=null) {
				try {
					stmt.close();
				} catch (SQLException e) {}
			}
		}
		return null;
	}

	public void delete(long id) {
		String query = "DELETE FROM computer WHERE id=?";
		
		Connection connect = null;
		PreparedStatement stmt = null;
		
		try {
			connect = dataSource.getConnection();
			stmt =  connect.prepareStatement(query);
			stmt.setLong(1, id);
			
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

			if (stmt!=null) {
				try {
					stmt.close();
				} catch (SQLException e) {}
			}
		}

	}

	public List<Computer> findAll(String search, String order, String sens) {
		List<Computer> computers = new ArrayList<Computer>();
		String query = "SELECT computer.id,computer.name,computer.introduced,computer.discontinued,computer.company_id,company.id,company.name FROM computer LEFT JOIN company ON computer.company_id=company.id WHERE computer.name LIKE '%"+search+"%' OR company.name LIKE '%"+search+"%' ORDER BY "+order+" "+sens;
		ResultSet results = null;
		Connection connect = null;
		Statement stmt = null;
		try {
			connect = dataSource.getConnection();
			 stmt = connect.createStatement();
			results = stmt.executeQuery(query);
		
		    
		    while (results.next()) {
		    	Computer computer = new Computer();
				computer.setId(results.getLong("id"));
				computer.setName(results.getString("name"));
				computer.setIntroduced(results.getDate("introduced"));
				computer.setDiscontinued(results.getDate("discontinued"));
				Company company = new Company();
				company.setId(results.getLong("company.id"));
				company.setName(results.getString("company.name"));
				computer.setCompany(company);			
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
			
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
  
      return computers;
	}

	public List<Computer> findAll() {
		 
		  List<Computer> computers = new ArrayList<Computer>();
			String query = "SELECT id,name,introduced,discontinued,company.id,company.name FROM computer "
					+ "INNNER JOIN company ON company.id=computer.company_id";
			ResultSet results = null;
			Connection connect = null;
			Statement stmt = null;
			try {
				connect = dataSource.getConnection();
				 stmt = connect.createStatement();
				results = stmt.executeQuery(query);
			
			    
			    while (results.next()) {
			    	Computer computer = new Computer();
					computer.setId(results.getLong("id"));
					computer.setName(results.getString("name"));
					computer.setIntroduced(results.getDate("introduced"));
					computer.setDiscontinued(results.getDate("discontinued"));
					Company company = new Company();
					company.setId(results.getLong("company.id"));
					company.setName(results.getString("company.name"));
					computer.setCompany(company);			
					computers.add(computer);
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
				
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
      
	      return computers;
	}
	
	private static class ComputerMapper implements RowMapper {
		public Computer mapRow(ResultSet rs, int rowNum) throws SQLException {
            Computer prod = new Computer();
            prod.setId(rs.getInt("id"));
            prod.setName(rs.getString("name"));
            //prod.setPrice(new Double(rs.getDouble("price")));
            return prod;
        }

    }

	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}



	public void add(String name, Date introducedDate, Date discontinuedDate,
			long companyId) {
		// TODO Auto-generated method stub
		java.sql.Date introduced = new java.sql.Date(introducedDate.getTime());
		java.sql.Date discontinued = new java.sql.Date(discontinuedDate.getTime());
		String query = "INSERT INTO computer (name,introduced,discontinued,company_id) VALUES (?,?,?,?)";
	
		Connection connect = null;
		PreparedStatement stmt = null;
		try {
			connect = dataSource.getConnection();
			stmt = connect.prepareStatement(query);
			
			stmt.setString(1, name);
			stmt.setDate(2, introduced);
			stmt.setDate(3, discontinued);
					
			if (companyId!=0) {
				stmt.setLong(4,companyId);
			}
			else {
				stmt.setNull(4, java.sql.Types.INTEGER);
			}
					
			stmt.executeUpdate(); 
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
				try {
					stmt.close();
					connect.close();
				} catch (SQLException e) {}
		}
	}

	
	public Computer create(Computer obj) {
		// TODO Auto-generated method stub
		return null;
	}

	public Computer create(String name, Date introducedDate,
			Date discontinuedDate, Company company) {
		
		return new Computer(name,introducedDate,discontinuedDate,company);
	}

	public Computer create(String name, Date introducedDate,
			Date discontinuedDate, long companyId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Computer create(long id, String name, Date introducedDate,
			Date discontinuedDate, Company company) {
		return new Computer(id,name,introducedDate,discontinuedDate,company);
	}

	public List<Computer> findAll(String search) {
		List<Computer> computers = new ArrayList<Computer>();
		String query = "SELECT computer.id,computer.name,computer.introduced,computer.discontinued,computer.company_id,company.id,company.name "
				+ "FROM computer LEFT JOIN company ON computer.company_id=company.id "
				+ "WHERE computer.name LIKE '%"+search+"%' OR company.name LIKE '%"+search+"%'";
		ResultSet results = null;
		
		Connection connect = null;
		Statement stmt = null;
		
		try {
			connect = dataSource.getConnection();
			stmt = connect.createStatement();
			results = stmt.executeQuery(query);
		   
		    while (results.next()) {
				Computer computer = new Computer();
				computer.setId(results.getLong("id"));
				computer.setName(results.getString("name"));
				computer.setIntroduced(results.getDate("introduced"));
				computer.setDiscontinued(results.getDate("discontinued"));
				Company company = new Company();
				company.setId(results.getLong("company.id"));
				company.setName(results.getString("company.name"));
				computer.setCompany(company);			
				computers.add(computer);
				
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
			
			if (stmt!=null) {
				try {
					stmt.close();
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

	public List<Computer> findAll(int limitMin, int limitMax, String search,
			String order, String sens) {
		// TODO Auto-generated method stub
		return null;
	}

}
