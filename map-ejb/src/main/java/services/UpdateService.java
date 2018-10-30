package services;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import entities.AvailabilityType;
import entities.Break;
import entities.ContractType;
import entities.Holiday;
import entities.Ressource;
import interfaces.UpdateServiceLocal;

@Stateless
public class UpdateService implements UpdateServiceLocal {
	@PersistenceContext(unitName = "pidev-ejb")
	EntityManager em;

	@Override
	public void updateResourcesAvailability() {
		RessourceService rs = new RessourceService();
		BreakService bs = new BreakService();
		HolidayService hs = new HolidayService();
		List<Ressource> resources = rs.listAll();
		List<Holiday> holidays = hs.listAll();
		Date today = new Date();

		for (Holiday h : holidays) {
			// CHECK IF THERE S A HOLIDAY
			if (h.getStartDate().compareTo(today) * h.getEndDate().compareTo(today) >= 0) {
				for (Ressource res : resources) {
					if (res.isActive()) {
						res.setOnLeave(true);
						res.setAvailability(AvailabilityType.Unavailable);
						rs.mergeRessource(res);
					}
				}
			} else {
				for (Ressource res : resources) {
					// IF NOT A HOLIDAY BUT RESOURCE IS ON A GRANTED LEAVE
					if (res.isOnLeave() && res.getLeave().isGranted() && res.isActive()) {
						// IF he's still on his leave
						if (res.getLeave().getStartDate().compareTo(today)
								* res.getLeave().getEndDate().compareTo(today) >= 0) {
							res.setAvailability(AvailabilityType.Unavailable);
							rs.mergeRessource(res);
							// IF HIS LEAVE ENDED
						} else {
							res.setAvailability(AvailabilityType.Available);
							res.getLeave().setTaken(true);
							bs.mergeBreak(res.getLeave());
							res.setLeave(null);
							rs.mergeRessource(res);
						}
					}
					// CHECK IF HE S IN A MANDATE
					if (res.getMandate() != null) {
						res.setContractType(ContractType.Mandate);
						long diff = Math.abs(res.getMandate().getEndDate().getTime() - today.getTime());
						long numberOfDay = (long) diff / (1000 * 60 * 60 * 24);
						if(numberOfDay <= 14)
						{
							res.setAvailability(AvailabilityType.AvailableSoon);
						}else{
							res.setAvailability(AvailabilityType.Unavailable);
						}
						rs.mergeRessource(res);
					}
				}
			}
		}

	}
	@Override
	public void generateSkills() {
		String insert = "insert into Skill(category,name) values ('Websites, IT & Software','.NET'), ('Websites, IT & Software','ASP.NET'), ('Websites, IT & Software','Bitcoin'), ('Websites, IT & Software','C#'), ('Websites, IT & Software','Angular.js'), ('Websites, IT & Software','.C++'), ('Websites, IT & Software','Apache'), ('Websites, IT & Software','Business Intelligence'), ('Websites, IT & Software','PHP'), ('Websites, IT & Software','AJAX'), ('Websites, IT & Software','Javascript'), ('Websites, IT & Software','Java'), ('Websites, IT & Software','JEE'), ('Websites, IT & Software','C'), ('Websites, IT & Software','CSS'), ('Websites, IT & Software','Python'), ('Websites, IT & Software','Django'), ('Websites, IT & Software','Wordpress'), ('Websites, IT & Software','JQUERY/Prototype'), ('Websites, IT & Software','HTML'), ('Websites, IT & Software','iOS Development'), ('Websites, IT & Software','ionic'), ('Websites, IT & Software','Objective C'), ('Websites, IT & Software','Linux'), ('Websites, IT & Software','Ruby'), ('Websites, IT & Software','Oracle'), ('Websites, IT & Software','Prestashop'), ('Websites, IT & Software','MySQL'), ('Websites, IT & Software','React.js'), ('Websites, IT & Software','Architecture Logicielle'), ('Websites, IT & Software','Swift'), ('Websites, IT & Software','UNIX'), ('Websites, IT & Software','VoIP'), ('Websites, IT & Software','Vue.js'), ('Websites, IT & Software','UML'), ('Websites, IT & Software','Software test'), ('Websites, IT & Software','Web security'), ('Websites, IT & Software','Conception'), ('Websites, IT & Software','X86/x64 Assembler'), ('Websites, IT & Software','Visual Basic'), ('Websites, IT & Software','Windows Server'), ('Websites, IT & Software','Mobile App Development'), ('Websites, IT & Software','J2ME'), ('Websites, IT & Software','Android'), ('Websites, IT & Software','Apple'), ('Websites, IT & Software','Bootstrap'), ('Design, Media & Architecture','2D Animation'), ('Design, Media & Architecture','3D Animation'), ('Design, Media & Architecture','Adobe InDesign'), ('Design, Media & Architecture','After Effects'), ('Design, Media & Architecture','Audio Services'), ('Design, Media & Architecture','Autodesk Sketchbook Pro'), ('Design, Media & Architecture','Interior Design'), ('Design, Media & Architecture','Photoshop'), ('Design, Media & Architecture','Photo Editing'), ('Design, Media & Architecture','User Experience Design'), ('Design, Media & Architecture','Website Design'), ('Design, Media & Architecture','Video Editing'), ('Design, Media & Architecture','Illustrator'), ('Design, Media & Architecture','3D Modelling'), ('Design, Media & Architecture','Animation'), ('Design, Media & Architecture','Logo Design'), ('Design, Media & Architecture','Corporate Identity'), ('Design, Media & Architecture','Graphic Design'), ('Design, Media & Architecture','Word'), ('Design, Media & Architecture','Building Architecture'), ('Design, Media & Architecture','Video Services'), ('Engineering & Science','Aeronautical Engineering'), ('Engineering & Science','Arduino'), ('Engineering & Science','Chemical Engineering'), ('Engineering & Science','Electronics'), ('Engineering & Science','Mathematics'), ('Engineering & Science','Linear Programming'), ('Engineering & Science','Medical'), ('Engineering & Science','Circuit Design'), ('Engineering & Science','Engineering'), ('Engineering & Science','GPS'), ('Engineering & Science','Matlab'), ('Engineering & Science','Machine Learning'), ('Engineering & Science','Telecoms Engineering'), ('Engineering & Science','Wireless'), ('Engineering & Science','AutoCad'), ('Engineering & Science','Civil Engineering'), ('Engineering & Science','Cryptography'), ('Engineering & Science','Mechanical Engineering'), ('Engineering & Science','Microcontroller'), ('Engineering & Science','Algorithm'), ('Engineering & Science','Data Mining'), ('Engineering & Science','Mechatronics'), ('Engineering & Science','Neutral Networks'), ('Sales & Marketing','Branding'), ('Sales & Marketing','Leads'), ('Sales & Marketing','Financial Sales'), ('Sales & Marketing','Market Research'), ('Sales & Marketing','Social Media Marketing'), ('Sales & Marketing','Advertising'), ('Sales & Marketing','Facebook Marketing'), ('Sales & Marketing','Google Adsense'), ('Sales & Marketing','Marketing'), ('Sales & Marketing','Telemarketing'), ('Sales & Marketing','Google Adwords'), ('Sales & Marketing','Marketing Strategy'), ('Sales & Marketing','Sales'), ('Sales & Marketing','Search Engine Marketing'), ('Sales & Marketing','Brand marketing'), ('Business, Accounting, Human Resources & Legal','Account Management'), ('Business, Accounting, Human Resources & Legal','ERP'), ('Business, Accounting, Human Resources & Legal','Human Resources'), ('Business, Accounting, Human Resources & Legal','Project Management'), ('Business, Accounting, Human Resources & Legal','Public Relations'), ('Business, Accounting, Human Resources & Legal','Accounting'), ('Business, Accounting, Human Resources & Legal','Business Analysis'), ('Business, Accounting, Human Resources & Legal','Contracts'), ('Business, Accounting, Human Resources & Legal','Legal'), ('Business, Accounting, Human Resources & Legal','Visa / Immigration'), ('Business, Accounting, Human Resources & Legal','Attorney'), ('Business, Accounting, Human Resources & Legal','Legal Research'), ('Business, Accounting, Human Resources & Legal','Tax Management'), ('Business, Accounting, Human Resources & Legal','Business Plans'), ('Business, Accounting, Human Resources & Legal','Legal Writings'), ('Business, Accounting, Human Resources & Legal','Translation') ";
		Query query = em.createQuery(insert);
		query.getFirstResult();
	}

