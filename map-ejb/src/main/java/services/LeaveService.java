package services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Leave;
import interfaces.LeaveServiceRemote;

public class LeaveService implements LeaveServiceRemote {

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
	public void removeLeave(Leave leave) {
		em.remove(leave);
	}
	@Override
	public Leave mergeLeave(Leave leave) {
		return em.merge(leave);
	}
	@Override
	public void refreshLeave(Leave leave) {
		em.refresh(leave);
	}
	@Override
	public void flush() {
		em.flush();
	}
	@Override
	public void clear() {
		em.clear();
	}
	@Override
	public boolean contains(Leave leave) {
		return em.contains(leave);
	}
	

}
