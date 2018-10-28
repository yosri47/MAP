package interfaces;

import java.util.List;

import javax.ejb.Local;


import entities.Project;
@Local
public interface ProjectServiceLocal {
	public void persistProject(Project project);
	public Project findProject(int id);
	public boolean removeProject(int id);
	public Project mergeProject(Project project);
	public void refreshProject(Project project);
	public void flush();
	public void clear();
	public boolean contains(Project project);
	public List<Project> afficherProjects();
	public Project afficherProject(int id);
	
	public List<Project> searchProjectByName(String name);
}
