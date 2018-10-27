package interfaces;

import java.util.List;

import javax.ejb.Local;

import entities.Holiday;
@Local
public interface HolidayServiceLocal {
	public void persistHoliday(Holiday holiday);
	public Holiday findHoliday(int id);
	public Holiday mergeHoliday(Holiday holiday);
	public boolean contains(Holiday holiday);
	public List<Holiday> searchByName(String name);
	public List<Holiday> searchByStartDate(String startDate);
	public List<Holiday> listAll();
	public int removeHolidayById(String id);
}
