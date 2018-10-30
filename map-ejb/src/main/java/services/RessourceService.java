package services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entities.Ressource;
import entities.Resume;
import entities.Skill;
import interfaces.RessourceServiceLocal;

@Stateless
public class RessourceService implements RessourceServiceLocal {
	@PersistenceContext(unitName = "pidev-ejb")
	EntityManager em;

	@Override
	public void persistRessource(Ressource res) {
		em.persist(res);
	}

	@Override
	public Ressource findRessource(int id) {
		return em.find(Ressource.class, id);
	}

	@Override
	public void removeRessource(Ressource res) {
		em.remove(res);
	}

	@Override
	public Ressource mergeRessource(Ressource res) {
		return em.merge(res);
	}

	@Override
	public boolean contains(Ressource res) {
		return em.contains(res);
	}
	@Override
	public int removeResourceById(String id) {
		int idS = Integer.parseInt(id);
		Query query = em.createQuery("UPDATE Ressource r SET r.isActive = 0");
		return query.setParameter("id", idS).executeUpdate();
	}
	@Override
	public List<Ressource> listAll()
	{
		TypedQuery<Ressource> query = em.createQuery("SELECT r FROM Ressource r",Ressource.class);
		return query.getResultList();
	}

	@Override
	public List<Ressource> getRessourceByActivity(String isActive) {
		int active;
		if(isActive.equals("true")) {active = 1;}
		else{active= 0;}
		TypedQuery<Ressource> query = em.createQuery("SELECT r FROM Ressource r WHERE r.isActive = :active",Ressource.class);
		return query.setParameter("active", active).getResultList();
	}

	@Override
	public List<Ressource> getRessourceByAvailability(String availability) {
		TypedQuery<Ressource> query = em.createQuery("SELECT r FROM Ressource r WHERE r.availability = :a",Ressource.class);
		return query.setParameter("a", availability).getResultList();
	}

	@Override
	public List<Ressource> getRessourceByRate(String rate) {
		Double r = Double.parseDouble(rate);
		TypedQuery<Ressource> query = em.createQuery("SELECT r FROM Ressource r WHERE r.rate = :rate",Ressource.class);
		return query.setParameter("rate", r).getResultList();
	}

	@Override
	public List<Ressource> getRessourceBySeniority(String seniority) {
		int sen = Integer.parseInt(seniority);
		TypedQuery<Ressource> query = em.createQuery("SELECT r FROM Ressource r WHERE r.seniority = :sen",Ressource.class);
		return query.setParameter("sen", sen).getResultList();
	}

	@Override
	public List<Ressource> getRessourceByName(String name) {
		TypedQuery<Ressource> query = em.createQuery("SELECT r FROM Ressource r WHERE r.name like CONCAT('%',:n,'%')",Ressource.class);
		return query.setParameter("n", name).getResultList();
	}

	@Override
	public Resume getResourceResume(String id) {
		int idR = Integer.parseInt(id);
		TypedQuery<Resume> query = em.createQuery("SELECT r FROM Resume r WHERE r.owner.userId = :id",Resume.class);
		return query.setParameter("id", idR).getSingleResult();
	}

	@Override
	public List<Skill> getResourceSkills(String id) {
		int idR = Integer.parseInt(id);
		TypedQuery<Skill> query = em.createQuery("SELECT s "
				+ "FROM Resume r JOIN r.skills s "
				+ "WHERE r.owner.userId = :id"
				,Skill.class);
		return query.setParameter("id", idR).getResultList();
	}
	@Override
	public List<Object[]> rankResourcesBySkillNumber()
	{
		
		TypedQuery<Object[]> q = em.createQuery(
			    "SELECT res.name,res.resume.skills" +
			    " FROM Ressource res"+
			    " ORDER BY res.name", Object[].class);

			return q.getResultList();
	}

}
