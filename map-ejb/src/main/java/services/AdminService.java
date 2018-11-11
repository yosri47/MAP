package services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Admin;
import entities.Client;
import entities.Message;
import entities.Request;
import entities.Ressource;
import interfaces.AdminLocal;

public class AdminService  implements AdminLocal {
	@PersistenceContext(unitName="pidev-ejb")
	EntityManager em;
	
	MessageService ms ;
	RequestService rs;
	
	@Override
	public int Valider(Request m) {
		m.setStatus(true);
	Client c = 	ms.getClientbyid(m.getReqcl().getUserId());
	Message mes = new Message();
	Admin admin = 	this.getAdminbyid(1);

	mes.setAdminsend(admin);
	mes.setClrecu(c);
	mes.setObject("votre requête a eté traité avec succes");
	mes.setType("Request Réponse");
if(	rs.getRessourcedispo()==null){
	mes.setContent("Aucun Ressource Dispponible");}
else { Ressource rous =rs.getRessourcedispo();
	mes.setContent("Ressource Dispponible "+rous.getName()+" "+rous.getRate());
}
		em.merge(m);
ms.ajouterMessage(mes);
		return 1;
	}
	
	
	
	public Admin getAdminbyid(int m) {
		
		TypedQuery<Admin> query = em.createQuery("select e from Admin e where e.userId ="+m,Admin.class);
	if(	query.getResultList().isEmpty()){return null;}
	else{
		return (Admin)query.getSingleResult();
	}	}

}
