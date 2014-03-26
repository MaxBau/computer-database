package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repository.CompanyRepository;
import domain.Company;

@Service
@Transactional
public class CompanyService {
	@Autowired
	private CompanyRepository companyRepository;
	
	public CompanyService()
	{
		super();
	}

	public Company find(long id) {
		return companyRepository.findOne(id);
	}
	
	public List<Company> getAllCompany() {
		return companyRepository.findAll();
	}
}
