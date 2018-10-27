package entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class Client extends User implements Serializable{
	@Enumerated(EnumType.STRING)
	private ClientType clientType;
	
	@Enumerated(EnumType.STRING)
	private ClientCategory clientCategory;
	
	private String clientLogo;
	private String clientNote;
	private String clientAddress;
	public Client() {
		super();
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
	
	
	

}
