package interfaces;

import javax.ejb.Local;

import entities.Skill;
@Local
public interface SkillServiceRemote {
	public void persistSkill(Skill skill);
	public Skill findSkill(int id);
	public void removeSkill(Skill skill);
	public Skill mergeSkill(Skill skill);
	public void refreshSkill(Skill skill);
	public void flush();
	public void clear();
	public boolean contains(Skill skill);
}
