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
import springapp.service.CompanyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;



@Repository
public class JdbcComputerDao implements ComputerDAO {

//	private JdbcTemplate jdbcTemplate;
//	
//	public void setDataSource(DataSource dataSource) {
//	        this.jdbcTemplate = new JdbcTemplate(dataSource);
//	}

	DataSource dataSource;
	CompanyService companyService;

	@Autowired
	public void setConnect(DataSource dataSource) {
		this.dataSource= (DataSource) dataSource;
	}

	@Autowired
	public void setCompanyService(CompanyService companyService) {
		this.companyService = companyService;
	}

	public Computer find(long id) {
		String query = "SELECT * FROM computer WHERE id=?";
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
				
				long company_id = results.getLong("company_id");
				if (company_id!=0) {
					Company company = companyService.find(company_id);
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
			System.out.println(stmt.toString());
			System.out.println(connect.getWarnings());

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

	public List<Computer> findAll(int limitMin, int limitMax, String search,
			String order, String sens) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Computer> findAll() {
		 
		  List<Computer> computers = new ArrayList<Computer>();
			String query = "SELECT id,name,introduced,discontinued,company_id FROM computer";
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
					
					long company_id = results.getLong("company_id");
					if (company_id!=0) {
						Company company = companyService.find(company_id);
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
				
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
//		  String sql = "select id, name,introduced,discontinued,company_id from computer";
//		  //BeanPropertyRowMapper<Computer> bprm = new BeanPropertyRowMapper<Computer>(Computer.class);
//		  
//		  List<Computer> computers = new ArrayList<Computer>();
//		  //STOP JDBC Template !!!
//		  List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
//		  for (Map row : rows) {
//				Computer customer = new Computer();
//				customer.setId((Long)(row.get("id")));
//				customer.setName((String)row.get("name"));
//				//customer.setAge((Integer)row.get("AGE"));
//				computers.add(customer);
//			}
////	      List<Computer> products = (List<Computer>)getJdbcTemplate().query(sql,cm);
//	      
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
		String query = "SELECT computer.id,computer.name,computer.introduced,computer.discontinued,computer.company_id FROM computer LEFT JOIN company ON computer.company_id=company.id WHERE computer.name LIKE '%"+search+"%' OR company.name LIKE '%"+search+"%'";
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
				
				long company_id = results.getLong("company_id");
				if (company_id!=0) {
					Company company = companyService.find(company_id);
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

}
