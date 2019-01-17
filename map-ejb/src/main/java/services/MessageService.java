package services;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entities.Admin;
import entities.Client;
import entities.Message;
import entities.Ressource;
import interfaces.MessageLocal;
import interfaces.messageRemote;


@Stateless 
public class MessageService  implements messageRemote, MessageLocal{
	
	@PersistenceContext(unitName="pidev-ejb")
	EntityManager em;
	
	MessageService se ;
	ClientService cl ;
	@Override
	public int ajouterMessage(Message m) {
	

	em.persist(m);
		return 1;
	}
	@Override
	public int modifier(Message m) {

em.merge(m);

		return m.getMessageId();

	}
	@Override
	public List<Message> getall() 
	{
		TypedQuery<Message> query = em.createQuery("select e from Message e ",Message.class);
		return (List<Message>)query.getResultList();
	}

	@Override
	public List<Message> getMessagebytype(String m) {
		TypedQuery<Message> query = em.createQuery("select e from Message e where e.type ='"+m+"'",Message.class);
		return (List<Message>)query.getResultList();
	}
	@Override
	public List<Message> getMessagebyObjet(String m) {
		TypedQuery<Message> query = em.createQuery("select e from Message e where e.object ='"+m+"'",Message.class);
		return (List<Message>)query.getResultList();
	}
	@Override
	public int delete(Message ms) {

		em.remove(em.contains(ms) ? ms : em.merge(ms)); 
	return 1;
	}
	@Override
	public Message getMessagebyid(int m) {
	
		TypedQuery<Message> query = em.createQuery("select e from Message e where e.messageId ="+m,Message.class);
	if(	query.getResultList().isEmpty()){return null;}
	else{
		return (Message)query.getSingleResult();
	}	}

	
	
	public Client getClientbyid(int m) {
	
		TypedQuery<Client> query = em.createQuery("select e from Client e where e.userId ="+m,Client.class);
	if(	query.getResultList().isEmpty()){return null;}
	else{
		return (Client)query.getSingleResult();
	}	}
	
	public List<Message>getMessagebysend(int m) {
		TypedQuery<Message> query = em.createQuery("select e from  Message e where e.clsend.userId ="+m+"or e.rssend ="+m,Message.class);
		return (List<Message>)query.getResultList();
		}
	public List<Message>getMessagebyrecu(int m) {
		
		TypedQuery<Message> query = em.createQuery("select e from  Message e where e.clrecu.userId ="+m+"or e.rsrecu = "+m,Message.class);
	if(	query.getResultList().isEmpty()){return null;}
	else{
		return (List< Message >)query.getResultList();
	}	}
	
	public List<Client>getMessagebyClient(int m) {
		TypedQuery<Client> query = em.createQuery("select e.clsend from  Message e where e.clsend.userId ="+m,Client.class);
		return (List<Client>)query.getResultList();
		}
	
	public List<Message>getMessagebyAdm(int m,int idcl) {
		TypedQuery<Message> query = em.createQuery("select e from  Message e where e.from_user_id ="+m+"and e.to_user_id ="+idcl,Message.class);
		return (List<Message>)query.getResultList();
		}
	
	public int removemessage(int m) {

		Query   query = em.createQuery("DELETE FROM Message e  WHERE e.messageId = "+m);
		return  query.executeUpdate() ;
		}
	
	
public Boolean verif(String chaine){
	if(chaine.matches("[a-zA-Z]+")){
		return true ;	}
	else{
			return false ;	}

}
public Boolean verifnum(int num){
	String chaine =Integer.toString(num);
	if(chaine.matches("[0-9]+")){
		return true ;	}
	else{
			return false ;	}

}
@Override
public Admin getAdminbyid(int m) {
	TypedQuery<Admin> query = em.createQuery("select e from Admin e where e.userId ="+m,Admin.class);
	if(	query.getResultList().isEmpty()){return null;}
	else{
		return (Admin)query.getSingleResult();
	}	
}

public Ressource getressourcebyid(int m) {
	TypedQuery<Ressource> query = em.createQuery("select e from Ressource e where e.userId ="+m,Ressource.class);
	if(	query.getResultList().isEmpty()){return null;}
	else{
		return (Ressource)query.getSingleResult();
	}	
}
}
