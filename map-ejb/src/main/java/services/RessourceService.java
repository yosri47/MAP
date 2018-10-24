package services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Ressource;
import interfaces.RessourceServiceRemote;
@Stateless
public class RessourceService implements RessourceServiceRemote {
	@PersistenceContext(unitName="pidev-ejb")
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
	public void refreshRessource(Ressource res) {
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
	public boolean contains(Ressource res) {
		return em.contains(res);
	}

}
