package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repository.CompanyDao;
import domain.Company;

@Service
@Transactional
public class CompanyService {
	@Autowired
	CompanyDao company ;
	
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
}
