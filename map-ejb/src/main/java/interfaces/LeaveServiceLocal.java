package interfaces;

import javax.ejb.Local;

import entities.Leave;
@Local
public interface LeaveServiceLocal {
	public void persistLeave(Leave leave);
	public Leave findLeave(int id);
	public void removeLeave(Leave leave);
	public Leave mergeLeave(Leave leave);
	public void refreshLeave(Leave leave);
	public void flush();
	public void clear();
	public boolean contains(Leave leave);
}
