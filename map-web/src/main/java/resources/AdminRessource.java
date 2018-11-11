package resources;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import entities.Message;
import entities.Request;
import interfaces.AdminLocal;
import interfaces.RequestLocal;
import interfaces.messageRemote;

@Path("admin")
@RequestScoped
public class AdminRessource {

	

	@EJB(beanName="AdminService")
	AdminLocal em;
	

	@EJB(beanName="RequestService")
	RequestLocal request;
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

		public Response ValiderRequete(Request req) {
			if (request.getRequestbyid(req.getRequestId())==null){
				return Response.status(Status.NOT_ACCEPTABLE).entity("Verifier les Donnees").build();

			}
				else{
Request reqs =request.getRequestbyid(req.getRequestId());
			 int res =  em.Valider(reqs);
				return Response.status(Status.OK).entity("Le Message a été envoyé").build();}
			}
}
