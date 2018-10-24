package interfaces;

import entities.Resume;

public interface ResumeServiceRemote {
	public void persistResume(Resume res);
	public Resume findResume(int id);
	public void removeResume(Resume res);
	public Resume mergeResume(Resume res);
	public void refreshResume(Resume res);
	public void flush();
	public void clear();
	public boolean contains(Resume res);

}
