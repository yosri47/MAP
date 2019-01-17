package interfaces;



import entities.Mandate;
import entities.Ressource;
import entities.Skill;

import javax.ejb.Local;
import javax.mail.MessagingException;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Local
public interface MandateServiceLocale {
	public void persistMandate(Mandate m) throws MessagingException;
	public Mandate findMandate(int projectId,int resourceId);
	public int removeMandateByResource(int id);
	public int removeMandateByProject(int id);

	public Mandate mergeMandate(Mandate c);
	public List<Ressource> SearchResourceByProject(int projectId);
	public List<Ressource>ResourceByProject(int projectId);
	public List<Skill> getSkillsRequired(int id);
	public void refreshMandate(Mandate c);
	public int archiveMandate();
	public List<Mandate>  alertMandate( );
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
//	public Ressource getResourceBySkill(String name);
public List<Skill> getResourceSkills(int idR ) ;


//Dashboard
public Long getNumberEmployeesInMandates();
	public int numberOfResourcesToClient(int clientId);
	public float mandateEfficiency(int mandateId);



}
