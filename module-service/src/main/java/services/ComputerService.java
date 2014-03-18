package services;

import java.util.ArrayList;
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
	private JdbcComputerDao computerDao;
	
	public ComputerService()
	{
		super();
	}
	
	public List<Computer> getAllComputers()
	{
		return computerDao.findAll();
	}
	@Transactional
	public WrapperListInt findAll(String search,String order,String sens,String limitMin, String limitMax) {
		
		WrapperListInt wrapper = new WrapperListInt(computerDao.findAll(search,order,sens,limitMin, limitMax),computerDao.count(search));
		
		ComputerDTO cDto = new ComputerDTO();
		List<ComputerDTO> computerList = new ArrayList<ComputerDTO>();
		for (Object computer : wrapper.getList()) {
			computerList.add(cDto.toDto((Computer) computer));
		}
		
		wrapper.setList(computerList);
		return wrapper;
	}
	
	public void addComputer(String name, String introduced, String discontinued, long companyId) {

		computerDao.add(name, LocalDate.parse(introduced), LocalDate.parse(discontinued), companyId);
	}
	
	public ComputerDTO getComputerById(long id) {
		return new ComputerDTO().toDto(computerDao.find(id));
	}
	
	public Computer create(String name, String introduced, String discontinued, Company company) {
		
		LocalDate introducedSubmit = LocalDate.parse(introduced);
		LocalDate discontinuedSubmit = LocalDate.parse(discontinued);
		return computerDao.create(name, introducedSubmit, discontinuedSubmit, company);
	}
	
	public Computer create(long id,String name, String introduced, String discontinued, Company company) {
		
		LocalDate introducedSubmit = LocalDate.parse(introduced);
		LocalDate discontinuedSubmit = LocalDate.parse(discontinued);
		
		return computerDao.create(id, name, introducedSubmit, discontinuedSubmit, company);
	}
	

	public void update(ComputerDTO computerDto) {

		computerDao.update(new ComputerDTO().fromDto(computerDto));
	}
	
	public void deleteComputer(long id) {
		computerDao.delete(id);
	}
	
	public List<Computer> findAll(String search) {
		return computerDao.findAll(search);
	}
}
