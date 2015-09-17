package org.se761.project.onlineportfolio.database;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.se761.project.onlineportfolio.exception.DatabaseRetrievalException;
import org.se761.project.onlineportfolio.exception.NotActiveException;
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
	 * Delete qualification from database
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
		
		//removing inactive quals
		for (int i = 0; i<quals.size(); i++){
			if (quals.get(i).isActive() == false){
				quals.remove(i);
			}
		}
		
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
		
		List<Qualification> quals = projectGroup.getQuals();
		//removing inactive quals
		for (int i = 0; i<quals.size(); i++){
			if (quals.get(i).isActive() == false){
				quals.remove(i);
			}
		}
		
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
		
		session.saveOrUpdate(qual);
		
		session.getTransaction().commit();
		session.close();
		closeSessionFactory();
		
		
		return qual;
		
	}
	
	
	
	
	
}
