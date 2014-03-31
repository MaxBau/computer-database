package impl;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import services.ComputerService;
import webservices.ComputerWS;
import domain.Computer;

@WebService(endpointInterface="webservices.ComputerWS")
@Service("computerWS")
public class ComputerWSImpl implements ComputerWS {
	
	@Autowired
	private ComputerService computerService;

	@WebMethod
	public List<Computer> getComputerList() {
		
		return computerService.findAll();
 
	}
}