	@Override
	public void generateResources() {
		String insert ="insert into Ressource(availability, contractType,seniority,rate,userId)values ('Available','interMandate',1,20.5,7), ('Available','interMandate',4,47,8), ('Available','interMandate',2,14.75,9), ('Available','interMandate',1,15.5,10), ('Available','interMandate',7,31,11), ('Available','interMandate',1,28.5,12), ('Available','Administration',1,20.5,13);";
		Query query = em.createQuery(insert);
		query.getFirstResult();
	}

	@Override
	public void generateUsers() {
		String insert = "insert into User(name,emailAddress,password,confirmPassword,userType)values ('Habib Daou','Habib@esprit.tn','habib','habib','Resource'), ('Jack Black','Jack@Black.com','JackBlack13','JackBlack13','Resource'), ('Jane Kelly','Jane@Kelly.uk','AZERTY1990','AZERTY1990','Resource'), ('Yosri Bedoui','Yosri@esprit.tn','yosriB123','yosriB123','Resource'), ('Olfa Rajah','Olfa@esprit.tn','OlfaRajah1','OlfaRajah1','Resource'), ('Hamouda Zelfani','Hamouda@esprit.tn','Hamouda1','Hamouda1','Resource'), ('Jane Doe','Jane@Doe.com','Anonymous1','Anonymous1','Resource');";
		Query query = em.createQuery(insert);
		query.getFirstResult();
	}
	@Override
	public void generateResumes()
	{
		
	}
}
