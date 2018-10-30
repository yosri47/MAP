package entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Organigram implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int organigramId;
	
	private String managementLevel;
	
	private String programName;
	
	private String accountManager;
	
	private String assignementManager;
	
	@OneToOne(mappedBy="organigram")
	private Project project;

	public Organigram() {
		super();
	}

	public Organigram(int organigramId, String managementLevel, String programName, String accountManager,
			String assignementManager, Project project) {
		super();
		this.organigramId = organigramId;
		this.managementLevel = managementLevel;
		this.programName = programName;
		this.accountManager = accountManager;
		this.assignementManager = assignementManager;
		this.project = project;
	}

	public int getOrganigramId() {
		return organigramId;
	}

	public void setOrganigramId(int organigramId) {
		this.organigramId = organigramId;
	}

	public String getManagementLevel() {
		return managementLevel;
	}

	public void setManagementLevel(String managementLevel) {
		this.managementLevel = managementLevel;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public String getAccountManager() {
		return accountManager;
	}

	public void setAccountManager(String accountManager) {
		this.accountManager = accountManager;
	}

	public String getAssignementManager() {
		return assignementManager;
	}

	public void setAssignementManager(String assignementManager) {
		this.assignementManager = assignementManager;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountManager == null) ? 0 : accountManager.hashCode());
		result = prime * result + ((assignementManager == null) ? 0 : assignementManager.hashCode());
		result = prime * result + ((managementLevel == null) ? 0 : managementLevel.hashCode());
		result = prime * result + organigramId;
		result = prime * result + ((programName == null) ? 0 : programName.hashCode());
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
		Organigram other = (Organigram) obj;
		if (accountManager == null) {
			if (other.accountManager != null)
				return false;
		} else if (!accountManager.equals(other.accountManager))
			return false;
		if (assignementManager == null) {
			if (other.assignementManager != null)
				return false;
		} else if (!assignementManager.equals(other.assignementManager))
			return false;
		if (managementLevel == null) {
			if (other.managementLevel != null)
				return false;
		} else if (!managementLevel.equals(other.managementLevel))
			return false;
		if (organigramId != other.organigramId)
			return false;
		if (programName == null) {
			if (other.programName != null)
				return false;
		} else if (!programName.equals(other.programName))
			return false;
		return true;
	}
	

}
