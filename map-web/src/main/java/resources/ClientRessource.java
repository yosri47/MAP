package resources;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.print.attribute.standard.PrinterMessageFromOperator;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import javax.ws.rs.core.Response.Status;

import entities.Client;
import interfaces.ClientServiceLocale;
import services.ClientService;


@Path("Clients")
@RequestScoped
public class ClientRessource {

	public static List<Client> clients = new ArrayList<>();

	@EJB(beanName = "ClientService")
	ClientServiceLocale cs;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addClient(Client c) {

		if (c != null) {
			clients.add(c);

			cs.persistClient(c);

		}

		return Response.status(Status.CREATED).entity("Client created").build();

	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response afficherList()

	{
		if (!cs.afficherClients().isEmpty())
			return Response.ok(cs.afficherClients()).build();
		return Response.status(Status.NO_CONTENT).build();
	}
	
	@DELETE
	@Path("{id}")
	@Consumes(MediaType.TEXT_PLAIN)
	public Response deleteClient(@PathParam(value = "id") String id){
		
		 cs.removeClient(Integer.parseInt(id));
		
		return Response.status(Status.OK).entity("delete successful").build();
	}
	
	

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response afficherList(@PathParam(value = "id") int id) {
		Client cl = cs.afficherClient(id);

		return Response.status(Status.ACCEPTED).entity(cl).build();
	}

}
