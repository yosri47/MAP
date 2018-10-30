package services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import interfaces.UpdateServiceLocal;
@Stateless
public class UpdateService implements UpdateServiceLocal {
	@PersistenceContext(unitName="pidev-ejb")
	EntityManager em;


	@Override
	public void generateResources() {
		
		Query query = em.createQuery("");
	}

	@Override
	public void generateResumes() {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void generateSkills() {
		Query query = em.createQuery("");

	}

	@Override
	public void updateResourcesAvailability() {
		//Query 
		
	}

}
