package org.se761.project.onlineportfolio.database;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.p4p.backpocketdriver.driverlog.exception.DriverLogException;
import org.p4p.backpocketdriver.driverlog.model.UserDetails;
import org.se761.project.onlineportfolio.exception.DatabaseRetrievalException;
import org.se761.project.onlineportfolio.exception.NotActiveException;
import org.se761.project.onlineportfolio.model.Account;
import org.se761.project.onlineportfolio.model.AdminGroup;
import org.se761.project.onlineportfolio.model.Image;
import org.se761.project.onlineportfolio.model.ProjectGroup;
import org.se761.project.onlineportfolio.model.Qualification;


public class QualificationDatabase {

	private SessionFactory sessionFactory;
	private Session session;

	public QualificationDatabase(){

	}

	/**
	 * Opens the current session
	 */
	public void openSessionFactory(){
		Configuration c = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
				applySettings(c.getProperties());

		sessionFactory = c.buildSessionFactory(builder.build());
	}

	/**
	 * Closing the current session
	 */
	public void closeSessionFactory(){
		sessionFactory.close();

	}

	/**
	 * Getting a particular qualification from the database
	 */
	public Qualification getQual(int qualId){
		openSessionFactory();
		session = sessionFactory.openSession();
		Qualification qual = (Qualification) session.get(Qualification.class, qualId);

		if(qual == null){
			closeSessionFactory();
			throw new DatabaseRetrievalException("Qual with id " + qualId + " could not be found");
		}

		if(qual.isActive() == false){
			closeSessionFactory();
			throw new NotActiveException("Qual with id " + qualId + " is not active");
		}

		session.close();
		closeSessionFactory();
		return qual;
	}


	/**
	 * Gets all the quals from the database (for superuser access)
	 */
	public List<Qualification> getAllQuals(){
		openSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		String getAllQuery = "FROM Qualification q";
		Query query = session.createQuery(getAllQuery);

		List<Qualification> quals = query.list();

		if(quals.size() == 0){
			closeSessionFactory();
			throw new DatabaseRetrievalException("No quals in the database to display.");
		}

		session.close();
		closeSessionFactory();
		return quals;
	}

	/**
	 * Gets all the public quals from the database (for film strip)
	 */
	public List<Qualification> getAllPublicQuals(){
		openSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		String getAllQuery = "FROM Qualification q";
		Query query = session.createQuery(getAllQuery);

		List<Qualification> quals = query.list();
		List<Integer> indicies = new ArrayList<>(); 

		
		//Remove any confidential and inactive quals
		for (int i =0; i < quals.size(); i++){
			System.out.println(quals.get(i).getStatus());
			if ((quals.get(i).getStatus().equalsIgnoreCase("confidential")) 
					|| (quals.get(i).isActive() == false)) {
				indicies.add(i);
				System.out.println(quals.size());
			}
		}

		removeFromList(indicies, quals);
		
		//Check if list is empty
		if(quals.size() == 0){
			closeSessionFactory();
			throw new DatabaseRetrievalException("No quals in the database to display.");
		}

		session.close();
		closeSessionFactory();
		return quals;
	}
	
	private void removeFromList(List<Integer> indicies, List toRemove){
		
		int index, count = 0;
		
		for(int i = 0; i < indicies.size(); i++){
			index = indicies.get(i) - count;
			toRemove.remove(index);
			count++;
		}
	}

	/**
	 * Adding a qualification to the database
	 */
	public void addQual(Qualification qual){
		openSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(qual);
		session.getTransaction().commit();
		session.close();
		closeSessionFactory();
	}

	/**
	 * Archive qualification from database
	 */
	public Qualification deleteQual(int qualId){
		openSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		Qualification qual = (Qualification) session.get(Qualification.class, qualId);

		if(qual == null){
			closeSessionFactory();
			throw new DatabaseRetrievalException("Qual with id " + qualId + " could not be found.");
		}

		qual.setActive(false);
		session.saveOrUpdate(qual);
		session.getTransaction().commit();
		session.close();
		closeSessionFactory();
		return qual;
	}
	
	
	/**
	 * Delete qualification from database
	 */
	public Qualification deleteQualFromDB(int qualId){
		openSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		Qualification qual = (Qualification) session.get(Qualification.class, qualId);

		if(qual == null){
			closeSessionFactory();
			throw new DatabaseRetrievalException("Qual with id " + qualId + " could not be found.");
		}

		
		session.delete(qual);
		session.getTransaction().commit();
		session.close();
		closeSessionFactory();
		return qual;
	}
	


