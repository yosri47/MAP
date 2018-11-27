package interfaces;

import java.util.List;

import javax.ejb.Local;

import entities.Message;
import entities.Request;



@Local

public interface RequestLocal {
	public int ajouterRequest(Request req);
	public int modifierRequest (Request req);
	public int deleteRequest (Request req);

	public List<Request> getall();
	public Request getRequestbyid(int req);
	public Message Valider (Request m);


}
