package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Client extends User implements Serializable{
	@Enumerated(EnumType.STRING)

	private ClientType clientType;
	
	
	@Enumerated(EnumType.STRING)

	private ClientCategory clientCategory;
	

	private String clientLogo;

	private String clientNote;

	private String clientAddress;
	
	private float lattitude;
	private float longitude;
	
	@OneToMany(mappedBy = "reqcl", cascade = CascadeType.ALL ,fetch=FetchType.EAGER )	

	private Set<Request>req ;
	
	@OneToMany(mappedBy = "clsend", cascade = CascadeType.ALL ,fetch=FetchType.EAGER )	
	private Set<Message>sends ;
   
	@OneToMany(mappedBy = "clrecu", cascade = CascadeType.ALL ,fetch=FetchType.EAGER )	
	private Set<Message>recu ;
	
    @JsonIgnore
	public Set<Request> getReq() {
		return req;
	}

	public void setReq(Set<Request> req) {
		this.req = req;
	}

	public Client(int userId, ClientType clientType, ClientCategory clientCategory, String clientLogo,
			String clientNote, String clientAddress) {
		super(userId);
		this.clientType = clientType;
		this.clientCategory = clientCategory;
		this.clientLogo = clientLogo;
		this.clientNote = clientNote;
		this.clientAddress = clientAddress;
		
	}
    @JsonIgnore
	public Set<Message> getSends() {
		return sends;
	}

	public void setSends(Set<Message> sends) {
		this.sends = sends;
	}
	@JsonIgnore
	public Set<Message> getRecu() {
		return recu;
	}

	public void setRecu(Set<Message> recu) {
		this.recu = recu;
	}

	public Client() {
		super();
	}
	
	public Client(int userId) {
		super(userId);
	}
	




	public Client(ClientType clientType, ClientCategory clientCategory, String clientLogo,
			String clientNote, String clientAddress) {
		super();
		this.clientType = clientType;
		this.clientCategory = clientCategory;
		this.clientLogo = clientLogo;
		this.clientNote = clientNote;
		this.clientAddress = clientAddress;
	}
	public ClientType getClientType() {
		return clientType;
	}
	public void setClientType(ClientType clientType) {
		this.clientType = clientType;
	}

	public ClientCategory getClientCategory() {
		return clientCategory;
	}
	public void setClientCategory(ClientCategory clientCategory) {
		this.clientCategory = clientCategory;
	}

	public String getClientLogo() {
		return clientLogo;
	}
	public void setClientLogo(String clientLogo) {
		this.clientLogo = clientLogo;
	}

	public String getClientNote() {
		return clientNote;
	}
	public void setClientNote(String clientNote) {
		this.clientNote = clientNote;
	}

	public String getClientAddress() {
		return clientAddress;
	}
	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((clientAddress == null) ? 0 : clientAddress.hashCode());
		result = prime * result + ((clientCategory == null) ? 0 : clientCategory.hashCode());
		result = prime * result + ((clientLogo == null) ? 0 : clientLogo.hashCode());
		result = prime * result + ((clientNote == null) ? 0 : clientNote.hashCode());
		result = prime * result + ((clientType == null) ? 0 : clientType.hashCode());
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
		Client other = (Client) obj;
		if (clientAddress == null) {
			if (other.clientAddress != null)
				return false;
		} else if (!clientAddress.equals(other.clientAddress))
			return false;
		if (clientCategory != other.clientCategory)
			return false;
		if (clientLogo == null) {
			if (other.clientLogo != null)
				return false;
		} else if (!clientLogo.equals(other.clientLogo))
			return false;
		if (clientNote == null) {
			if (other.clientNote != null)
				return false;
		} else if (!clientNote.equals(other.clientNote))
			return false;
		if (clientType != other.clientType)
			return false;
		return true;
	}

	public float getLattitude() {
		return lattitude;
	}

	public void setLattitude(float lattitude) {
		this.lattitude = lattitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public Client(int userId, ClientType clientType, ClientCategory clientCategory, String clientLogo,
			String clientNote, String clientAddress, float lattitude, float longitude) {
		super(userId);
		this.clientType = clientType;
		this.clientCategory = clientCategory;
		this.clientLogo = clientLogo;
		this.clientNote = clientNote;
		this.clientAddress = clientAddress;
		this.lattitude = lattitude;
		this.longitude = longitude;
	}
	
	
	

}
