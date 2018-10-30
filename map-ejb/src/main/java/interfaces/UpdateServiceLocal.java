package interfaces;

import javax.ejb.Local;

@Local
public interface UpdateServiceLocal {
	//Verifier le status des resources, en congé ou non, en vacance ou non TODO
	public void updateResourcesAvailability();
	//Generer des données dans la base de donnée TODO
	public void generateSkills();
	public void generateResources();
	public void generateUsers();
	public void generateResumes();

}
