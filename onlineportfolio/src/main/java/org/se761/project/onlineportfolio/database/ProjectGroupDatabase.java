package org.se761.project.onlineportfolio.database;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.se761.project.onlineportfolio.exception.DatabaseRetrievalException;
import org.se761.project.onlineportfolio.model.Account;
import org.se761.project.onlineportfolio.model.ProjectGroup;
import org.se761.project.onlineportfolio.model.Qualification;

public class ProjectGroupDatabase {
	private SessionFactory sessionFactory;
	private Session session;
	
	public ProjectGroupDatabase(){
		
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
	 * Adding a project group to the database
	 */
	public ProjectGroup addProjectGroup(ProjectGroup projGroup){
		openSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(projGroup);
		session.getTransaction().commit();
		session.close();
		closeSessionFactory();
		return projGroup;
	}
	
	/**
	 * Delete project group from database
	 */
	public ProjectGroup deleteProjGroup(int projGroupId){
		openSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		ProjectGroup projGroup = (ProjectGroup) session.get(ProjectGroup.class, projGroupId);
		
		if(projGroup == null){
			closeSessionFactory();
			throw new DatabaseRetrievalException("Project group with id " + projGroupId + " could not be found.");
		}
		
		session.delete(projGroup);
		session.getTransaction().commit();
		session.close();
		closeSessionFactory();
		return projGroup;
	}
	
	/**
	 * Get all project groups from the database
	 */
	public List<ProjectGroup> getAllProjectGroups(){
		openSessionFactory();
		session = sessionFactory.openSession();
		
		String getAllQuery = "FROM ProjectGroup p";
		Query query = session.createQuery(getAllQuery);

		List<ProjectGroup> projectGroups = query.list();
		
		if(projectGroups.size() == 0){
			closeSessionFactory();
			throw new DatabaseRetrievalException("No project groups in the database to display.");
		}
		
		session.close();
		closeSessionFactory();
		return projectGroups;
	}
	
	/**
	 * Get a project group
	 */
	public ProjectGroup getProjectGroup(int projGroupId){
		openSessionFactory();
		session = sessionFactory.openSession();
		ProjectGroup projGroup = (ProjectGroup) session.get(ProjectGroup.class, projGroupId);
		
		if(projGroup == null){
			closeSessionFactory();
			throw new DatabaseRetrievalException("Project group with id " + projGroupId + " could not be found");
		}
		
		session.close();
		closeSessionFactory();
		return projGroup;
	}
	

	/**
	 * Get a project groups associated with a particular account
	 */
	public List<ProjectGroup> getProjectGroupForAccount(int accountId){
		openSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		Account account = (Account) session.get(Account.class, accountId);
		
		if(account == null){
			closeSessionFactory();
			throw new DatabaseRetrievalException("Account with id " + accountId + " could not be found");
		}
		
		List<ProjectGroup> projectGroups = account.getProjGroups();
		session.getTransaction().commit();
		session.close();
		closeSessionFactory();
		return projectGroups;
		
	}
	
	/**
	 * Edit a project group
	 */
	public ProjectGroup editProjectGroupDetails(int projectGroupId, ProjectGroup newProjectGroup){
		openSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction(); 
		
		ProjectGroup projectGroup = (ProjectGroup) session.get(ProjectGroup.class, projectGroupId);
		
		if(projectGroup == null){
			closeSessionFactory();
			throw new DatabaseRetrievalException("Project Group with id " + projectGroupId + " could not be found");
		}
		
		projectGroup = newProjectGroup;
		session.update(projectGroup);
		session.getTransaction().commit();
		session.close();
		closeSessionFactory();
		
		
		return projectGroup;
		
	}
	

	
}
