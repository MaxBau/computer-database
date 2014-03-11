package springapp.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springapp.domain.Company;
import springapp.domain.Computer;
import springapp.repository.JdbcComputerDao;

@Service
public class ComputerService {
	private JdbcComputerDao computer;
	
	private ComputerService()
	{}
	
	public JdbcComputerDao getComputer() {
		return computer;
	}
	@Autowired
	public void setComputer(JdbcComputerDao computer) {
		this.computer = computer;
	}

	public List<Computer> getAllComputers()
	{
		return computer.findAll();
	}
	
	public void addComputer(String name, String introducedDate, String discontinuedDate, long companyId) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date introducedSubmit = new Date();
		Date discontinuedSubmit = new Date();
		try {
			//TODO Conversions dates
			introducedSubmit = sdf.parse(introducedDate);
			discontinuedSubmit = sdf.parse(discontinuedDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		computer.add(name, introducedSubmit, discontinuedSubmit, companyId);
		
	}
	
	public Computer getComputerById(long id) {
		return computer.find(id);
	}
	
	public Computer create(String name, String introduced, String discontinued, Company company) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date introducedSubmit = new Date();
		Date discontinuedSubmit = new Date();
		try {
			//TODO Conversions dates
			introducedSubmit = sdf.parse(introduced);
			discontinuedSubmit = sdf.parse(discontinued);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return computer.create(name, introducedSubmit, discontinuedSubmit, company);
	}
	
	public Computer create(long id,String name, String introduced, String discontinued, Company company) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date introducedSubmit = new Date();
		Date discontinuedSubmit = new Date();
		try {
			//TODO Conversions dates
			introducedSubmit = sdf.parse(introduced);
			discontinuedSubmit = sdf.parse(discontinued);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return computer.create(id, name, introducedSubmit, discontinuedSubmit, company);
	}
	

	public void update(Computer obj) {

		computer.update(obj);
	}
	
	public void deleteComputer(long id) {
		computer.delete(id);
	}
	
	public List<Computer> findAll(String search) {
		return computer.findAll(search);
	}
//
//	private static class ComputerServiceHolder
//	{
//		private final static ComputerService instance = new ComputerService();
//	}
//	
//	public static ComputerService getInstance() {
//		return ComputerServiceHolder.instance;
//	}
//	
//	public List<Computer> getAllComputers(int limitMin,int limitMax, String search,String order,String sens)
//	{
//		return computerDAO.findAll(limitMin,limitMax,search,order,sens);
//	}
//	


//	
//	public void createComputer(Computer obj) {
//		Connection connect = ConnectionMySql.getInstance();
//		try {
//			connect.setAutoCommit(false);
//			computerDAO.create(connect,obj);
//			logDAO.addLog(connect, 0, "Computer added");
//			connect.commit();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			try {
//				connect.rollback();
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			e.printStackTrace();
//		} finally {
//			try {
//				connect.close();
//			} catch (SQLException e) {}
//		}
//	}
//	

//
//	public Computer updateComputer(Computer computer) {
//		// TODO Auto-generated method stub
//		return computerDAO.update(computer);
//	}
//	

//	
//	public int countComputer() {
//		return computerDAO.count();
//	}
}
