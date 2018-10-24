package interfaces;

import javax.ejb.Local;

import entities.Holiday;
@Local
public interface HolidayServiceLocal {
	public void persistHoliday(Holiday holiday);
	public Holiday findHoliday(int id);
	public void removeHoliday(Holiday holiday);
	public Holiday mergeHoliday(Holiday holiday);
	public void refreshHoliday(Holiday holiday);
	public void flush();
	public void clear();
	public boolean contains(Holiday holiday);
}
