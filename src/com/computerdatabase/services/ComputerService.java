package com.computerdatabase.services;

import java.util.List;

import com.computerdatabase.classes.Computer;
import com.computerdatabase.dao.ComputerDAO;


public class ComputerService {
	private ComputerDAO computerDAO= ComputerDAO.getInstance();
	private ComputerService()
	{}
	
	private static class ComputerServiceHolder
	{
		private final static ComputerService instance = new ComputerService();
	}
	
	public static ComputerService getInstance() {
		return ComputerServiceHolder.instance;
	}
	
	public List<Computer> getAllComputers()
	{
		return computerDAO.findAll();
	}
	
	public void createComputer(Computer obj) {
		computerDAO.create(obj);
	}
	
	public Computer getComputerById(long id) {
		return computerDAO.find(id);
	}

	public Computer updateComputer(Computer computer) {
		// TODO Auto-generated method stub
		return computerDAO.update(computer);
	}
}
