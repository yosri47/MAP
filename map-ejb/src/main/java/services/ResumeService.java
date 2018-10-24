package services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Resume;
import interfaces.ResumeServiceRemote;

public class ResumeService implements ResumeServiceRemote {

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
	public void removeResume(Resume res) {
		em.refresh(res);
	}
	@Override
	public Resume mergeResume(Resume res) {
		return em.merge(res);
	}
	@Override
	public void refreshResume(Resume res) {
		em.refresh(res);
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
	public boolean contains(Resume res) {
		return em.contains(res);
	}

}
