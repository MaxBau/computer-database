package webservices;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import domain.Computer;

public interface ComputerWS {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getList")
	public List<Computer> getComputerList();

}
