package interfaces;

import java.util.List;

import javax.ejb.Remote;

import entities.Client;
@Remote
public interface ClientServiceRemote {
	public void persistClient(Client c);
	public Client findClient(int id);
	public void removeClient(Client c);
	public Client mergeClient(Client c);
	public void refreshClient(Client c);
	public void flush();
	public void clear();
	public boolean contains(Client c);
	public List<Client> afficherClients();
	public Client afficherClient(int id );

}
