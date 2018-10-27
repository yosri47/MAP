package resources;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import entities.Holiday;
import entities.Skill;
import interfaces.SkillServiceLocal;

@Path("skills")
@RequestScoped
public class SkillResource {
	@EJB(beanName = "SkillService")
	private SkillServiceLocal ss;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addSkill(Skill s) {
		if (s != null) {
			ss.persistSkill(s);
			return Response.status(Status.CREATED).entity("OK: " + s.getCategory() + ": " + s.getName() + " ajouté")
					.build();
		} else {
			return Response.status(Status.NOT_ACCEPTABLE).entity("ERREUR D AJOUT").build();
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateSkill(Skill s) {
		ss.mergeSkill(s);
		return Response.status(Status.OK).entity("Skill modified").build();
	}

	@DELETE
	@Consumes(MediaType.TEXT_PLAIN)
	public Response deleteSkill(@QueryParam(value = "id") String id) {
		if (ss.removeSkillById(id) > 0) {
			return Response.status(Status.OK).entity("Removed").build();
		} else {
			return Response.status(Status.NOT_FOUND).entity("Skill not found").build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response ListSkills(@QueryParam(value = "countcategory") String countcategory,
			@QueryParam(value = "countname") String countname, @QueryParam(value = "id") String id,
			@QueryParam(value = "category") String category, @QueryParam(value = "name") String name) {
		if (countcategory != null) {
			return Response.status(Status.OK).entity(ss.getCountByCategory(countcategory)).build();
		}else if(countname != null){
			return Response.status(Status.OK).entity(ss.getCountByName(countname)).build();
		}else if (id != null) {
			int idH = Integer.parseInt(id);
			return Response.status(Status.OK).entity(ss.findSkill(idH)).build();
		} else if (name != null) {
			return Response.status(Status.OK).entity(ss.searchByName(name)).build();
		} else if (category != null) {
			return Response.status(Status.OK).entity(ss.searchByCategory(category)).build();
		} else {
			return Response.status(Status.OK).entity(ss.listAll()).build();
		}
	}

}
