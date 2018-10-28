package entities;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Message implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int messageId;
	private String object;
	private String content;
	private String type;
	
	@OneToOne
	private User from;
	@OneToOne
	private User to;
	public Message() {
		super();
	}
	public Message(int messageId, String object, String content, String type, User from, User to) {
		super();
		this.messageId = messageId;
		this.object = object;
		this.content = content;
		this.type = type;
		this.from = from;
		this.to = to;
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
	public User getFrom() {
		return from;
	}
	public void setFrom(User from) {
		this.from = from;
	}
	public User getTo() {
		return to;
	}
	public void setTo(User to) {
		this.to = to;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((from == null) ? 0 : from.hashCode());
		result = prime * result + messageId;
		result = prime * result + ((object == null) ? 0 : object.hashCode());
		result = prime * result + ((to == null) ? 0 : to.hashCode());
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
		if (from == null) {
			if (other.from != null)
				return false;
		} else if (!from.equals(other.from))
			return false;
		if (messageId != other.messageId)
			return false;
		if (object == null) {
			if (other.object != null)
				return false;
		} else if (!object.equals(other.object))
			return false;
		if (to == null) {
			if (other.to != null)
				return false;
		} else if (!to.equals(other.to))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	
	
}
