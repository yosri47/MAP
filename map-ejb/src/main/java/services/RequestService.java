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
import interfaces.RequestLocal;


@Stateless 

public class RequestService implements RequestLocal {
	@PersistenceContext(unitName="timesheetDS")
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
		return (Request) query.getSingleResult();
	}
	
}
