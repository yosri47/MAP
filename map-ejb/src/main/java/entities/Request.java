package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.ws.rs.DefaultValue;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
	private Boolean Status;

    @ManyToOne
	@JoinColumn (name="reqadmin" ,referencedColumnName="userId" )
	private Admin reqadmin;


  	@ManyToOne
  	@JoinColumn (name="reqcl" ,referencedColumnName="userId" )
  	private Client reqcl;
    
    
    
    
	public Request(String duration, double cost, Date startDate, Date endDate, String typeressource, Admin reqadmin,
			Client reqcl) {
		super();
		this.duration = duration;
		this.cost = cost;
		this.startDate = startDate;
		this.endDate = endDate;
		this.typeressource = typeressource;
		this.reqadmin = reqadmin;
		this.reqcl = reqcl;
	}


	public Request(String duration, double cost, Date startDate, Date endDate, String typeressource, Boolean status) {
		super();
		this.duration = duration;
		this.cost = cost;
		this.startDate = startDate;
		this.endDate = endDate;
		this.typeressource = typeressource;
		Status = status;
	}


	public Request(String duration, double cost, Date startDate, Date endDate, String typeressource, Boolean status,
			Admin reqadmin, Client reqcl) {
		super();
		this.duration = duration;
		this.cost = cost;
		this.startDate = startDate;
		this.endDate = endDate;
		this.typeressource = typeressource;
		Status = status;
		this.reqadmin = reqadmin;
		this.reqcl = reqcl;
	}


	public Boolean getStatus() {
		return Status;
	}


	public void setStatus(Boolean status) {
		Status = status;
	}


	public Request(String duration, double cost, String typeressource, Admin reqadmin, Client reqcl) {
		super();
		this.duration = duration;
		this.cost = cost;
		this.typeressource = typeressource;
		this.reqadmin = reqadmin;
		this.reqcl = reqcl;
	}


	public Admin getReqadmin() {
		return reqadmin;
	}


	public void setReqadmin(Admin reqadmin) {
		this.reqadmin = reqadmin;
	}


	public Client getReqcl() {
		return reqcl;
	}


	public void setReqcl(Client reqcl) {
		this.reqcl = reqcl;
	}


	public Request(int requestId, String duration, double cost, Date startDate, Date endDate, String typeressource) {
		super();
		this.requestId = requestId;
		this.duration = duration;
		this.cost = cost;
		this.startDate = startDate;
		this.endDate = endDate;
		this.typeressource = typeressource;
	}


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
	
	public String getTyperessource() {
		return typeressource;
	}


	public void setTyperessource(String typeressource) {
		this.typeressource = typeressource;
	}
}
