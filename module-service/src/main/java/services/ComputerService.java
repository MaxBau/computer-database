package services;

import java.util.List;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Company;
import domain.Computer;
import domain.WrapperListInt;
import dto.ComputerDTO;
import repository.JdbcComputerDao;


@Service

public class ComputerService {
	@Autowired
	private JdbcComputerDao computer;
	
	public ComputerService()
	{
		super();
	}
	
	public List<Computer> getAllComputers()
	{
		return computer.findAll();
	}
	@Transactional
	public WrapperListInt findAll(String search,String order,String sens,String limitMin, String limitMax) {
		
		WrapperListInt wrapper = new WrapperListInt(computer.findAll(search,order,sens,limitMin, limitMax),computer.count(search));
		return wrapper;
	}
	
	public void addComputer(String name, LocalDate introduced, LocalDate discontinued, long companyId) {

		computer.add(name, introduced, discontinued, companyId);
	}
	
	public ComputerDTO getComputerById(long id) {
		return new ComputerDTO().toDto(computer.find(id));
	}
	
	public Computer create(String name, String introduced, String discontinued, Company company) {
		
		LocalDate introducedSubmit = LocalDate.parse(introduced);
		LocalDate discontinuedSubmit = LocalDate.parse(discontinued);
		return computer.create(name, introducedSubmit, discontinuedSubmit, company);
	}
	
	public Computer create(long id,String name, String introduced, String discontinued, Company company) {
		
		LocalDate introducedSubmit = LocalDate.parse(introduced);
		LocalDate discontinuedSubmit = LocalDate.parse(discontinued);
		
		return computer.create(id, name, introducedSubmit, discontinuedSubmit, company);
	}
	

	public void update(ComputerDTO computerDto) {

		computer.update(new ComputerDTO().fromDto(computerDto));
	}
	
	public void deleteComputer(long id) {
		computer.delete(id);
	}
	
	public List<Computer> findAll(String search) {
		return computer.findAll(search);
	}
}
