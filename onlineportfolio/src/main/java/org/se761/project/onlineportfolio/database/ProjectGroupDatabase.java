package org.se761.project.onlineportfolio.database;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.se761.project.onlineportfolio.exception.DatabaseRetrievalException;
import org.se761.project.onlineportfolio.exception.NotActiveException;
import org.se761.project.onlineportfolio.model.Account;
import org.se761.project.onlineportfolio.model.AdminGroup;
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

		projGroup.setActive(false);
		session.saveOrUpdate(projGroup);
		session.getTransaction().commit();
		session.close();
		closeSessionFactory();
		return projGroup;
	}

	/**
	 * Reactivate project group from database
	 */
	public ProjectGroup reactivateProjGroup(int projGroupId){
		openSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		ProjectGroup projGroup = (ProjectGroup) session.get(ProjectGroup.class, projGroupId);

		if(projGroup == null){
			closeSessionFactory();
			throw new DatabaseRetrievalException("Project group with id " + projGroupId + " could not be found so unable to reactivate project group.");
		}

		projGroup.setActive(true);
		session.saveOrUpdate(projGroup);
		session.getTransaction().commit();
		session.close();
		closeSessionFactory();
		return projGroup;
	}

	/**
	 * Get all project groups from the database (for superuser access)
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

		if(projGroup.isActive() == false){
			closeSessionFactory();
			throw new NotActiveException("The Project Group with id " + projGroupId +"  is not active");
		}

		session.close();
		closeSessionFactory();
		return projGroup;
	}


	/**
	 * Get all project groups associated with a particular account
	 */
	public List<ProjectGroup> getProjectGroupForAccount(int accountId){
		openSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		List<ProjectGroup> tempList = new ArrayList<ProjectGroup>();
		Account account = (Account) session.get(Account.class, accountId);

		if(account == null){
			closeSessionFactory();
			throw new DatabaseRetrievalException("Account with id " + accountId + " could not be found");
		}

		List<ProjectGroup> projectGroups = account.getProjGroups();
		tempList = projectGroups;

		//removing inactive project groups
		for (int i = 0; i<projectGroups.size(); i++){
			if (projectGroups.get(i).isActive() == false){
				tempList.remove(i);
			}
		}
		session.getTransaction().commit();
		session.close();
		closeSessionFactory();
		return tempList;

	}

	/**
	 * Get all project groups associated with an admin group
	 */
	public List<ProjectGroup> getProjectGroupForAdmin(int adminGroupId){
		openSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		

		AdminGroup adminGroup = (AdminGroup) session.get(AdminGroup.class, adminGroupId);

		if(adminGroup == null){
			closeSessionFactory();
			throw new DatabaseRetrievalException("Admin group with id " + adminGroupId + " could not be found");
		}

		List<ProjectGroup> projectGroups = adminGroup.getProjectGroups();
		List<ProjectGroup> tempList = new ArrayList<ProjectGroup>(projectGroups);

		//remove inactive project groups
		for(int i = 0; i <projectGroups.size(); i++){
			if(projectGroups.get(i).isActive() == false){
				tempList.remove(i);
			}
		}

		session.getTransaction().commit();
		session.close();
		closeSessionFactory();
		return tempList;

	}


	/**
	 * Edit a project group
	 */
	public ProjectGroup editProjectGroupDetails(ProjectGroup newProjectGroup){
		openSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction(); 

		ProjectGroup projectGroup = (ProjectGroup) session.get(ProjectGroup.class, newProjectGroup.getProjGroupId());

		if(projectGroup == null){
			closeSessionFactory();
			throw new DatabaseRetrievalException("Unable to retrieve project group with id " +newProjectGroup.getProjGroupId());
		}

		projectGroup.setActive(newProjectGroup.isActive());
		projectGroup.setProjGroupId(newProjectGroup.getProjGroupId());
		projectGroup.setProjGroupName(newProjectGroup.getProjGroupName());

		session.update(projectGroup);
		session.getTransaction().commit();
		session.close();
		closeSessionFactory();


		return projectGroup;

	}

	/**
	 * Associate a project group with an admin group
	 */
	public ProjectGroup addProjectGroupToAdmin(int adminGroupId, int projectGroupId){
		openSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();

		AdminGroup adminGroup = (AdminGroup) session.get(AdminGroup.class, adminGroupId);

		if(adminGroup == null){
			closeSessionFactory();
			throw new DatabaseRetrievalException("Admin group with id " + adminGroupId + " could not be found");
		}
		
		ProjectGroup projGroup = (ProjectGroup) session.get(ProjectGroup.class, projectGroupId);
		
		if(projGroup == null){
			closeSessionFactory();
			throw new DatabaseRetrievalException("Project group with id " + projectGroupId + " could not be found");
		}
		
		projGroup.getAdminGroups().add(adminGroup);
		adminGroup.getProjectGroups().add(projGroup);
		session.saveOrUpdate(projGroup);
		session.saveOrUpdate(adminGroup);
		session.getTransaction().commit();
		session.close();
		closeSessionFactory();
		return projGroup;

	}


}
