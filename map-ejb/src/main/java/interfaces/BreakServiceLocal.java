package interfaces;

import java.util.List;

import javax.ejb.Local;

import entities.Break;
@Local
public interface BreakServiceLocal {
	public void persistBreak(Break Break);
	public Break findBreak(int id);
	public Break mergeBreak(Break Break);
	public boolean contains(Break Break);
	public List<Break> searchByStartDate(String startDate);
	public List<Break> searchByEndDate(String endDate);
	public List<Break> searchByGrant(String isGranted);
	public List<Break> searchByResource(String ressource);
	public List<Break> listAll();
	public int deleteBreak(String id);
	public int acceptBreak(String id);
}
