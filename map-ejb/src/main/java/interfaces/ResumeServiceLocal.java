package interfaces;

import java.util.List;

import javax.ejb.Local;

import entities.Resume;
@Local
public interface ResumeServiceLocal {
	public void persistResume(Resume res);
	public Resume findResume(int id);
	public Resume mergeResume(Resume res);
	public boolean contains(Resume res);
	public Resume findResumeByResource(String resource);
	public List<Resume> listAll();
	public int removeHolidayById(String id);

}
