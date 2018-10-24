package services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
	public void refreshSkill(Skill skill) {
		em.refresh(skill);
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
	public boolean contains(Skill skill) {
		return em.contains(skill);
	}

}
