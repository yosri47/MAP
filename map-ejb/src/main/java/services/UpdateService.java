package services;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import entities.AvailabilityType;
import entities.Break;
import entities.Holiday;
import entities.Ressource;
import interfaces.UpdateServiceLocal;

@Stateless
public class UpdateService implements UpdateServiceLocal {
	@PersistenceContext(unitName = "pidev-ejb")
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
		RessourceService rs = new RessourceService();
		BreakService bs = new BreakService();
		HolidayService hs = new HolidayService();
		List<Ressource> resources = rs.listAll();
		List<Break> breaks = bs.listAll();
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
						}

						// IF HE S NOT ON A LEAVE ENDED
						else {
							//IF HE S IN A MANDATE WORKING
							if(res.getMandate().getStartDate().compareTo(today)
									* res.getMandate().getEndDate().compareTo(today) >= 0){
								res.setAvailability(AvailabilityType.Available);
								res.setOnLeave(false);
								res.getLeave().setTaken(true);
								bs.mergeBreak(res.getLeave());
								rs.mergeRessource(res);
							}else{
								
							}
						}
					}
					// IF HE S WORKING BUT HAS A LEAVE COMING UP
					else if (!res.isOnLeave() && res.getLeave() != null && res.isActive()) {
						if (res.getLeave().isGranted()) {
							res.setOnLeave(true);
							res.setAvailability(AvailabilityType.Unavailable);
							rs.mergeRessource(res);
						}
					}
				}
			}
		}
	}

}