	/**
	 * Reactivate qualification from database
	 */
	public Qualification reactivateQual(int qualId){
		openSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		Qualification qual = (Qualification) session.get(Qualification.class, qualId);

		if(qual == null){
			closeSessionFactory();
			throw new DatabaseRetrievalException("Qual with id " + qualId + " could not be found so unable to reactivate");
		}

		qual.setActive(true);
		session.saveOrUpdate(qual);
		session.getTransaction().commit();
		session.close();
		closeSessionFactory();
		return qual;
	}

	/**
	 * Add a qualification against an admin group
	 */
	public Qualification addQualificationToAdminGroup(int adminGroupId, int qualId){
		openSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		AdminGroup adminGroup = (AdminGroup) session.get(AdminGroup.class, adminGroupId);

		if(adminGroup == null){
			closeSessionFactory();
			throw new DatabaseRetrievalException("Admin Group with id " + adminGroupId + " could not be found, so can't add qualification");
		}

		Qualification qual = (Qualification) session.get(Qualification.class, qualId);

		if(qual == null){
			closeSessionFactory();
			throw new DatabaseRetrievalException("Qual with id " + qualId + " could not be found");
		}

		adminGroup.getQuals().add(qual);
		qual.getAdminGroups().add(adminGroup);
		session.save(qual);
		session.save(adminGroup);
		session.getTransaction().commit();
		session.close();
		closeSessionFactory();
		return qual;

	}


	/**
	 * Add a qualification against an project group
	 */
	public Qualification addQualificationToProjectGroup(int projectGroupId, int qualId){
		openSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		ProjectGroup projectGroup = (ProjectGroup) session.get(ProjectGroup.class, projectGroupId);

		if(projectGroup == null){
			closeSessionFactory();
			throw new DatabaseRetrievalException("Project Group with id " + projectGroupId + " could not be found, so can't add qualification");
		}

		Qualification qual = (Qualification) session.get(Qualification.class, qualId);

		if(qual == null){
			closeSessionFactory();
			throw new DatabaseRetrievalException("Qual with id " + qualId + " could not be found");
		}

		projectGroup.getQuals().add(qual);
		qual.getProjGroups().add(projectGroup);
		session.save(qual);
		session.save(projectGroup);
		session.getTransaction().commit();
		session.close();
		closeSessionFactory();
		return qual;

	}

	/**
	 * Add a qualification against an account 
	 */
	public Qualification addQualificationToAccount(int accountId, int qualId){
		openSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		Account account = (Account) session.get(Account.class, accountId);

		if(account == null){
			closeSessionFactory();
			throw new DatabaseRetrievalException("Account with id " + accountId + " could not be found");
		}

		Qualification qual = (Qualification) session.get(Qualification.class, qualId);

		if(qual == null){
			closeSessionFactory();
			throw new DatabaseRetrievalException("Qualification with id " + qualId + " could not be found");
		}

		account.getAccountsQual().add(qual);
		qual.getAccountsQual().add(account);
		session.saveOrUpdate(qual);
		session.saveOrUpdate(account);
		session.getTransaction().commit();
		session.close();
		closeSessionFactory();
		return qual;
	}


