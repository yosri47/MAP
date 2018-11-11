package entities;

import java.io.Serializable;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;

import java.util.HashSet;
import java.util.Set;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;


import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

import javax.persistence.ManyToOne;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Ressource extends User implements Serializable{
	private String photo;
	@Enumerated(EnumType.STRING)
	private AvailabilityType availability;
	
	private String sector;
	private int seniority;
	private String note;
	private double rate;
	@Enumerated(EnumType.STRING)
	private ContractType contractType;
	@Column(columnDefinition= "boolean default false")
	private boolean isOnLeave;
	@Column(columnDefinition = "boolean default true")
	private boolean isActive;
	@OneToOne
	@JoinColumn(name="leaveId")
	private Break leave;
	@OneToOne
	@JoinColumn(name="resumeId")
	@JsonIgnore
	private Resume resume;

	@OneToMany(mappedBy = "rssend", cascade = CascadeType.ALL ,fetch=FetchType.EAGER )	
	private Set<Message>rssends ;
   
	@OneToMany(mappedBy = "rsrecu", cascade = CascadeType.ALL ,fetch=FetchType.EAGER )	
	private Set<Message>recu ;
	
	@OneToMany(mappedBy = "resource",fetch= FetchType.EAGER)
	private Set<Mandate> mandates = new HashSet<>();
	
	
	@ManyToOne
	@JoinColumn(name="projectId")
	private Project project;


	
	public Ressource() {
		super();
	}

	


	public Ressource(int userId) {
		super(userId);
	}
	


	public String getPhoto() {
		return photo;
	}


	public void setPhoto(String photo) {
		this.photo = photo;
	}


	public AvailabilityType getAvailability() {
		return availability;
	}


	public void setAvailability(AvailabilityType availability) {
		this.availability = availability;
	}


	public String getSector() {
		return sector;
	}


	public void setSector(String sector) {
		this.sector = sector;
	}


	public int getSeniority() {
		return seniority;
	}


	public void setSeniority(int seniority) {
		this.seniority = seniority;
	}


	public String getNote() {
		return note;
	}


	public void setNote(String note) {
		this.note = note;
	}


	public double getRate() {
		return rate;
	}


	public void setRate(double rate) {
		this.rate = rate;
	}


	public ContractType getContractType() {
		return contractType;
	}


	public void setContractType(ContractType contractType) {
		this.contractType = contractType;
	}


	public boolean isOnLeave() {
		return isOnLeave;
	}


	public void setOnLeave(boolean isOnLeave) {
		this.isOnLeave = isOnLeave;
	}


	public boolean isActive() {
		return isActive;
	}


	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}


	public Break getLeave() {
		return leave;
	}


	public void setLeave(Break leave) {
		this.leave = leave;
	}


	public Resume getResume() {
		return resume;
	}


	public void setResume(Resume resume) {
		this.resume = resume;
	}



	public Project getProject() {
		return project;
	}


	public void setProject(Project project) {
		this.project = project;
	}


	public Ressource(String photo, AvailabilityType availability, String sector, int seniority, String note,
			double rate, ContractType contractType, boolean isOnLeave, boolean isActive, Break leave, Resume resume,
			Set<Mandate> mandates, Project project) {
		super();
		this.photo = photo;
		this.availability = availability;
		this.sector = sector;
		this.seniority = seniority;
		this.note = note;
		this.rate = rate;
		this.contractType = contractType;
		this.isOnLeave = isOnLeave;
		this.isActive = isActive;
		this.leave = leave;
		this.resume = resume;
		this.project = project;
	}


	


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((availability == null) ? 0 : availability.hashCode());
		result = prime * result + ((contractType == null) ? 0 : contractType.hashCode());
		result = prime * result + (isActive ? 1231 : 1237);
		result = prime * result + (isOnLeave ? 1231 : 1237);
		result = prime * result + ((leave == null) ? 0 : leave.hashCode());
		result = prime * result + ((note == null) ? 0 : note.hashCode());
		result = prime * result + ((photo == null) ? 0 : photo.hashCode());
		result = prime * result + ((project == null) ? 0 : project.hashCode());
		long temp;
		temp = Double.doubleToLongBits(rate);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((resume == null) ? 0 : resume.hashCode());
		result = prime * result + ((sector == null) ? 0 : sector.hashCode());
		result = prime * result + seniority;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ressource other = (Ressource) obj;
		if (availability != other.availability)
			return false;
		if (contractType == null) {
			if (other.contractType != null)
				return false;
		} else if (!contractType.equals(other.contractType))
			return false;
		if (isActive != other.isActive)
			return false;
		if (isOnLeave != other.isOnLeave)
			return false;
		if (leave == null) {
			if (other.leave != null)
				return false;
		} else if (!leave.equals(other.leave))
			return false;
		if (note == null) {
			if (other.note != null)
				return false;
		} else if (!note.equals(other.note))
			return false;
		if (photo == null) {
			if (other.photo != null)
				return false;
		} else if (!photo.equals(other.photo))
			return false;
		if (project == null) {
			if (other.project != null)
				return false;
		} else if (!project.equals(other.project))
			return false;
		if (Double.doubleToLongBits(rate) != Double.doubleToLongBits(other.rate))
			return false;
		if (resume == null) {
			if (other.resume != null)
				return false;
		} else if (!resume.equals(other.resume))
			return false;
		if (sector == null) {
			if (other.sector != null)
				return false;
		} else if (!sector.equals(other.sector))
			return false;
		if (seniority != other.seniority)
			return false;
		return true;
	}
	
	
	
	
	
	
}