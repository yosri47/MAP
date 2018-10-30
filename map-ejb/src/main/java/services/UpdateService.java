package services;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import entities.AvailabilityType;
import entities.Break;
import entities.ContractType;
import entities.Holiday;
import entities.Ressource;
import interfaces.BreakServiceLocal;
import interfaces.HolidayServiceLocal;
import interfaces.RessourceServiceLocal;
import interfaces.UpdateServiceLocal;

@Stateless
public class UpdateService implements UpdateServiceLocal {
	@PersistenceContext(unitName = "pidev-ejb")
	EntityManager em;

	@Override
	public void updateResourcesAvailability() {
		
		/*
		List<Ressource> resources = rs.listAll();
		List<Holiday> holidays = hs.listAll();
		Date today = new Date();

		for (Holiday h : holidays) {
			// CHECK IF THERE S A HOLIDAY
			if (h.getStartDate().compareTo(today) * h.getEndDate().compareTo(today) >= 0) {
				for (Ressource res : resources) {
					if (res.isActive()) {
						res.setOnLeave(true);
						res.setAvailability(AvailabilityType.Unavailable);
						rs.mergeRessource(res);
					}
				}
			} else {
				for (Ressource res : resources) {
					// IF NOT A HOLIDAY BUT RESOURCE IS ON A GRANTED LEAVE
					if (res.isOnLeave() && res.getLeave().isGranted() && res.isActive()) {
						// IF he's still on his leave
						if (res.getLeave().getStartDate().compareTo(today)
								* res.getLeave().getEndDate().compareTo(today) >= 0) {
							res.setAvailability(AvailabilityType.Unavailable);
							rs.mergeRessource(res);
							// IF HIS LEAVE ENDED
						} else {
							res.setAvailability(AvailabilityType.Available);
							res.getLeave().setTaken(true);
							bs.mergeBreak(res.getLeave());
							res.setLeave(null);
							rs.mergeRessource(res);
						}
					}
				}
			}
		}
		*/

	}
}
