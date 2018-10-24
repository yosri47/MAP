package interfaces;

import entities.Ressource;

public interface RessourceServiceRemote {
	public void persistRessource(Ressource res);
	public Ressource findRessource(int id);
	public void removeRessource(Ressource res);
	public Ressource mergeRessource(Ressource res);
	public void refreshRessource(Ressource res);
	public void flush();
	public void clear();
	public boolean contains(Ressource res);
	

}
