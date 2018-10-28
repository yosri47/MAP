package interfaces;

import java.util.List;

import javax.ejb.Local;

import entities.Ressource;
@Local
public interface RessourceServiceLocal {
	public void persistRessource(Ressource res);
	public Ressource findRessource(int id);
	public void removeRessource(Ressource res);
	public Ressource mergeRessource(Ressource res);
	public boolean contains(Ressource res);
	public int removeResourceById(String id);
	public List<Ressource> listAll();
	public List<Ressource> getRessourceByActivity(String isActive);
	public List<Ressource> getRessourceByAvailability(String availability);
	public List<Ressource> getRessourceByRate(String rate);
	public List<Ressource> getRessourceBySeniority(String seniority);
	public List<Ressource> getRessourceByName(String name);
	

}
