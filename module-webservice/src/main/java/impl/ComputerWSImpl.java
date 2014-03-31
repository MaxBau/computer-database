package impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import services.ComputerService;
import webservices.ComputerWS;
import domain.Computer;

public class ComputerWSImpl implements ComputerWS {
	
	@Autowired
	private ComputerService computerService;

	
	public List<Computer> getComputerList() {
		
		return computerService.findAll();
 
	}
}
