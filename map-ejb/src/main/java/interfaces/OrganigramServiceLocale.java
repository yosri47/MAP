package interfaces;

import java.util.List;

import javax.ejb.Local;

import entities.Organigram;

@Local
public interface OrganigramServiceLocale {

	public void persistOrganigram(Organigram c);
	public Organigram findOrganigram(int id);
	public boolean removeOrganigram(int id);
	public Organigram mergeOrganigram(Organigram c);
	public void refreshOrganigram(Organigram c);
	public void flush();
	public void clear();
	public boolean contains(Organigram c);
	public List<Organigram> afficherOrganigrams();
	public Organigram afficherOrganigram(int id );
	
	public List<Organigram> searchOrganigramByName(String name);
	
}
