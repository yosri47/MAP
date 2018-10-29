package resources;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import entities.Organigram;
import interfaces.OrganigramServiceLocale;

@Path("Organigrams")
@RequestScoped
public class OrganigramRessource {


	@EJB(beanName = "OrganigramService")
	OrganigramServiceLocale os;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addOrganigram(Organigram o) {

		if (o != null) {


			os.persistOrganigram(o);

		}

		return Response.status(Status.CREATED).entity("Organigram created").build();

	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response afficherList()

	{
		if (!os.afficherOrganigrams().isEmpty())
			return Response.ok(os.afficherOrganigrams()).build();
		return Response.status(Status.NO_CONTENT).build();
	}
	
	@DELETE
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteOrganigram(@PathParam(value = "id") String id){
		
		 os.removeOrganigram(Integer.parseInt(id));
		
		return Response.status(Status.OK).entity("delete successful").build();
	}
	
	

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response afficherList(@PathParam(value = "id") int id) {
		Organigram cl = os.afficherOrganigram(id);

		return Response.status(Status.ACCEPTED).entity(cl).build();
	}
	
	
	
	
	
	
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateOrganigram(Organigram a ) 
	{
		
		os.mergeOrganigram(a);
		
		return Response.status(Status.CREATED).entity("Organigram Updated succesfully").build(); }


}
