package com.computerdatabase.services;

import java.util.List;

import com.computerdatabase.classes.Company;
import com.computerdatabase.dao.CompanyDAO;

public class CompanyService {
	private CompanyDAO companyDAO = CompanyDAO.getInstance();
	
	private CompanyService()
	{}
	
	private static class CompanyServiceHolder
	{
		private final static CompanyService instance = new CompanyService();
	}
	
	public static CompanyService getInstance() {
		return CompanyServiceHolder.instance;
	}
	public List<Company> getAllCompany() {
		return companyDAO.findAll();
	}

}
