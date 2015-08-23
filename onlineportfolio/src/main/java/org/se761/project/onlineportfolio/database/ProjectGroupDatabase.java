package org.se761.project.onlineportfolio.database;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.se761.project.onlineportfolio.exception.DatabaseRetrievalException;
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
	public void addProjectGroup(ProjectGroup projGroup){
		openSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(projGroup);
		session.getTransaction().commit();
		session.close();
		closeSessionFactory();
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
	
}
