package resources;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import interfaces.UpdateServiceLocal;


@Path("setup")
@RequestScoped
public class UpdateResource {
	@EJB(beanName = "UpdateService")
	private UpdateServiceLocal us;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response setup()
	{
		us.updateResourcesAvailability();
		return Response.status(Status.OK).entity("ok").build();
	}

}
