package resources;

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

import entities.Request;
import interfaces.RequestLocal;



@Path("req")
@RequestScoped
public class RequestRessource {
	@EJB(beanName="RequestService")
	RequestLocal em;
	
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addrequest(Request req) {
		 int res = em.ajouterRequest(req);
			return Response.status(Status.OK).entity("Created"+res).build();
		}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response Modifier(Request req) {
		 int res =  em.modifierRequest(req);
			return Response.status(Status.OK).entity("Updated"+res).build();
		}
	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response supprimer(@PathParam(value="id")int idreq) {
		   Request req = em.getRequestbyid(idreq);
	     int res = em.deleteRequest(req);
			return Response.status(Status.OK).entity("Removed"+res).build();
		}
	
	@GET
	@Path("{reqid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getmessagebyid(@PathParam(value="reqid")int reqid) {
		Request res =   em.getRequestbyid(reqid);
			return Response.status(Status.OK).entity("L element est"+res.getRequestId()+ "req cost est "+res.getCost()).build();
		}
	
}
