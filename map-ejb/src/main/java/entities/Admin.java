package entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity
public class Admin  extends User  {

	@OneToMany(mappedBy = "reqadmin", cascade = CascadeType.ALL ,fetch=FetchType.EAGER )		
    private List<Request> RequestList;

	public Admin() {
		super();
	}

	public Admin(List<Request> requestList) {
		super();
		RequestList = requestList;
	}

	public List<Request> getRequestList() {
		return RequestList;
	}

	public void setRequestList(List<Request> requestList) {
		RequestList = requestList;
	}
	
	
	
}
