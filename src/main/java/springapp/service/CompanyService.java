package springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springapp.domain.Company;
import springapp.repository.JdbcCompanyDao;

@Service
public class CompanyService {
	JdbcCompanyDao company ;
	
	private CompanyService()
	{}
	
	public JdbcCompanyDao getCompany() {
		return company;
	}
	@Autowired
	public void setCompany(JdbcCompanyDao company) {
		this.company = company;
	}

	public Company find(long id) {
		return company.find(id);
	}
	
	public List<Company> getAllCompany() {
		return company.findAll();
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
