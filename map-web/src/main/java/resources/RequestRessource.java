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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import entities.Admin;
import entities.Client;
import entities.Message;
import entities.Request;
import interfaces.AdminLocal;
import interfaces.ClientServiceLocale;
import interfaces.MessageLocal;
import interfaces.RequestLocal;



@Path("req")
@RequestScoped
public class RequestRessource {
	@EJB(beanName="RequestService")
	RequestLocal em;
	

	
	@EJB(beanName="MessageService")
	MessageLocal cloc;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addrequest(Request req) {
		 int res = em.ajouterRequest(req);
			return Response.status(Status.OK).entity("Created"+res).build();
		}

	
	@POST
	@Path("add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addrequesttest(Request req ,@QueryParam("idcl") int cl ,@QueryParam("idad")int ad) {
		
	 Admin a= cloc.getAdminbyid(ad);
	Client c = cloc.getClientbyid(cl);
	 req.setReqadmin(a);
	 req.setReqcl(c);
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
	@Path("getall")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() {
		List<Request> mes = new ArrayList<>();
		mes=  em.getall();
			return Response.status(Status.OK).entity(mes).build();
		}
	
	@PUT
	@Path("valider")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

		public Response ValiderRequete(@QueryParam("idreq") int idreq) {
			if (em.getRequestbyid(idreq)==null){
				return Response.status(Status.NOT_ACCEPTABLE).entity("Verifier les Donnees").build();

			}
				else{
Request reqs =em.getRequestbyid(idreq);
			 Message res =  em.Valider(reqs);
			 cloc.ajouterMessage(res);
				return Response.status(Status.OK).entity("Le Message a été envoyé").build();}
			}
	

}
