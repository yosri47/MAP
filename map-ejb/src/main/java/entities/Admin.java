package entities;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity
public class Admin  extends User  {

	@OneToMany(mappedBy = "reqadmin", cascade = CascadeType.ALL ,fetch=FetchType.EAGER)		
    private List<Request> RequestList;
	@OneToMany(mappedBy = "adminsend", cascade = CascadeType.ALL ,fetch=FetchType.EAGER )	
	private Set<Message>sends ;

    @JsonIgnore
	public Set<Message> getSends() {
		return sends;
	}

	public void setSends(Set<Message> sends) {
		this.sends = sends;
	}

	public Admin() {
		super();
	}

    @JsonIgnore

	public List<Request> getRequestList() {
		return RequestList;
	}

	public void setRequestList(List<Request> requestList) {
		RequestList = requestList;
	}
	
	
	
}
