package interfaces;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;

import entities.Client;
import entities.Message;
import entities.Ressource;



@Remote
public interface messageRemote {
	public Boolean verif(String chaine);

		public int ajouterMessage(Message m);
	public int modifier (Message m);
	public int delete (Message m);

	public List<Message> getall();
	public Message getMessagebyid(int m);
	public List<Message> getMessagebytype(String m);
	public List<Message> getMessagebyObjet(String m);
	public Boolean verifnum(int num);
	public Client getClientbyid(int m);
	public List<Message>getMessagebyrecu(int m);
	public List<Message>getMessagebysend(int m) ;
	public List<Client>getMessagebyClient(int m);
	public Ressource getressourcebyid(int m);

}
