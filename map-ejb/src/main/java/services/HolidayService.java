package services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Holiday;
import interfaces.HolidayServiceRemote;

public class HolidayService implements HolidayServiceRemote {

	@PersistenceContext(unitName="pidev-ejb")
	EntityManager em;
	@Override
	public void persistHoliday(Holiday holiday) {
		em.persist(holiday);
	}
	@Override
	public Holiday findHoliday(int id) {
		return em.find(Holiday.class, id);
	}
	@Override
	public void removeHoliday(Holiday holiday) {
		em.remove(holiday);
	}
	@Override
	public Holiday mergeHoliday(Holiday holiday) {
		return em.merge(holiday);
	}
	@Override
	public void refreshHoliday(Holiday holiday) {
		em.refresh(holiday);
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
	public boolean contains(Holiday holiday) {
		return em.contains(holiday);
	}


}
