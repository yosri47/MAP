package resources;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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

import entities.Client;
import entities.Project;
import interfaces.ClientServiceLocale;
import interfaces.OrganigramServiceLocale;
import interfaces.ProjectServiceLocal;

@Path("Projects")
@RequestScoped
public class ProjectRessource {
	
	@PersistenceContext(unitName="pidev-ejb")
	EntityManager em;

	@EJB(beanName = "ProjectService")
	ProjectServiceLocal ps ;
	
	@EJB(beanName = "ClientService")
	ClientServiceLocale cs;
	
	@EJB(beanName = "OrganigramService")
	OrganigramServiceLocale os;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addProject(Project p) {

		if (p != null) {
			

			ps.persistProject(p);

		}

		return Response.status(Status.CREATED).entity("Project created").build();

	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response afficherList()

	{
		if (!ps.afficherProjects().isEmpty())
			return Response.ok(ps.afficherProjects()).build();
		return Response.status(Status.NO_CONTENT).build();
	}
	
	@DELETE
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteProject(@PathParam(value = "id") String id){
		
		 ps.removeProject(Integer.parseInt(id));
		
		return Response.status(Status.OK).entity("delete successful").build();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response afficherList(@PathParam(value = "id") int id) {
		Project p = ps.afficherProject(id);

		return Response.status(Status.ACCEPTED).entity(p).build();
	}
	
	@GET
	@Path("ProjectType/{ProjectType}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchByProjectType(@PathParam(value = "ProjectType") String ProjectType) {
		
		if (!ps.searchProjectByName(ProjectType).isEmpty())
			return Response.ok(ps.searchProjectByName(ProjectType)).build();
		return Response.status(Status.NOT_FOUND).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateProject(Project p ) 
	{
		
		ps.mergeProject(p);
		
		return Response.status(Status.CREATED).entity("Project Updated succesfully").build(); }
       
	   @PUT
	   @Consumes(MediaType.APPLICATION_JSON)
	   @Path("{idClient}/{idProject}")
       public Response SetProjectOwnerAndApprove(@PathParam(value = "idClient") String idClient
   			,@PathParam(value = "idProject") String idProject ) {
		   
		   // selectionner le projet dabord
		   
		   Project p = ps.findProject(Integer.parseInt(idProject));
		   
		   //Affecter le client 
		   
		   p.setOwner(cs.findClient(Integer.parseInt(idClient)));
		   p.setApproved(true);
		   
		   //valider affectation
		   
		   ps.mergeProject(p);
		   
			return Response.status(Status.CREATED).entity("Project affected to client and approved").build(); 
			
	   }
	   
	   @PUT
	   @Consumes(MediaType.APPLICATION_JSON)
	   @Path("Organigram/{idOrganigram}/{idProject}")
       public Response SetProjectOrganigram(@PathParam(value = "idOrganigram") String idOrganigram
   			,@PathParam(value = "idProject") String idProject ) {
		   
		   // selectionner le projet dabord
		   
		   Project p = ps.findProject(Integer.parseInt(idProject));
		   
		   //Affecter l'organigram
		   
		   p.setOrganigram(os.findOrganigram(Integer.parseInt(idOrganigram)));
		   
		   //valider affectation
		   
		   ps.mergeProject(p);
		   
			return Response.status(Status.CREATED).entity("Organigram affected to client").build(); 
			
	   }
	   
	   
	   
	   
	   
	   
	   
	   
	   }

	




