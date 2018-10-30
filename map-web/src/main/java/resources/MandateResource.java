package resources;



import entities.Mandate;
import interfaces.MandateServiceLocale;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Path("mandates")
@RequestScoped
public class MandateResource {

	public static List<Mandate> mandates = new ArrayList<>();

	@EJB(beanName = "MandateService")
	MandateServiceLocale ms;
	//MailSenderLocal ml;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addMandate(Mandate m) {

		if (m != null) {

			mandates.add(m);

			ms.persistMandate(m);
			


		}

		return Response.status(Status.CREATED).entity("Mandate Created").build();

	}


	
	@DELETE
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteMandate(@PathParam(value = "id") String id){
		
		 ms.removeMandatet(Integer.parseInt(id));
		
		return Response.status(Status.OK).entity("delete successful").build();
	}
	
	

	/*@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response afficherList(@PathParam(value = "id") int id) {
		Mandate m = ms.afficherMandate(id);

		return Response.status(Status.ACCEPTED).entity(m).build();
	}*/
	
@PUT
	@Path("{id}/{champ}/{newValeur}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateMandate(@PathParam(value = "id") String id
			, @PathParam(value = "champ") Date champ, @PathParam(value = "newValeur") Date newValeur)
	{
            
		 if (champ.equals("endDate")){
			Mandate a = ms.findMandate(Integer.parseInt(id));
			a.setEndDate(newValeur);
			ms.mergeMandate(a);
			return Response.status(Status.CREATED).entity("End DAte Updated").build(); }
		 
		 if (champ.equals("startDate")){
				Mandate a = ms.findMandate(Integer.parseInt(id));
				a.setStartDate(newValeur);
				ms.mergeMandate(a);
				return Response.status(Status.ACCEPTED).entity("Start Date Updated").build(); }
		 

		 
		
		

		return Response.status(Status.BAD_REQUEST).entity("Mandate NonUpdated").build();

	}

	

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response ListMandate(@QueryParam(value = "projectId") int projectId,@QueryParam(value = "countProject") int countProject,@QueryParam(value = "countResource") int countResource,
								  @QueryParam(value = "idResource") int idResource, @QueryParam(value = "startDate") String startDate,
								  @QueryParam(value = "endDate") String endDate, @QueryParam(value = "mandateId") int mandateId,
								  @QueryParam(value = "fee") Double fee,  @QueryParam(value = "archive") boolean archive) {
		if (projectId != 0) {
			return Response.status(Status.OK).entity(ms.searchMandateByProject(projectId)).build();
		} else if (countProject != 0) {
			return Response.status(Status.OK).entity(ms.getCountByProject(countProject)).build();
		}
		else if (countResource != 0) {
			return Response.status(Status.OK).entity(ms.getCountByResource(countResource)).build();
		} else if (idResource != 0) {
			return Response.status(Status.OK).entity(ms.getMandateByResource(idResource)).build();
		} else if (mandateId != 0) {

			return Response.status(Status.OK).entity(ms.findMandate(mandateId)).build();
		} else if (startDate != null) {
			return Response.status(Status.OK).entity(ms.getMandateByStartDate(startDate)).build();
		} else if (endDate != null) {
			return Response.status(Status.OK).entity(ms.getMandateByendDate(endDate)).build();
		} else if (fee != null) {
			return Response.status(Status.OK).entity(ms.getMandateByFee(fee)).build();
		}else if (archive==true) {
			return Response.status(Status.OK).entity(ms.getArchivedMandate()).build();
		}
		else {
			return Response.status(Status.OK).entity(ms.listAll()).build();
		}
	}
	
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateMandate(Mandate m )
	{	if(m==null)
		{
			ms.archiveMandate();
			return Response.status(Status.CREATED).entity("Mandate Archived").build();
		}

		ms.mergeMandate(m);
		
		return Response.status(Status.CREATED).entity("Mandate Updated succesfully").build(); }

        

	}
	
	
	
	


