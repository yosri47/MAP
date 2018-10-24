package resources;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import entities.Holiday;
import services.HolidayService;

@Path("holidays")
public class HolidayResource {
	HolidayService hs;
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response add(Holiday h)
	{
		if(h==null){
			return Response.status(Status.NO_CONTENT).entity("ERREUR d'ajout").build();
			}else if(!hs.contains(h)){
				return Response.status(Status.NO_CONTENT).entity("ERREUR d'ajout").build();
			}
		hs.persistHoliday(h);
		return Response.status(Status.CREATED).entity("OK: "+h.getHolidayId()+" ajouté").build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchEmploye(@QueryParam(value="startdate")String startDate,@QueryParam(value="enddate")String endDate,@QueryParam(value="id")String idH)
	{
		if(idH != null) {
			int id = Integer.parseInt(idH);
			Holiday h = new Holiday();
			h.setHolidayId(id);
			if(!hs.contains(h))
			{
				return Response.status(Status.NOT_FOUND).entity("Erreur: Liste vide").build();
			}
			return Response.status(Status.OK).entity(hs.findHoliday(id)).build();
		}
		if(startDate != null){
			Holiday holiday = new Holiday();
			Date date;
			try {
				date = new SimpleDateFormat("dd/MM/yyyy").parse(startDate);
			} catch (ParseException e) {
				e.printStackTrace();
				return Response.status(Status.SERVICE_UNAVAILABLE).entity("PARSING ERROR"+ e.getMessage()).build();
			}
			holiday.setStartDate(date);
			//TODO
			//return Response.status(Status.FOUND).entity(employes).build();
		}
		return Response.status(Status.OK).entity("TODO").build();

	}
	
	

}
