package springapp.repository;

import java.sql.Connection;
import java.util.List;

import org.springframework.stereotype.Repository;

import springapp.domain.Company;

@Repository
public interface CompanyDAO {
	public Company find(long id);
	public Company create(Connection connect, Company obj);
	public Company update(Company obj);
	public void delete(long id);
	public List<Company> findAll(int limitMin,int limitMax,String search,String order,String sens);
	public List<Company> findAll();
	public int count();
}
