package interfaces;

import java.util.List;

import javax.ejb.Local;

import entities.Client;

@Local
public interface ClientServiceLocale {
	public void persistClient(Client c);
	public Client findClient(int id);
	public boolean removeClient(int id);
	public Client mergeClient(Client c);
	public void refreshClient(Client c);
	public void flush();
	public void clear();
	public boolean contains(Client c);
	public List<Client> afficherClients();
	public Client afficherClient(int id );
	
	public List<Client> searchClientByName(String name);
	
}
