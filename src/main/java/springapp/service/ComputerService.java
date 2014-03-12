package springapp.service;

import java.util.List;

import org.joda.time.LocalDate;
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
		LocalDate introducedSubmit = LocalDate.parse(introducedDate);
		LocalDate discontinuedSubmit = LocalDate.parse(discontinuedDate);
		
		computer.add(name, introducedSubmit.toDate(), discontinuedSubmit.toDate(), companyId);
	}
	
	public Computer getComputerById(long id) {
		return computer.find(id);
	}
	
	public Computer create(String name, String introduced, String discontinued, Company company) {
		
		LocalDate introducedSubmit = LocalDate.parse(introduced);
		LocalDate discontinuedSubmit = LocalDate.parse(discontinued);
		return computer.create(name, introducedSubmit.toDate(), discontinuedSubmit.toDate(), company);
	}
	
	public Computer create(long id,String name, String introduced, String discontinued, Company company) {
		
		LocalDate introducedSubmit = LocalDate.parse(introduced);
		LocalDate discontinuedSubmit = LocalDate.parse(discontinued);
		
		return computer.create(id, name, introducedSubmit.toDate(), discontinuedSubmit.toDate(), company);
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
}
