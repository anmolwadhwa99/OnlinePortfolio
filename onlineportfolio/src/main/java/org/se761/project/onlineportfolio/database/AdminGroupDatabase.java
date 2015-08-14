package org.se761.project.onlineportfolio.database;

import java.util.List;

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
	 * Add a qualification against an admin group
	 */
	public Qualification addQualification(int adminGroupId, Qualification qual){
		openSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		AdminGroup adminGroup = (AdminGroup) session.get(AdminGroup.class, adminGroupId);

		if(adminGroup == null){
			closeSessionFactory();
			throw new DatabaseRetrievalException("Admin Group with id " + adminGroupId + " could not be found, so can't add qualification");
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
	 * Get all qualifications associated it with an admin group
	 */
	public List<Qualification> getAllQualifications(int adminGroupId){
		openSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction(); 

		AdminGroup adminGroup = (AdminGroup) session.get(AdminGroup.class, adminGroupId);

		if(adminGroup == null){
			closeSessionFactory();
			throw new DatabaseRetrievalException("Admin Group with id " + adminGroupId + " could not be found, so can't retrieve qualifications");
		}
		
		List<Qualification> quals = adminGroup.getQuals();
		session.getTransaction().commit();
		session.close();
		closeSessionFactory();
		return quals;
	}
	
	/**
	 * Add an account to an admin group
	 */
	public Account addAccount(int adminGroupId, int accountId){
		openSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		
		AdminGroup adminGroup = (AdminGroup) session.get(AdminGroup.class, adminGroupId);
		Account account = (Account) session.get(Account.class, accountId);
		
		if(adminGroup == null){
			closeSessionFactory();
			throw new DatabaseRetrievalException("Admin Group with id " + adminGroupId + " could not be found, so unable to add account to admin group");
		}
		
		if(account == null){
			closeSessionFactory();
			throw new DatabaseRetrievalException("Account with id " + accountId + " could not be found, so unable to add account to admin group");
		}
		
		adminGroup.getAccounts().add(account);
		account.getAdminGroup().add(adminGroup);
		session.save(account);
		session.save(adminGroup);
		
		session.getTransaction().commit();
		session.close();
		closeSessionFactory();
		
		return account;
	}
	
	/**
	 * Get all accounts associated with an admin group
	 */
	public List<Account> getAllAccounts(int adminGroupId){
		openSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		
		AdminGroup adminGroup = (AdminGroup) session.get(AdminGroup.class, adminGroupId);
		
		if(adminGroup == null){
			closeSessionFactory();
			throw new DatabaseRetrievalException("Admin Group with id " + adminGroupId + " could not be found, so unable to retrieve all accounts");
		}
		
		List<Account> accounts = adminGroup.getAccounts();
		session.getTransaction().commit();
		session.close();
		closeSessionFactory();
		
		return accounts;
		
	}




}
