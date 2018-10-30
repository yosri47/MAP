package resources;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import interfaces.UpdateServiceLocal;


@Path("setup")
@RequestScoped
public class UpdateResource {
	@EJB(beanName = "UpdateService")
	private UpdateServiceLocal us;
	
	@GET
	public Response setup()
	{
		us.generateUsers();
		us.generateResources();
		us.generateSkills();
		return Response.status(Status.OK).entity("OK").build();
	}

}
