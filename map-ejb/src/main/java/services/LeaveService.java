package services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entities.Holiday;
import entities.Leave;
import interfaces.LeaveServiceLocal;

public class LeaveService implements LeaveServiceLocal {

	@PersistenceContext(unitName="pidev-ejb")
	EntityManager em;
	
	@Override
	public void persistLeave(Leave leave) {
		em.persist(leave);
	}
	@Override
	public Leave findLeave(int id) {
		return em.find(Leave.class, id);
	}
	@Override
	public Leave mergeLeave(Leave leave) {
		return em.merge(leave);
	}
	@Override
	public boolean contains(Leave leave) {
		return em.contains(leave);
	}
	@Override
	public List<Leave> searchByStartDate(String startDate) {
		Date date = new Date();
		TypedQuery<Leave> query = em.createQuery("SELECT l FROM Leave l WHERE l.startDate > :startDate OR l.startDate = :startDate ORDER BY l.startDate",Leave.class);
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
	public List<Leave> searchByEndDate(String endDate) {
		Date date = new Date();
		TypedQuery<Leave> query = em.createQuery("SELECT l FROM Leave l WHERE l.endDate = :endDate ORDER BY l.startDate",Leave.class);
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
	public List<Leave> searchByGrant(String isGranted) {
		Date today = new Date();
		TypedQuery<Leave> query = em.createQuery("SELECT l FROM Leave l WHERE l.isGranted = :granted AND l.startDate > :date",Leave.class);
		return query.setParameter("granted", isGranted).setParameter("date", today).getResultList();
	}
	@Override
	public List<Leave> searchByResource(String ressource) {
		int idR = Integer.parseInt(ressource);
		TypedQuery<Leave> query = em.createQuery("SELECT l FROM Leave l WHERE l.resource = :res",Leave.class);
		return query.setParameter("res", idR).getResultList();
	}
	@Override
	public List<Leave> listAll() {
		Date today = new Date();
		TypedQuery<Leave> query = em.createQuery("SELECT l FROM Leave l WHERE l.endDate > :today OR l.endDate = :today",Leave.class);
		return query.setParameter("today", today).getResultList();
	}
	@Override
	public int acceptLeave(String id) {
		int idL = Integer.parseInt(id);
		Query query = em.createQuery("UPDATE Leave l SET l.isGranted = true WHERE l.leaveId = :id");
		return query.setParameter("id", idL).executeUpdate();
	}
	@Override
	public int deleteLeave(String id) {
		int idL = Integer.parseInt(id);
		Query query = em.createQuery("UPDATE Leave l SET l.taken = true WHERE l.leaveId = :id");
		return query.setParameter("id", idL).executeUpdate();
	}
	

}
