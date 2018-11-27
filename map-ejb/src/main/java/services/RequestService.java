package services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Admin;
import entities.Client;
import entities.Message;
import entities.Request;
import entities.Ressource;
import interfaces.RequestLocal;
import interfaces.RequestRemote;


@Stateless 

public class RequestService implements RequestLocal , RequestRemote {
	@PersistenceContext(unitName="pidev-ejb")
	EntityManager em;
	MessageService ms ;
	AdminService ad ;

	@Override
	public int ajouterRequest(Request req) {
	
		em.persist(req);
		return req.getRequestId();
	}

	@Override
	public int modifierRequest(Request req) {
		em.merge(req);

		return req.getRequestId();
	}

	@Override
	public int deleteRequest(Request req) {
		em.remove(em.contains(req) ? req : em.merge(req)); 
		return 1;
	}

	@Override
	public List<Request> getall() {
		TypedQuery<Request> query = em.createQuery("select r from Request r ",Request.class);
		return (List<Request>)query.getResultList();
	}

	@Override
	public Request getRequestbyid(int req) {
		TypedQuery<Request> query = em.createQuery("select r from Request r  where r.requestId ="+req,Request.class);
		if(	query.getResultList().isEmpty()){return null;}
		else{
		return (Request) query.getSingleResult();}
	}
	
	public Ressource getRessourcedispo() {
		TypedQuery<Ressource> query = em.createQuery("select r from Ressource r  where r.isOnLeave="+1+"ORDER BY r.rate",Ressource.class);
		if(	query.getResultList().isEmpty()){return null;}
		else{
		return (Ressource) query.getResultList().get(1);
	}
	}

	public Message Valider(Request m) {
		m.setStatus(true);
		Client c = m.getReqcl();
		Message mes = new Message();
		Admin admin = 	m.getReqadmin();

		mes.setAdminsend(admin);
		mes.setClrecu(c);
		mes.setObject("votre requête a eté traité avec succes");
		mes.setType("Request Réponse");
		
		Date date = new Date();
		mes.setDateSend(date);	
	if(	this.getRessourcedispo()==null){
		mes.setContent("Aucun Ressource Dispponible");}
	else { Ressource rous =this.getRessourcedispo();
		mes.setContent("Ressource Dispponible "+rous.getName()+" "+rous.getRate());
	}
			em.merge(m);
			return mes;
		}
	
	
}
