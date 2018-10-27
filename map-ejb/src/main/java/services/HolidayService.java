package services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entities.Holiday;
import interfaces.HolidayServiceLocal;
@Stateless
public class HolidayService implements HolidayServiceLocal {

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
	public Holiday mergeHoliday(Holiday holiday) {
		return em.merge(holiday);
	}
	@Override
	public boolean contains(Holiday holiday) {
		return em.contains(holiday);
	}
	@Override
	public List<Holiday> searchByName(String name) {
		
		TypedQuery<Holiday> query = em.createQuery("SELECT h FROM Holiday h where h.name LIKE CONCAT('%',:name,'%')",Holiday.class);
		return query.getResultList();
	}
	@Override
	public List<Holiday> searchByStartDate(String startDate) {
		
		Date date = new Date();
		TypedQuery<Holiday> query = em.createQuery("SELECT h FROM Holiday h WHERE h.startDate > :startDate OR h.startDate = :startDate ORDER BY h.startDate",Holiday.class);
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
	public List<Holiday> listAll()
	{
		Date today = new Date();
		TypedQuery<Holiday> query = em.createQuery("SELECT h FROM Holiday h WHERE h.endDate > :today",Holiday.class);
		return query.setParameter("today", today).getResultList();
	}
	public int removeHolidayById(String id)
	{
		int idH = Integer.parseInt(id);
		Query query = em.createQuery("DELETE FROM Holiday h WHERE h.holidayId = :id");
		return query.setParameter("id", idH).executeUpdate();
	}


}
