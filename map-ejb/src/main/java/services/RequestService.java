package services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Request;
import entities.Ressource;
import interfaces.RequestLocal;


@Stateless 

public class RequestService implements RequestLocal {
	@PersistenceContext(unitName="pidev-ejb")
	EntityManager em;

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
		TypedQuery<Ressource> query = em.createQuery("select r from Ressource r  where r.isOnLeave="+1+" and rownum ="+1,Ressource.class);
		if(	query.getResultList().isEmpty()){return null;}
		else{
		return (Ressource) query.getSingleResult();
	}
	}	
	
	
}
