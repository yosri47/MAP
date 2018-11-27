package interfaces;

import java.util.List;

import javax.ejb.Remote;

import entities.Request;

@Remote
public interface RequestRemote {
	public int ajouterRequest(Request req);
	public int modifierRequest (Request req);
	public int deleteRequest (Request req);

	public List<Request> getall();
	public Request getRequestbyid(int req);
}
