package services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entities.Holiday;
import entities.Skill;
import interfaces.SkillServiceLocal;
@Stateless
public class SkillService implements SkillServiceLocal {
	@PersistenceContext(unitName="pidev-ejb")
	EntityManager em;

	@Override
	public void persistSkill(Skill skill) {
		em.persist(skill);
	}
	@Override
	public Skill findSkill(int id) {
		return em.find(Skill.class, id);
	}
	@Override
	public void removeSkill(Skill skill) {
		em.remove(skill);
	}
	@Override
	public Skill mergeSkill(Skill skill) {
		return em.merge(skill);
	}
	@Override
	public boolean contains(Skill skill) {
		return em.contains(skill);
	}
	@Override
	public int removeSkillById(String id) {
		int idS = Integer.parseInt(id);
		Query query = em.createQuery("DELETE FROM Skill s WHERE s.skillId = :id");
		return query.setParameter("id", idS).executeUpdate();
	}
	@Override
	public long getCountByCategory(String category) {
		TypedQuery<Long> query = em.createQuery("SELECT count(s) FROM Skill s WHERE s.category LIKE CONCAT('%',:category,'%')",Long.class);
		return query.setParameter("category", category).getSingleResult();
	}
	@Override
	public long getCountByName(String name) {
		TypedQuery<Long> query = em.createQuery("SELECT count(s) FROM Skill s WHERE s.name LIKE CONCAT('%',:name,'%')",Long.class);
		return query.setParameter("name", name).getSingleResult();
	}
	@Override
	public List<Skill> searchByName(String name) {
		TypedQuery<Skill> query = em.createQuery("SELECT s FROM Skill s where s.name LIKE CONCAT('%',:name,'%')",Skill.class);
		return query.setParameter("name", name).getResultList();
	}
	@Override
	public List<Skill> searchByCategory(String category) {
		TypedQuery<Skill> query = em.createQuery("SELECT s FROM Skill s where s.category LIKE CONCAT('%',:category,'%')",Skill.class);
		return query.setParameter("category",category).getResultList();
	}
	@Override
	public List<Skill> listAll() {
		TypedQuery<Skill> query = em.createQuery("SELECT s FROM Skill s",Skill.class);
		return query.getResultList();
	}
	@Override
	public List<String> getCategories() {
		TypedQuery<String> query = em.createQuery("SELECT DISTINCT(s.category) from Skill s",String.class);
		return query.getResultList();
	}

}
