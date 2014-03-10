package springapp.repository;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import springapp.domain.Computer;
@Repository
public interface ComputerDAO {
	public Computer find(long id);
	public Computer create(Computer obj);
	public Computer update(Computer obj);
	public void delete(long id);
	public List<Computer> findAll(int limitMin,int limitMax,String search,String order,String sens);
	public List<Computer> findAll();
	public int count();
	public void create(String name, Date introducedDate, Date discontinuedDate, long companyId);
}
