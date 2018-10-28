package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Request  implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int requestId;
	private String duration;
	private double cost;
	
	@Temporal(TemporalType.DATE)
	private Date startDate;
	@Temporal(TemporalType.DATE)
	private Date endDate;
	private String typeressource ;

	
	public Request(int requestId, String duration, double cost, Date startDate, Date endDate) {
		this.requestId = requestId;
		this.duration = duration;
		this.cost = cost;
		this.startDate = startDate;
		this.endDate = endDate;

	}
	
	
	public Request(String duration, double cost, Date startDate, Date endDate) {
		super();
		this.duration = duration;
		this.cost = cost;
		this.startDate = startDate;
		this.endDate = endDate;
	}


	public Request(String duration, double cost) {
		super();
		this.duration = duration;
		this.cost = cost;
	}

	public Request() {
		super();
	}

	public int getRequestId() {
		return requestId;
	}
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
}
