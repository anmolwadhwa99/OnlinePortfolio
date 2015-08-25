package org.se761.project.onlineportfolio.database;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.se761.project.onlineportfolio.exception.DatabaseRetrievalException;
import org.se761.project.onlineportfolio.model.Account;
import org.se761.project.onlineportfolio.model.AdminGroup;
import org.se761.project.onlineportfolio.model.Qualification;

public class AdminGroupDatabase {

	private SessionFactory sessionFactory;
	private Session session;

	public AdminGroupDatabase(){

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
	 * Get a admin group from the database
	 */
	public AdminGroup getAdminGroup(int adminGroupId){
		openSessionFactory();
		session = sessionFactory.openSession();
		AdminGroup adminGroup = (AdminGroup) session.get(AdminGroup.class, adminGroupId);

		if(adminGroup == null){
			closeSessionFactory();
			throw new DatabaseRetrievalException("Admin Group with id " + adminGroupId + " could not be found");
		}

		session.close();
		closeSessionFactory();
		return adminGroup;
	}
	
	/**
	 * Get all admin groups from the database
	 */
	public List<AdminGroup> getAllAdminGroups(){
		openSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		String getAllQuery = "FROM AdminGroup ag";
		Query query = session.createQuery(getAllQuery);

		List<AdminGroup> adminGroups = query.list();
		
		if(adminGroups.size() == 0){
			closeSessionFactory();
			throw new DatabaseRetrievalException("No admin groups in the database to display.");
		}
		

		session.close();
		closeSessionFactory();
		return adminGroups;
	}

	/**
	 * Add a admin group to the database
	 */
	public void addAdminGroup(AdminGroup adminGroup){
		openSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(adminGroup);
		session.getTransaction().commit();
		session.close();
		closeSessionFactory();
	}

	/**
	 * Delete admin group from database
	 */
	public AdminGroup deleteAdminGroup(int adminGroupId){
		openSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		AdminGroup adminGroup = (AdminGroup) session.get(AdminGroup.class, adminGroupId);

		if(adminGroup == null){
			closeSessionFactory();
			throw new DatabaseRetrievalException("Admin Group with id " + adminGroupId + " could not be found.");
		}

		session.delete(adminGroup);
		session.getTransaction().commit();
		session.close();
		closeSessionFactory();
		return adminGroup;
	}
	
	/**
	 * Get all admin groups associated with a qualification
	 */
	public List<AdminGroup> getAllAdminGroupsFromQualification(int qualId){
		openSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		
		Qualification qual = (Qualification) session.get(Qualification.class, qualId);
		
		if(qual == null){
			closeSessionFactory();
			throw new DatabaseRetrievalException("Qual with id " + qualId + " could not be found, so can't retrieve admin groups");
		}
		
		List<AdminGroup> adminGroups = qual.getAdminGroups();
		session.getTransaction().commit();
		session.close();
		closeSessionFactory();
		return adminGroups;
	}
	
	/**
	 * Get all admin groups associated with an account
	 */
	public List<AdminGroup> getAllAdminGroupsFromAccount(int accountId){
		openSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		
		Account account = (Account) session.get(Account.class, accountId);
		
		if(account == null){
			closeSessionFactory();
			throw new DatabaseRetrievalException("Account with id " + accountId + " could not be found, so unable to retrieve admin groups");
		}
		
		List<AdminGroup> adminGroups = account.getAdminGroup();
		session.getTransaction().commit();
		session.close();
		closeSessionFactory();
		
		return adminGroups;
	}
	
	/**
	 * Edit admin group
	 */
	
	public AdminGroup editAdminGroupDetails(int adminGroupId, AdminGroup modifiedAdminGroup){
		openSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		
		AdminGroup adminGroup = (AdminGroup) session.get(AdminGroup.class, adminGroupId);
		
		if (adminGroup == null){
			closeSessionFactory();
			throw new DatabaseRetrievalException("AdminGroup with id: " + adminGroupId + " could not be found");
		}
		
		adminGroup = modifiedAdminGroup;
		session.update(adminGroup);
		session.getTransaction().commit();
		session.close();
		closeSessionFactory();
		
		return adminGroup;
		
	}

}
