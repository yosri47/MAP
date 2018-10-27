package resources;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import entities.Holiday;
import interfaces.HolidayServiceLocal;
import services.HolidayService;

@Path("holidays")
@RequestScoped
public class HolidayResource {
	@EJB(beanName="HolidayService")
	private HolidayServiceLocal hs;		
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addHoliday(Holiday h)
	{
		if(h!=null)
		{
		hs.persistHoliday(h);
			return Response.status(Status.CREATED).entity("OK: "+h.getHolidayId()+": "+h.getName()+" ajouté").build();
		}else{
			return Response.status(Status.NOT_ACCEPTABLE).entity("ERREUR D AJOUT").build();
		}
	}
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateHoliday(Holiday h)
	{
		hs.mergeHoliday(h);
		return Response.status(Status.OK).entity("Holiday modified").build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response ListHolidays(@QueryParam(value="id") String id,@QueryParam(value="name") String name, @QueryParam(value="startDate") String startDate)
	{
		if(id != null){
			int idH = Integer.parseInt(id);
			return Response.status(Status.OK).entity(hs.findHoliday(idH)).build();
		}else if(name != null) {
				return Response.status(Status.OK).entity(hs.searchByName(name)).build();
		}else if(startDate != null){
			return Response.status(Status.OK).entity(hs.searchByStartDate(startDate)).build();
		}else{
			return Response.status(Status.OK).entity(hs.listAll()).build();
		}
	}
	@DELETE
	@Consumes(MediaType.TEXT_PLAIN)
	public Response deleteEmploye(@QueryParam(value="id")String id)
	{
		if(hs.removeHolidayById(id) > 0){
			return Response.status(Status.OK).entity("Removed").build();
		}else{
			return Response.status(Status.NOT_FOUND).entity("Holiday not found").build();
		}
	}
	
	
	

}
