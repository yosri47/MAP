package services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Client;
import entities.Project;
import interfaces.ProjectServiceLocal;

@Stateless
public class ProjectService implements ProjectServiceLocal {

	@PersistenceContext(unitName="SoaRevision-ejb")
	EntityManager em;
	
	@Override
	public void persistProject(Project project) {
		em.persist(project);
	}
	@Override
	public Project findProject(int id) {
		return em.find(Project.class, id);
	}
	@Override
	public boolean removeProject(int id) {
		Project project =em.find(Project.class, id) ;
		if(em.find(Project.class, id) != null){
			em.remove(project);
			return true ;
		}
		return false ;
	}
	@Override
	public Project mergeProject(Project project) {
		Project p = this.findProject(project.getProjectId());
		p = project ;
		return em.merge(p);
	}
	@Override
	public void refreshProject(Project project) {
		em.refresh(project);
	}
	@Override
	public void flush() {
		em.flush();
	}
	@Override
	public void clear() {
		em.clear();
	}
	@Override
	public boolean contains(Project project) {
		return em.contains(project);
	}
	@Override
	public List<Project> afficherProjects() {
		TypedQuery<Project> query = em.createQuery("Select p from Project p",Project.class);
		List<Project> ls = query.getResultList();
		return ls ;
	}
	@Override
	public Project afficherProject(int id) {
		return em.find(Project.class, id);
	}
	@Override
	public List<Project> searchProjectByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}


}
