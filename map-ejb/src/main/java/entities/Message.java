package entities;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Message implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int messageId;
	private String object;
	private String content;
	private String type;
   
	
	@ManyToOne
	@JoinColumn (name="clsend" ,referencedColumnName="userId" )
	private Client clsend;
	@ManyToOne
	@JoinColumn (name="clrecu" ,referencedColumnName="userId")
	private Client clrecu;


	public Message(String object, String content, String type, Client clsend, Client clrecu) {
		super();
		this.object = object;
		this.content = content;
		this.type = type;
		this.clsend = clsend;
		this.clrecu = clrecu;
	}

	public Client getClsend() {
		return clsend;
	}
	 
	public void setClsend(Client clsend) {
		this.clsend = clsend;
	}
	public Client getClrecu() {
		return clrecu;
	}
	public void setClrecu(Client clrecu) {
		this.clrecu = clrecu;
	}
	public Message() {
		super();
	}
	public Message(int messageId, String object, String content, String type) {
		super();
		this.messageId = messageId;
		this.object = object;
		this.content = content;
		this.type = type;

	}
	

	public Message(int messageId, String object, String type) {
		super();
		this.messageId = messageId;
		this.object = object;
		this.type = type;
	}
	
	public Message(String object, String type) {
		super();
		this.object = object;
		this.type = type;
	}
	public Message(int messageId) {
		super();
		this.messageId = messageId;
	}
	public int getMessageId() {
		return messageId;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	public String getObject() {
		return object;
	}
	public void setObject(String object) {
		this.object = object;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());

		result = prime * result + messageId;
		result = prime * result + ((object == null) ? 0 : object.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Message other = (Message) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		
		if (messageId != other.messageId)
			return false;
		if (object == null) {
			if (other.object != null)
				return false;
		} else if (!object.equals(other.object))
			return false;
		
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	
	
}
