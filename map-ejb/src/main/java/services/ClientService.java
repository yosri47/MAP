package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Client;
import interfaces.ClientServiceLocale;
import interfaces.ClientServiceRemote;

@Stateless
public class ClientService implements ClientServiceLocale{

	@PersistenceContext(unitName="pidev-ejb")
	EntityManager em;
	
	@Override
	public void persistClient(Client client) {
		em.persist(client);
	}
	@Override
	public Client findClient(int id) {
		return em.find(Client.class, id);
	}
	@Override
	public boolean removeClient(int id) {
		Client client =em.find(Client.class, id) ;
		if(em.find(Client.class, id) != null){
			em.remove(client);
			return true ;
		}
		return false ;
	}
	@Override
	public Client mergeClient(Client client) {
		return em.merge(client);
	}
	@Override
	public void refreshClient(Client client) {
		em.refresh(client);
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
	public boolean contains(Client client) {
		return em.contains(client);
	}
	@Override
	public List<Client> afficherClients() {
		TypedQuery<Client> query = em.createQuery("Select c from Client c",Client.class);
		List<Client> ls = query.getResultList();
		return ls ;
	}
	@Override
	public Client afficherClient(int id) {
		
		return em.find(Client.class, id);
	}


}
