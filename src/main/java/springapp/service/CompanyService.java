package springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import springapp.domain.Company;
import springapp.repository.JdbcCompanyDao;
@Service
@Transactional
public class CompanyService {
	@Autowired
	JdbcCompanyDao company ;
	
	public CompanyService()
	{
		super();
	}

	public Company find(long id) {
		return company.find(id);
	}
	
	public List<Company> getAllCompany() {
		return company.findAll();
	}

	public Company create() {
		return company.create();
	}
	
	public Company create(long id) {
		return company.create(id);
	}
//	
//	private static class CompanyServiceHolder
//	{
//		private final static CompanyService instance = new CompanyService();
//	}
//	
//	public static CompanyService getInstance() {
//		return CompanyServiceHolder.instance;
//	}
	

//	public List<Company> getAllCompany(int limitMin,int limitMax,String search,String order,String sens) {
//		return companyDAO.findAll(limitMin,limitMax,search,order,sens);
//	}
//	

}
