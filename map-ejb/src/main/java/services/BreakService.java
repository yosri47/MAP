package services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entities.Break;
import interfaces.BreakServiceLocal;
@Stateless
public class BreakService implements BreakServiceLocal {

	@PersistenceContext(unitName="pidev-ejb")
	EntityManager em;
	
	@Override
	public void persistBreak(Break Break) {
		em.persist(Break);
	}
	@Override
	public Break findBreak(int id) {
		return em.find(Break.class, id);
	}
	@Override
	public Break mergeBreak(Break Break) {
		return em.merge(Break);
	}
	@Override
	public boolean contains(Break Break) {
		return em.contains(Break);
	}
	@Override
	public List<Break> searchByStartDate(String startDate) {
		Date date = new Date();
		TypedQuery<Break> query = em.createQuery("SELECT l FROM Break l WHERE l.startDate > :startDate OR l.startDate = :startDate ORDER BY l.startDate",Break.class);
		try {
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			date = formatter.parse(startDate);
			query.setParameter("startDate", date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return query.getResultList();
	}
	@Override
	public List<Break> searchByEndDate(String endDate) {
		Date date = new Date();
		TypedQuery<Break> query = em.createQuery("SELECT l FROM Break l WHERE l.endDate = :endDate ORDER BY l.endDate",Break.class);
		try {
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			date = formatter.parse(endDate);
			query.setParameter("endDate", date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return query.getResultList();
	}
	@Override
	public List<Break> searchByGrant(String isGranted) {
		Date today = new Date();
		TypedQuery<Break> query = em.createQuery("SELECT l FROM Break l WHERE l.isGranted = :granted AND l.startDate > :date",Break.class);
		return query.setParameter("granted", isGranted).setParameter("date", today).getResultList();
	}
	@Override
	public List<Break> searchByResource(String ressource) {
		int idR = Integer.parseInt(ressource);
		TypedQuery<Break> query = em.createQuery("SELECT l FROM Break l WHERE l.resource = :res",Break.class);
		return query.setParameter("res", idR).getResultList();
	}
	@Override
	public List<Break> listAll() {
		Date today = new Date();
		TypedQuery<Break> query = em.createQuery("SELECT l FROM Break l WHERE l.endDate > :today OR l.endDate = :today",Break.class);
		return query.setParameter("today", today).getResultList();
	}
	@Override
	public int acceptBreak(String id) {
		int idL = Integer.parseInt(id);
		Query query = em.createQuery("UPDATE Break l SET l.isGranted = true WHERE l.BreakId = :id");
		return query.setParameter("id", idL).executeUpdate();
	}
	@Override
	public int deleteBreak(String id) {
		int idL = Integer.parseInt(id);
		Query query = em.createQuery("UPDATE Break l SET l.isTaken = true WHERE l.BreakId = :id");
		return query.setParameter("id", idL).executeUpdate();
	}
	

}
