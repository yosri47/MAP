package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Project implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int projectId;
	
	
	@Temporal(TemporalType.DATE)
	private Date startDate;
	@Temporal(TemporalType.DATE)
	private Date endDate;
	
	
	@Enumerated(EnumType.STRING)
	private ProjectType projectType;
	private double profitability;
	private String note;
	private String address;
	private int levioResources;
	private int otherResources;
	@ManyToOne
	@JoinColumn(name="owner")
	private Client owner;
	
	
	@OneToMany(mappedBy="project",fetch = FetchType.EAGER)
	@JsonIgnore
	private Set<Ressource> ressourcesList = new HashSet<>();
	@OneToMany(mappedBy="project",fetch = FetchType.EAGER)
	@JsonIgnore
	private Set<Mandate> mandates = new HashSet<>() ;
	@ManyToMany
	private Set<Skill> skillsRequired ;
	@OneToOne
	@JoinColumn(name="organigramId")
	private Organigram organigram;
	public Project() {
		super();
	}
	public Project(int projectId, Date startDate, Date endDate, ProjectType projectType, double profitability, String note,
			String address, int levioResources, int otherResources, Client owner, Set<Ressource> ressourcesList,
			Set<Mandate> mandates, Set<Skill> skillsRequired, Organigram organigram) {
		super();
		this.projectId = projectId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.projectType = projectType;
		this.profitability = profitability;
		this.note = note;
		this.address = address;
		this.levioResources = levioResources;
		this.otherResources = otherResources;
		this.owner = owner;
		this.ressourcesList = ressourcesList;
		this.mandates = mandates;
		this.skillsRequired = skillsRequired;
		this.organigram = organigram;
	}
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
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
	
	public ProjectType getProjectType() {
		return projectType;
	}
	public void setProjectType(ProjectType projectType) {
		this.projectType = projectType;
	}
	public double getProfitability() {
		return profitability;
	}
	public void setProfitability(double profitability) {
		this.profitability = profitability;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getLevioResources() {
		return levioResources;
	}
	public void setLevioResources(int levioResources) {
		this.levioResources = levioResources;
	}
	public int getOtherResources() {
		return otherResources;
	}
	public void setOtherResources(int otherResources) {
		this.otherResources = otherResources;
	}
	public Client getOwner() {
		return owner;
	}
	public void setOwner(Client owner) {
		this.owner = owner;
	}

	public Set<Ressource> getRessourcesList() {
		return ressourcesList;
	}
	public void setRessourcesList(Set<Ressource> ressourcesList) {
		this.ressourcesList = ressourcesList;
	}

	

	public Set<Mandate> getMandates() {
		return mandates;
	}
	public void setMandates(Set<Mandate> mandates) {
		this.mandates = mandates;
	}
	public Set<Skill> getSkillsRequired() {
		return skillsRequired;
	}
	public void setSkillsRequired(Set<Skill> skillsRequired) {
		this.skillsRequired = skillsRequired;
	}
	public Organigram getOrganigram() {
		return organigram;
	}
	public void setOrganigram(Organigram organigram) {
		this.organigram = organigram;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + levioResources;
		result = prime * result + ((mandates == null) ? 0 : mandates.hashCode());
		result = prime * result + ((note == null) ? 0 : note.hashCode());
		result = prime * result + ((organigram == null) ? 0 : organigram.hashCode());
		result = prime * result + otherResources;
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		long temp;
		temp = Double.doubleToLongBits(profitability);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + projectId;
		result = prime * result + ((projectType == null) ? 0 : projectType.hashCode());
		result = prime * result + ((ressourcesList == null) ? 0 : ressourcesList.hashCode());
		result = prime * result + ((skillsRequired == null) ? 0 : skillsRequired.hashCode());
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
		Project other = (Project) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (levioResources != other.levioResources)
			return false;
		if (mandates == null) {
			if (other.mandates != null)
				return false;
		} else if (!mandates.equals(other.mandates))
			return false;
		if (note == null) {
			if (other.note != null)
				return false;
		} else if (!note.equals(other.note))
			return false;
		if (organigram == null) {
			if (other.organigram != null)
				return false;
		} else if (!organigram.equals(other.organigram))
			return false;
		if (otherResources != other.otherResources)
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		if (Double.doubleToLongBits(profitability) != Double.doubleToLongBits(other.profitability))
			return false;
		if (projectId != other.projectId)
			return false;
		if (projectType == null) {
			if (other.projectType != null)
				return false;
		} else if (!projectType.equals(other.projectType))
			return false;
		if (ressourcesList == null) {
			if (other.ressourcesList != null)
				return false;
		} else if (!ressourcesList.equals(other.ressourcesList))
			return false;
		if (skillsRequired == null) {
			if (other.skillsRequired != null)
				return false;
		} else if (!skillsRequired.equals(other.skillsRequired))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}
	
	
	

}
