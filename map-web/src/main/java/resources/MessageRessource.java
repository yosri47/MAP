package resources;


import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
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

import entities.Client;
import entities.Message;
import interfaces.ClientServiceLocale;
import interfaces.ClientServiceRemote;
import interfaces.messageRemote;

@Path("mes")
@RequestScoped
public class MessageRessource {

	@EJB(beanName="MessageService")
	messageRemote em;


	@EJB(beanName="ClientService")
	ClientServiceLocale cl;
	
@POST
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public Response add(Message m) {
	
	
	if (!em.verif(m.getObject())||!em.verif(m.getType())){
		return Response.status(Status.NOT_ACCEPTABLE).entity("Verifier les Donnees").build();

	}
	else{
	 int res = em.ajouterMessage(m);
		return Response.status(Status.CREATED).entity("Created"+m.getMessageId()).build();}
	
	}

@PUT
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

	public Response Modifier(Message m) {
		if (!em.verif(m.getObject())||!em.verif(m.getType())||em.getMessagebyid(m.getMessageId())==null){
			return Response.status(Status.NOT_ACCEPTABLE).entity("Verifier les Donnees").build();

		}
			else{

		 int res =  em.modifier(m);
			return Response.status(Status.OK).entity("Updated"+res).build();}
		}
@DELETE
@Path("{id}")
@Produces(MediaType.APPLICATION_JSON)
public Response supprimer(@PathParam(value="id")int idmessage) {
	if (em.getMessagebyid(idmessage)==null){
		return Response.status(Status.NOT_ACCEPTABLE).entity("Verifier les Donnees").build();

	}
	else{
	Message ms = em.getMessagebyid(idmessage);
	   
     int res = em.delete(ms);
		return Response.status(Status.OK).entity("Removed"+res).build();}
	}
@GET
@Path("{messageid}")
@Produces(MediaType.APPLICATION_JSON)
public Response getmessagebyid(@PathParam(value="messageid")int idmessage) {
	 Message res =   em.getMessagebyid(idmessage);
	
	 if ( res== null ){ return Response.status(Status.BAD_REQUEST).entity("Verifier les Donnees").build();
	 
	}
	 else{return Response.status(Status.OK).entity("L element est"+res.getMessageId()+ "type est "+res.getType()).build();
		
}	 
}
@GET
@Path("getall")
@Produces(MediaType.APPLICATION_JSON)
public Response getAll() {
	List<Message> mes = new ArrayList<>();
	mes=  em.getall();
		return Response.status(Status.OK).entity(mes).build();
	}

@GET
@Path("getbytype")
@Produces(MediaType.APPLICATION_JSON)
public Response getAllbytype(@QueryParam("type") String type) {
	List<Message> mes = new ArrayList<>();
	mes=  em.getMessagebytype(type);
		return Response.status(Status.OK).entity(mes).build();
	}
@GET
@Path("getbyobject")
@Produces(MediaType.APPLICATION_JSON)
public Response getAllbyObject(@QueryParam("getbyobject") String Objet) {
	List<Message> mes = new ArrayList<>();
	mes=  em.getMessagebyObjet(Objet);
		return Response.status(Status.OK).entity(mes).build();
	}

@GET
@Path("send")
@Produces(MediaType.APPLICATION_JSON)
public Response getmessagebysend(@QueryParam("idsend") int idmessage) {
	List<Message> mes = new ArrayList<>();
	mes=  em.getMessagebysend(idmessage);
		return Response.status(Status.OK).entity(mes).build();
		
}	 
@GET
@Path("allclientsend")
@Produces(MediaType.APPLICATION_JSON)
public Response getmessagebyclient(@QueryParam("idclient") int client) {
	List<Client> mes = new ArrayList<>();
	mes=  em.getMessagebyClient(client);
		return Response.status(Status.OK).entity(mes).build();
		
}
@GET
@Path("recu")
@Produces(MediaType.APPLICATION_JSON)
public Response getmessagebyrecu(@QueryParam("idrecu") int idmessage) {
	List<Message> mes = new ArrayList<>();
	mes=  em.getMessagebyrecu(idmessage);
		return Response.status(Status.OK).entity(mes).build();
		
}


}

