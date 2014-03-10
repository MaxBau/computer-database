package springapp.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	public void addComputer(String name, Date introducedDate, Date discontinuedDate, long companyId) {
		computer.create(name, introducedDate, discontinuedDate, companyId);
		
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
//	public Computer getComputerById(long id) {
//		return computerDAO.find(id);
//	}
//
//	public Computer updateComputer(Computer computer) {
//		// TODO Auto-generated method stub
//		return computerDAO.update(computer);
//	}
//	
//	public void deleteComputer(long id) {
//		computerDAO.delete(id);
//	}
//	
//	public int countComputer() {
//		return computerDAO.count();
//	}
}
