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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import entities.Resume;
import interfaces.ResumeServiceLocal;

@Path("resumes")
@RequestScoped
public class ResumeResource {
	@EJB(beanName = "ResumeService")
	private ResumeServiceLocal rs;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addResume(Resume r) {
		if (r != null) {
			rs.persistResume(r);
			return Response.status(Status.CREATED).entity("OK: " + r.getResumeId() + " ajouté").build();
		} else {
			return Response.status(Status.NOT_ACCEPTABLE).entity("ERREUR D AJOUT").build();
		}
	}
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateResume(Resume r) {
		rs.mergeResume(r);
		return Response.status(Status.OK).entity("Resume modified").build();
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response ListResume(@QueryParam(value="id") String id,@QueryParam(value="resource") String resource)
	{
		if(id != null){
			int idR = Integer.parseInt(id);
			return Response.status(Status.OK).entity(rs.findResume(idR)).build();
		}else if(resource != null){
			return Response.status(Status.OK).entity(rs.findResumeByResource(resource)).build();
		}else{
			return Response.status(Status.OK).entity(rs.listAll()).build();
		}
	}
	@DELETE
	@Consumes(MediaType.TEXT_PLAIN)
	public Response deleteResume(@QueryParam(value="id")String id)
	{
		if(rs.removeHolidayById(id) > 0){
			return Response.status(Status.OK).entity("Removed").build();
		}else{
			return Response.status(Status.NOT_FOUND).entity("Resume not found").build();
		}
	}
	
}
