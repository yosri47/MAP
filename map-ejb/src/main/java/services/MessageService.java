package services;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entities.Message;
import interfaces.messageRemote;


@Stateless 
public class MessageService  implements messageRemote{
	
	@PersistenceContext(unitName="timesheetDS")
	EntityManager em;
	
	MessageService se ;
	
	@Override
	public int ajouterMessage(Message m) {
	


	em.persist(m);
		return m.getMessageId();
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
		return (Message)query.getSingleResult();
	}

public Boolean verif(String chaine){
	if(chaine.matches("[a-zA-Z]+")){
		return true ;	}
	else{
			return false ;	}

}
	
}
