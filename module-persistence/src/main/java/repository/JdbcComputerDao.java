package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import domain.Company;
import domain.Computer;

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
				+ "FROM computer LEFT JOIN company ON company.id=computer.company_id WHERE computer.id=?";

		ResultSet results = null;
		Connection connect = null;
		Computer computer = null;
		PreparedStatement stmt = null;

		try {
			connect = dataSource.getConnection();
			stmt = connect.prepareStatement(query);
			stmt.setLong(1, id);

			results = stmt.executeQuery();
			computer = new Computer();

			while (results.next()) {
				computer.setId(results.getLong("id"));
				computer.setName(results.getString("name"));
				computer.setIntroduced(new LocalDate(results.getDate("introduced")));
				computer.setDiscontinued(new LocalDate(results.getDate("discontinued")));
				Company company = new Company();
				company.setId(results.getLong("company.id"));
				company.setName(results.getString("company.name"));
				computer.setCompany(company);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (connect != null) {
				try {
					connect.close();
				} catch (SQLException e) {
				}
			}

			if (results != null) {
				try {
					results.close();
				} catch (SQLException e) {
				}
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
				}
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
			stmt = connect.prepareStatement(query);
			stmt.setString(1, obj.getName());
			stmt.setTimestamp(2, new Timestamp(obj.getIntroduced().toDateTimeAtStartOfDay().getMillis()));
			stmt.setTimestamp(3, new Timestamp(obj.getDiscontinued().toDateTimeAtStartOfDay().getMillis()));

			if (obj.getCompany().getId() != 0) {
				stmt.setLong(4, obj.getCompany().getId());
			} else {
				stmt.setNull(4, java.sql.Types.INTEGER);
			}

			stmt.setLong(5, obj.getId());

			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (connect != null) {
				try {
					connect.close();
				} catch (SQLException e) {
				}
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
				}
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
			stmt = connect.prepareStatement(query);
			stmt.setLong(1, id);

			stmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (connect != null) {
				try {
					connect.close();
				} catch (SQLException e) {
				}
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			}
		}

	}

	public List<Computer> findAll(String search, String order, String sens, String limitMin, String limitMax) {
		List<Computer> computers = new ArrayList<Computer>();

		if (order.equals(" "))
			order = "computer.name";
		String query = "SELECT computer.id,computer.name,computer.introduced,computer.discontinued,computer.company_id,company.id,company.name "
				+ "FROM computer LEFT JOIN company ON computer.company_id=company.id " + "WHERE computer.name LIKE '%" + search + "%' " + "OR company.name LIKE '%" + search + "%' " + "ORDER BY "
				+ order + " " + sens + " LIMIT " + limitMin + "," + limitMax;
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
				computer.setIntroduced(new LocalDate(results.getDate("introduced")));
				computer.setDiscontinued(new LocalDate(results.getDate("discontinued")));
				Company company = new Company();
				company.setId(results.getLong("company.id"));
				company.setName(results.getString("company.name"));
				computer.setCompany(company);
				computers.add(computer);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			if (connect != null) {
				try {
					connect.close();
				} catch (SQLException e) {
				}
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
		String query = "SELECT computer.id,name,introduced,discontinued,company.id,company.name FROM computer LEFT JOIN company ON company.id=computer.company_id";
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
				computer.setIntroduced(new LocalDate(results.getDate("introduced")));
				computer.setDiscontinued(new LocalDate(results.getDate("discontinued")));
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
			if (connect != null) {
				try {
					connect.close();
				} catch (SQLException e) {
				}
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

	// private static class ComputerMapper implements RowMapper {
	// public Computer mapRow(ResultSet rs, int rowNum) throws SQLException {
	// Computer prod = new Computer();
	// prod.setId(rs.getInt("id"));
	// prod.setName(rs.getString("name"));
	// //prod.setPrice(new Double(rs.getDouble("price")));
	// return prod;
	// }
	//
	// }

	public int count(String search) {
		String query = "SELECT COUNT(computer.id) AS nbComputer FROM computer LEFT JOIN company ON computer.company_id=company.id WHERE computer.name LIKE '%" + search + "%' "
				+ "OR company.name LIKE '%" + search + "%'";

		ResultSet results = null;
		Connection connect = null;
		Statement stmt = null;
		int nb = 0;
		try {
			connect = dataSource.getConnection();
			stmt = connect.createStatement();
			results = stmt.executeQuery(query);

			while (results.next()) {
				nb = results.getInt("nbComputer");
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			if (connect != null) {
				try {
					connect.close();
				} catch (SQLException e) {
				}
			}

			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return nb;
	}

	public void add(String name, LocalDate introducedDate, LocalDate discontinuedDate, long companyId) {

		String query = "INSERT INTO computer (name,introduced,discontinued,company_id) VALUES (?,?,?,?)";

		Connection connect = null;
		PreparedStatement stmt = null;
		try {
			connect = dataSource.getConnection();
			stmt = connect.prepareStatement(query);

			stmt.setString(1, name);
			stmt.setTimestamp(2, new Timestamp(introducedDate.toDateTimeAtStartOfDay().getMillis()));
			stmt.setTimestamp(3, new Timestamp(discontinuedDate.toDateTimeAtStartOfDay().getMillis()));

			if (companyId != 0) {
				stmt.setLong(4, companyId);
			} else {
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
			} catch (SQLException e) {
			}
		}
	}

	public Computer create(Computer obj) {
		return null;
	}

	public Computer create(String name, LocalDate introducedDate, LocalDate discontinuedDate, Company company) {

		return new Computer(name, introducedDate, discontinuedDate, company);
	}

	public Computer create(String name, Date introducedDate, Date discontinuedDate, long companyId) {
		return null;
	}

	public Computer create(long id, String name, LocalDate introducedDate, LocalDate discontinuedDate, Company company) {
		return new Computer(id, name, introducedDate, discontinuedDate, company);
	}

	public List<Computer> findAll(String search) {
		List<Computer> computers = new ArrayList<Computer>();
		String query = "SELECT computer.id,computer.name,computer.introduced,computer.discontinued,computer.company_id,company.id,company.name FROM computer LEFT JOIN company ON computer.company_id=company.id "
		+ "WHERE computer.name LIKE '%" + search + "%' OR company.name LIKE '%" + search + "%'";
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
				computer.setIntroduced(new LocalDate(results.getDate("introduced")));
				computer.setDiscontinued(new LocalDate(results.getDate("discontinued")));
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
			if (connect != null) {
				try {
					connect.close();
				} catch (SQLException e) {
				}
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			}

			if (results != null) {
				try {
					results.close();
				} catch (SQLException e) {
				}
			}
		}

		return computers;
	}

	public List<Computer> findAll(int limitMin, int limitMax, String search, String order, String sens) {
		// TODO Auto-generated method stub
		return null;
	}

}