	/**
	 * Get all qualifications associated with an admin group
	 */
	public List<Qualification> getAllQualificationsForAdminGroup(int adminGroupId){
		openSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction(); 

		AdminGroup adminGroup = (AdminGroup) session.get(AdminGroup.class, adminGroupId);

		if(adminGroup == null){
			closeSessionFactory();
			throw new DatabaseRetrievalException("Admin Group with id " + adminGroupId + " could not be found, so can't retrieve qualifications");
		}

		List<Qualification> quals = adminGroup.getQuals();
		List<Integer> indicies = new ArrayList<>();

		//removing inactive quals
		for(int i =0; i < quals.size(); i++){
			if(quals.get(i).isActive() == false){
				indicies.add(i);
			}
		}		
		
		removeFromList(indicies, quals);
		//removing inactive quals
//		for (int i = 0; i<quals.size(); i++){
//			if (quals.get(i).isActive() == false){
//				quals.remove(i);
//			}
//		}

		session.getTransaction().commit();
		session.close();
		closeSessionFactory();
		return quals;
	}

	/**
	 * Get all qualifications associated with an project group
	 */
	public List<Qualification> getAllQualificationsForProjectGroup(int projectGroupId){
		openSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction(); 

		ProjectGroup projectGroup = (ProjectGroup) session.get(ProjectGroup.class, projectGroupId);

		if(projectGroup == null){
			closeSessionFactory();
			throw new DatabaseRetrievalException("Project Group with id " + projectGroupId + " could not be found, so can't retrieve qualifications");
		}

		List<Qualification> quals = projectGroup.getQuals();;
		List<Integer> indicies = new ArrayList();

		//removing inactive quals
		for(int i =0; i < quals.size(); i++){
			if(quals.get(i).isActive() == false){
				indicies.add(i);
			}
		}		
		
		removeFromList(indicies, quals);
//		//removing inactive quals
//		for (int i = 0; i<quals.size(); i++){
//			if (quals.get(i).isActive() == false){
//				quals.remove(i);
//			}
//		}

		session.getTransaction().commit();
		session.close();
		closeSessionFactory();
		return quals;
	}


	/**
	 * Get all quals associated with an account
	 */
	public List<Qualification> getAllQualificationsForAccount(int accountId){
		openSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();

		Account account = (Account) session.get(Account.class, accountId);

		if(account == null){
			closeSessionFactory();
			throw new DatabaseRetrievalException("Account with id " + accountId + " could not be found, so can't retrieve qualifications");
		}
		List<Qualification> quals = account.getAccountsQual();
		List<Integer> indicies = new ArrayList();

		//removing inactive quals
		for(int i =0; i < quals.size(); i++){
			if(quals.get(i).isActive() == false){
				indicies.add(i);
			}
		}
		
		removeFromList(indicies, quals);
		
		session.getTransaction().commit();
		session.close();
		closeSessionFactory();
		return quals;
	}

	/**
	 * Edit a qualification
	 */
	public Qualification editQualification(Qualification qual){
		openSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction(); 

		Qualification qualification = (Qualification) session.get(Qualification.class, qual.getQualId());

		if(qualification == null){
			closeSessionFactory();
			throw new DatabaseRetrievalException("Unable to retrieve qual with id " +qual.getQualId());
		}
		
		qualification.setActive(qual.isActive());
		qualification.setAnonymous(qual.isAnonymous());
		qualification.setAnonymousName(qual.getAnonymousName());
		qualification.setChallengesFaced(qual.getChallengesFaced());
		qualification.setClientImage(qual.getClientImage());
		qualification.setClientName(qual.getClientName());
		qualification.setEmailButton(qual.getEmailButton());
		qualification.setIndustry(qual.getIndustry());
		qualification.setOutcomeStatement(qual.getOutcomeStatement());
		qualification.setProblemStatement(qual.getProblemStatement());
		qualification.setProjectImage(qual.getProjectImage());
		qualification.setProjectName(qual.getProjectName());
		qualification.setQualId(qual.getQualId());
		qualification.setRelevanceToClient(qual.getRelevanceToClient());
		qualification.setServiceLine(qual.getServiceLine());
		qualification.setSolutionStatement(qual.getSolutionStatement());
		qualification.setStatus(qual.getStatus());
		qualification.setSubtitle(qual.getSubtitle());
		qualification.setTags(qual.getTags());
		qualification.setWebsiteButton(qual.getWebsiteButton());


		session.update(qualification);
		session.getTransaction().commit();
		session.close();
		closeSessionFactory();


		return qualification;

	}

}
