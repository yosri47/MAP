package services;

import entities.Mandate;
import entities.Project;
import entities.Ressource;
import interfaces.MandateServiceLocale;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Stateless
public class MandateService implements MandateServiceLocale {

	@PersistenceContext(unitName="pidev-ejb")
	EntityManager em;


	@Override
	public void persistMandate(Mandate m) {
		m.setState(false);
		System.out.println((double) calculateFee(m));
		m.setFee((double) calculateFee(m));
		em.persist(m);

	}

	@Override
	public Mandate findMandate(int id) {
		return em.find(Mandate.class, id);

	}

	@Override
	public boolean removeMandatet(int id) {
		/*Query query = em.createQuery("DELETE FROM Mandate m WHERE m.resource.getUserId(): =id");

		return query.executeUpdate();*/
		Mandate mandate =em.find(Mandate.class, id) ;
		if(em.find(Mandate.class, id) != null){
			em.remove(mandate);
			return true ;
		}
		return false ;
	}

	@Override
	public Mandate mergeMandate(Mandate c) {

		return em.merge(c);
	}

	@Override
	public void refreshMandate(Mandate c) {
		em.refresh(c);
	}

	@Override
	public int archiveMandate() {
		Date today = new Date();
		today.getTime();
		Query query = em.createQuery("UPDATE Mandate m SET m.state=1 WHERE m.endDate <= NOW()");

		return query.executeUpdate();


	}

	@Override
	public Boolean alertMandate(Date endDate ) {
		Date today = new Date();
		//Date endDate = m.getEndDate();

		long diff = Math.abs(endDate.getTime() - today.getTime());
		long numberOfDay = (long) diff / (1000 * 60 * 60 * 24);
		if (numberOfDay == 40) {
			return true;
		} else {

			return false;
		}
	}

	@Override
	public Date getEndDate(int id) {
		TypedQuery<Date> query=em.createQuery("SELECT m.endDate from Mandate m WHERE m.resource= :id",Date.class);
		query.setParameter("id",id);
		System.out.println(query.getSingleResult());
		return query.getSingleResult();
	}

	@Override
	public long getCountByProject(int idP) {
		TypedQuery<Long> query = em.createQuery("SELECT count(*) FROM Mandate m WHERE m.project.getProjectId() = :id",Long.class);
		return query.setParameter("id",idP).getSingleResult();
	}

	@Override
	public long getCountByResource(int idR) {
		TypedQuery<Long> query = em.createQuery("SELECT count(*) FROM Mandate m WHERE m.resource.getUserId() = :id",Long.class);
		return query.setParameter("id",idR).getSingleResult();
	}

	@Override
	public List<Mandate> getArchivedMandate() {
		TypedQuery<Mandate> query = em.createQuery("SELECT DISTINCT m FROM Mandate m where m.state=1", Mandate.class);
		System.out.println(query.getResultList());
		return query.getResultList();
	}


	@Override
	public void endMandate() {
		Date today=new Date();



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
	public boolean contains(Mandate c) {

			return em.contains(c);

	}

	@Override
	public List<Mandate> afficherMandates() {
			TypedQuery<Mandate> query = em.createQuery("Select m from Mandate m",Mandate.class);
			List<Mandate> ls = query.getResultList();
			return ls ;
	}

	@Override
	public Mandate afficherMandate(int id) {
		return em.find(Mandate.class, id);
	}

	@Override
	public List<Mandate> searchMandateByProject(int id) {
		return em.createQuery("select m from Mandate m where "
				+ "m.projectId=:project", Mandate.class)
				.setParameter("project", id).getResultList();
	}

	@Override
	public float calculateFee(Mandate m) {
		Ressource r=em.find(Ressource.class, 1);
		System.out.println(r.getName());
		long diff = Math.abs(m.getEndDate().getTime() - m.getStartDate().getTime());
		long numberOfDay = (long)diff/(1000 * 60 * 60 * 24);

				Double salaryPerDay=r.getRate()/30;
		return (float) (salaryPerDay*1.8*numberOfDay);
	}


	@Override
	public List<Mandate> getMandateByResource(int idResource) {
		TypedQuery<Mandate> query = em.createQuery("SELECT m FROM Mandate m WHERE m.idResource = :idResource",Mandate.class);
		return query.setParameter("idResource", idResource).getResultList();
	}
	@Override
	public List<Mandate> getMandateByStartDate(String startDate) {
		Date date = new Date();
		TypedQuery<Mandate> query = em.createQuery("SELECT m FROM Mandate m WHERE m.startDate > :startDate OR m.startDate = :startDate ORDER BY m.startDate",Mandate.class);
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
	public List<Mandate> getMandateByendDate(String endDate) {
		Date date = new Date();
		TypedQuery<Mandate> query = em.createQuery("SELECT m FROM Mandate m WHERE m.endDate > :endDate OR m.endDate = :endDate ORDER BY m.endDate",Mandate.class);
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
	public List<Mandate> getMandateByFee(Double fee) {
		TypedQuery<Mandate> query = em.createQuery("SELECT m FROM Mandate m WHERE m.fee = :fee", Mandate.class);
		return query.setParameter("fee", fee).getResultList();	}
	@Override
	public List<Mandate> listAll()
	{
		TypedQuery<Mandate> query = em.createQuery("SELECT DISTINCT m FROM Mandate m", Mandate.class);
		System.out.println(query.getResultList());
		return query.getResultList();
	}

}
