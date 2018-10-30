package interfaces;



import entities.Mandate;
import entities.Project;

import javax.ejb.Local;
import java.util.Date;
import java.util.List;

@Local
public interface MandateServiceLocale {
	public void persistMandate(Mandate m);
	public Mandate findMandate(int id);
	public boolean removeMandatet(int id);
	public Mandate mergeMandate(Mandate c);
	public void refreshMandate(Mandate c);
	public int archiveMandate();
	public Boolean alertMandate(Date endDate );
	public Date getEndDate(int id);
	public void endMandate( );
	public void flush();
	public void clear();
	public boolean contains(Mandate c);
	public List<Mandate> afficherMandates();
	public Mandate afficherMandate(int id);
	public List<Mandate> getMandateByResource(int idResource);
	public List<Mandate> getMandateByStartDate(String startDate);
	public List<Mandate> getMandateByendDate(String endDate);
	public long getCountByProject(int idP);
	public long getCountByResource(int idR);
	public List<Mandate>getArchivedMandate();
	public List<Mandate> getMandateByFee(Double fee);
	public List<Mandate> listAll();
	public List<Mandate> searchMandateByProject(int id);
	public float calculateFee(Mandate m);
	
}
