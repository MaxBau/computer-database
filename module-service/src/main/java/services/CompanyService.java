package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

import domain.Company;
import repository.JdbcCompanyDao;

@Service
@Transactional
public class CompanyService {
	@Autowired
	JdbcCompanyDao company ;
	
	@Autowired
	private PlatformTransactionManager transactionManager;
	
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
}
