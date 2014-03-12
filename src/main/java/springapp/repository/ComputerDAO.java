package springapp.repository;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import springapp.domain.Company;
import springapp.domain.Computer;


public interface ComputerDAO {
	public Computer find(long id);
	public Computer create(Computer obj);
	public Computer update(Computer obj);
	public void delete(long id);
	public List<Computer> findAll(int limitMin,int limitMax,String search,String order,String sens);
	public List<Computer> findAll();
	public List<Computer> findAll(String search);
	public int count();
	public Computer create(String name, Date introducedDate, Date discontinuedDate, long companyId);
	public void add(String name, Date introducedDate, Date discontinuedDate, long companyId);
	Computer create(String name, Date introducedDate, Date discontinuedDate,
			Company company);
	public Computer create(long id,String name, Date introducedDate, Date discontinuedDate, Company company);
}
