package services;


import entities.*;
import interfaces.MandateServiceLocale;

import javax.ejb.Stateless;
import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Stateless
public class MandateService implements MandateServiceLocale {

	@PersistenceContext(unitName="pidev-ejb")
	EntityManager em;
	RessourceService rs;
	ProjectService ps;
	Mail_API mail;


	@Override
	public void persistMandate(Mandate m) throws MessagingException {
		m.setState(false);
		m.setFee((double) calculateFee(m));


		em.persist(m);
		mail.sendMail("olfa.rajah@esprit.tn","New Mandate","You were assigned to a new project following the acceptance of your profile by our client");

		}
	@Override
	public List<Skill> getResourceSkills(int idR ) {

		TypedQuery<Skill> query = em.createQuery("SELECT s "
						+ "FROM Resume r JOIN r.skills s "
						+ "WHERE r.owner.userId = :id"
				,Skill.class);
		return query.setParameter("id", idR).getResultList();
	}


	@Override
	public Long getNumberEmployeesInMandates() {
		String sql = "SELECT COUNT(r.id) FROM Ressource r WHERE r.availability='Unavailable'";
		Query q = em.createQuery(sql);
		Long count =(Long) q.getSingleResult();
		return count;
	}

	@Override
	public int numberOfResourcesToClient(int clientId) {
		Client c=em.find(Client.class, clientId);
		int numRes=0;
		String sql = "Select p from Project p where p.owner.userId="+c.getUserId();
		Query q = em.createQuery(sql);
		List<Project> projects  =(List<Project>) q.getResultList();
		for (Project p : projects){
			String sql2="Select Count(distinct m.resource) from Mandate m where m.project.id="+p.getProjectId();
			Query q2 = em.createQuery(sql2);
			Long count = (Long)q2.getSingleResult();
			numRes+=count;
		}
		return numRes;
	}

	@Override
	public float mandateEfficiency(int mandateId) {
		return 0;
	}

	@Override
	public Mandate findMandate(int projectId,int resourceId) {
		TypedQuery<Mandate> query = em.createQuery("SELECT  m FROM Mandate m where m.project.projectId= :projectId and m.resource.userId = :resourceId", Mandate.class);
		query.setParameter("projectId",projectId);
		query.setParameter("resourceId",resourceId);
		return query.getSingleResult();


	}



	@Override
	public int removeMandateByResource(int id) {
		Query query = em.createQuery("DELETE FROM Mandate m WHERE m.resource.userId = :id ");

		return query.setParameter("id",id).executeUpdate();

	}
	@Override
	public int removeMandateByProject(int id) {
		Query query = em.createQuery("DELETE FROM Mandate m WHERE m.project.projectId= :id");

		return query.setParameter("id",id).executeUpdate();

	}

	@Override
	public Mandate mergeMandate(Mandate c) {

		return em.merge(c);
	}

	@Override
	public List<Ressource> SearchResourceByProject(int projectId) {
		Project project;
		List<Skill> listSkillsRequired = this.getSkillsRequired(projectId);
		List<Ressource> listeRecourceNeeded = new ArrayList<>();
		TypedQuery<Project> query = em.createQuery("SELECT m FROM Project m where m.id=:pId", Project.class);
		query.setParameter("pId", projectId);


			project =query.getSingleResult();
		TypedQuery<Ressource> query1 = em.createQuery("SELECT m FROM Ressource m where m.availability='Available'", Ressource.class);

		List<Ressource> resources = query1.getResultList();

			resources.forEach(e -> {
				System.out.println(e.getName());
						List<Skill> resourceskills = this.getRSkills(e.getUserId());
						if (resourceskills.containsAll(listSkillsRequired)) {
							listeRecourceNeeded.add(e);
						}

					}
			);


			return listeRecourceNeeded;


	}

	@Override
	public List<Ressource> ResourceByProject(int projectId) {
		TypedQuery<Ressource> query = em.createQuery("SELECT r FROM Ressource r WHERE r.project.projectId = :a",Ressource.class);
		return query.setParameter("a", projectId).getResultList();
	}

	public List<Skill> getRSkills(int id) {

		TypedQuery<Skill> query = em.createQuery("SELECT s "
						+ "FROM Resume r JOIN r.skills s "
						+ "WHERE r.owner.userId = :id"
				,Skill.class);
		return query.setParameter("id", id).getResultList();
	}

	@Override
	public void refreshMandate(Mandate c) {
		em.refresh(c);
	}

	@Override
	public int archiveMandate() {
		Date today = new Date();
		today.getTime();
		Query query = em.createQuery("UPDATE Mandate m SET m.state=true WHERE m.endDate <= NOW()");

		return query.executeUpdate();


	}

	@Override
	public List<Mandate>  alertMandate( ) {
        TypedQuery<Mandate> query = em.createQuery("SELECT DISTINCT m FROM Mandate m WHERE DATEDIFF(m.endDate,DATE( NOW() ))BETWEEN 0 AND 40",Mandate.class);
        return query.getResultList();
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
		TypedQuery<Long> query = em.createQuery("select count(*) from Mandate m where "
				+ "m.project.projectId=:project", Long.class);
		return query.setParameter("project",idP).getSingleResult();
	}

	@Override
	public long getCountByResource(int idR) {
		TypedQuery<Long> query = em.createQuery("SELECT count(*) FROM Mandate m WHERE m.resource.userId = :id",Long.class);
		return query.setParameter("id",idR).getSingleResult();
	}

	@Override
	public List<Mandate> getArchivedMandate() {
		TypedQuery<Mandate> query = em.createQuery("SELECT DISTINCT m FROM Mandate m where m.state=true", Mandate.class);
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
	public float calculateFee(Mandate m) {
		Ressource r=em.find(Ressource.class, 2);
		System.out.println(r.getName());
		long diff = Math.abs(m.getEndDate().getTime() - m.getStartDate().getTime());
		long numberOfDay = (long)diff/(1000 * 60 * 60 * 24);

				float salaryPerDay= (float) (r.getRate()/30);
		return (float) (salaryPerDay*1.8*numberOfDay);
	}


	@Override
	public List<Mandate> getMandateByResource(int id) {
		return em.createQuery("select m from Mandate m where "
				+ "m.resource.userId=:resource", Mandate.class)
				.setParameter("resource", id).getResultList();
	}

	@Override
	public List<Mandate> searchMandateByProject(int id) {
		return em.createQuery("select m from Mandate m  where m.project.projectId =:project", Mandate.class)
				.setParameter("project", id).getResultList();
	}
	@Override
	public List<Mandate> getMandateByStartDate(String startDate) {
		Date date = new Date();
		TypedQuery<Mandate> query = em.createQuery("SELECT m FROM Mandate m WHERE m.startDate = :startDate ORDER BY m.startDate",Mandate.class);
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
		TypedQuery<Mandate> query = em.createQuery("SELECT m FROM Mandate m WHERE m.endDate = :endDate ORDER BY m.endDate",Mandate.class);
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
		TypedQuery<Mandate> query = em.createQuery("SELECT DISTINCT m FROM Mandate m WHERE m.state = false", Mandate.class);
		System.out.println(query.getResultList());
		return query.getResultList();
	}

@Override
	public List<Skill> getSkillsRequired(int id) {


		Query query = em.createQuery("SELECT p.skillsRequired  FROM Project p WHERE p.projectId = :id");

		 query.setParameter("id", id);
	List<Skill> skills= query.getResultList();
	return skills;
	}

}
