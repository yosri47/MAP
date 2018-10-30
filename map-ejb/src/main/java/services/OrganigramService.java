package services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Organigram;
import interfaces.OrganigramServiceLocale;

@Stateless
public class OrganigramService implements OrganigramServiceLocale {
	
	@PersistenceContext(unitName="pidev-ejb")
	EntityManager em;


	@Override
	public void persistOrganigram(Organigram o) {
		em.persist(o);
		
	}

	@Override
	public Organigram findOrganigram(int id) {
		return em.find(Organigram.class, id);
	}

	@Override
	public boolean removeOrganigram(int id) {
		Organigram Organigram =em.find(Organigram.class, id) ;
		if(em.find(Organigram.class, id) != null){
			em.remove(Organigram);
			return true ;
		}
		return false ;
	}

	@Override
	public Organigram mergeOrganigram(Organigram  organigram) {
		Organigram o = this.findOrganigram(organigram.getOrganigramId());
		o = organigram;
		return em.merge(o);
	}

	@Override
	public void refreshOrganigram(Organigram o) {
		em.refresh(o);
		
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
	public boolean contains(Organigram o) {
		return em.contains(o);
		}

	@Override
	public List<Organigram> afficherOrganigrams() {
		TypedQuery<Organigram> query = em.createQuery("Select c from Organigram c",Organigram.class);
		List<Organigram> ls = query.getResultList();
		return ls ;
	}

	@Override
	public Organigram afficherOrganigram(int id) {
		return em.find(Organigram.class, id);
	}

	@Override
	public List<Organigram> searchOrganigramByName(String name) {
		return em.createQuery("select c from Organigram c where "
				+ "c.name=:namee", Organigram.class)
				.setParameter("namee", name).getResultList();
	}

}
