package webservices;

import java.util.List;

import javax.jws.WebService;

import domain.Computer;
@WebService
public interface ComputerWS {

		
		public List<Computer> getComputerList();

}
