package services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entities.Ressource;
import entities.Resume;
import interfaces.ResumeServiceLocal;

public class ResumeService implements ResumeServiceLocal {

	@PersistenceContext(unitName="pidev-ejb")
	EntityManager em;
	@Override
	public void persistResume(Resume res) {
		em.persist(res);
	}
	@Override
	public Resume findResume(int id) {
		return em.find(Resume.class, id);
	}
	@Override
	public Resume mergeResume(Resume res) {
		return em.merge(res);
	}
	@Override
	public boolean contains(Resume res) {
		return em.contains(res);
	}
	@Override
	public Resume findResumeByResource(String resource) {
		TypedQuery<Resume> query = em.createQuery("SELECT r FROM Resume r WHERE r.owner = :resource",Resume.class);
		return query.setParameter("resource", resource).getSingleResult();
	}
	@Override
	public List<Resume> listAll() {
		TypedQuery<Resume> query = em.createQuery("SELECT r FROM Resume r",Resume.class);
		return query.getResultList();
	}
	@Override
	public int removeHolidayById(String id) {
		int idR = Integer.parseInt(id);
		Query query = em.createQuery("DELETE FROM Resume r WHERE r.resumeId = :id");
		return query.setParameter("id", idR).executeUpdate();
	}

}
