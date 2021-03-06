package interfaces;

import java.util.List;

import javax.ejb.Remote;

import entities.Message;
import entities.Request;

@Remote
public interface RequestRemote {
	public int ajouterRequest(Request req);
	public int modifierRequest (Request req);
	public int deleteRequest (Request req);
	public List<Request> getRequestStatus() ;
	public List<Request> getRequestStatu();
	public List<Request> getall();
	public Request getRequestbyid(int req);
	public Message Valider (Request m);
	public List<Request> getallRequestsSend(int id);
	public int deleteReq(int req);
	public int removeRequest(int m);
}
