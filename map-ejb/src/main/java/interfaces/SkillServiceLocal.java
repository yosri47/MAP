package interfaces;

import java.util.List;

import javax.ejb.Local;

import entities.Skill;
@Local
public interface SkillServiceLocal {
	public void persistSkill(Skill skill);
	public Skill findSkill(int id);
	public void removeSkill(Skill skill);
	public Skill mergeSkill(Skill skill);
	public boolean contains(Skill skill);
	public int removeSkillById(String id);
	public long getCountByCategory(String category);
	public long getCountByName(int id);
	public List<Skill> searchByName(String name);
	public List<Skill> searchByCategory(String category);
	public List<Skill> listAll();
	public List<String> getCategories();

}
