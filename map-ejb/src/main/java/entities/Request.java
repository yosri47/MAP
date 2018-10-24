package entities;

import java.sql.Date;
import java.util.List;

public class Request {
	private int requestId;
	private String duration;
	private double cost;
	private Date startDate;
	private Date endDate;
	
	private List<Skill> skillsneeded;
	private List<Ressource> resourcesSuggested; 

}
