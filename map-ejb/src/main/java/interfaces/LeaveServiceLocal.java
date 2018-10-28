package interfaces;

import java.util.List;

import javax.ejb.Local;

import entities.Leave;
@Local
public interface LeaveServiceLocal {
	public void persistLeave(Leave leave);
	public Leave findLeave(int id);
	public Leave mergeLeave(Leave leave);
	public boolean contains(Leave leave);
	public List<Leave> searchByStartDate(String startDate);
	public List<Leave> searchByEndDate(String endDate);
	public List<Leave> searchByGrant(String isGranted);
	public List<Leave> searchByResource(String ressource);
	public List<Leave> listAll();
	public int deleteLeave(String id);
	public int acceptLeave(String id);
}
