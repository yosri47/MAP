package entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Break implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int leaveId;
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	@Temporal(TemporalType.DATE)
	private Date endDate;
	@OneToOne
	private Ressource resource;
	private boolean isGranted;
	private boolean isTaken;

	public Break() {
		super();
	}

	public Break(int leaveId, Date startDate, Date endDate, Ressource resource, boolean isGranted,boolean isTaken) {
		super();
		this.leaveId = leaveId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.resource = resource;
		this.isGranted = isGranted;
		this.isTaken = isTaken;
	}

	


	public boolean isTaken() {
		return isTaken;
	}

	public void setTaken(boolean isTaken) {
		this.isTaken = isTaken;
	}

	public int getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(int leaveId) {
		this.leaveId = leaveId;
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

	public Ressource getResource() {
		return resource;
	}

	public void setResource(Ressource resource) {
		this.resource = resource;
	}

	public boolean isGranted() {
		return isGranted;
	}

	public void setGranted(boolean isGranted) {
		this.isGranted = isGranted;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + (isGranted ? 1231 : 1237);
		result = prime * result + leaveId;
		result = prime * result + ((resource == null) ? 0 : resource.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Break other = (Break) obj;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (isGranted != other.isGranted)
			return false;
		if (leaveId != other.leaveId)
			return false;
		if (resource == null) {
			if (other.resource != null)
				return false;
		} else if (!resource.equals(other.resource))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}
	
	
	
	

}
