package interfaces;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import entities.Ressource;
import entities.Resume;
import entities.Skill;
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
	public Resume getResourceResume(String id);
	public List<Skill> getResourceSkills(String id);
	public Map<String, Long> rankResourcesBySkillNumber();
	

}
