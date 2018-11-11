package resources;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import entities.Break;
import interfaces.BreakServiceLocal;

@Path("leaves")
@RequestScoped
public class BreakResource {
	@EJB(beanName = "BreakService")
	private BreakServiceLocal ls;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response requestBreak(Break l) {
		if (l != null) {
			l.setGranted(false);
			ls.persistBreak(l);
			return Response.status(Status.CREATED).entity("OK: " + l.getLeaveId() + " requested").build();
		} else {
			return Response.status(Status.NOT_ACCEPTABLE).entity("ERREUR D AJOUT").build();
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateBreak(Break l) {
		ls.mergeBreak(l);
		return Response.status(Status.OK).entity("Holiday modified").build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response ListBreaks(@QueryParam(value = "id") String id, @QueryParam(value = "startDate") String startDate,
			@QueryParam(value = "endDate") String endDate, @QueryParam(value = "isGranted") String isGranted,
			@QueryParam(value = "resource") String ressource) {
		if (id != null) {
			int idL = Integer.parseInt(id);
			return Response.status(Status.OK).entity(ls.findBreak(idL)).build();
		} else if (startDate != null) {
			return Response.status(Status.OK).entity(ls.searchByStartDate(startDate)).build();
		} else if (endDate != null) {
			return Response.status(Status.OK).entity(ls.searchByEndDate(endDate)).build();
		} else if (isGranted != null) {
			return Response.status(Status.OK).entity(ls.searchByGrant(isGranted)).build();
		} else if (ressource != null) {
			return Response.status(Status.OK).entity(ls.searchByResource(ressource)).build();
		} else {
			return Response.status(Status.OK).entity(ls.listAll()).build();
		}
	}

	@GET
	@Path("/grant")
	@Produces(MediaType.APPLICATION_JSON)
	public Response grantLeave(@QueryParam(value = "id") String id) {
		if (ls.acceptBreak(id) > 0) {
			return Response.status(Status.OK).entity("OK").build();
		}
		return Response.status(Status.FORBIDDEN).entity("ERREUR !").build();
	}

}
