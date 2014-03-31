package webservices;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import domain.Computer;

public interface ComputerWS {

	@GET
	@Produces("application/xml")
	@Path("/getList")
	public List<Computer> getComputerList();

}
