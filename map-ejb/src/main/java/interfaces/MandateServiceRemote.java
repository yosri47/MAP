package interfaces;



import entities.Mandate;

import javax.ejb.Remote;
import java.util.Date;
import java.util.List;

@Remote
public interface MandateServiceRemote {
	public void persistMandate(Mandate m);
	public Mandate findMandate(int id);
	public boolean removeMandatet(int id);
	public Mandate mergeMandate(Mandate c);
	public void refreshMandate(Mandate c);
	public void flush();
	public void clear();
	public boolean contains(Mandate c);
	public List<Mandate> afficherMandates();
	public Mandate afficherMandate(int id);
	public List<Mandate> getMandateByResource(int idResource);
	public List<Mandate> getMandateByStartDate(Date startDate);
	public List<Mandate> getMandateByendDate(Date endDate);
	public List<Mandate> getMandateByFee(Double Fee);
	public List<Mandate> listAll();
	
	public List<Mandate> searchMandateByProject(int id);
	
}
