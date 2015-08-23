package org.se761.project.onlineportfolio.database;


import java.util.ArrayList;
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

public class AccountDatabase {
	
	private SessionFactory sessionFactory;
	private Session session;
	
	public AccountDatabase(){
		
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
	 * Gets details for a particular account 
	 */
	public Account getAccountDetails(int accountId){
		openSessionFactory();
		session = sessionFactory.openSession();
		Account account = (Account) session.get(Account.class, accountId);
		
		if(account == null){
			closeSessionFactory();
			throw new DatabaseRetrievalException("Account with id " + accountId + " could not be found");
		}
		
		session.close();
		closeSessionFactory();
		return account;
	}
	
	/**
	 * Gets all the accounts from the database
	 */
	public List<Account> getAllAccounts(){
		openSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		String getAllQuery = "FROM Account a";
		Query query = session.createQuery(getAllQuery);

		List<Account> accounts = query.list();
		
		if(accounts.size() == 0){
			closeSessionFactory();
			throw new DatabaseRetrievalException("No accounts in the database to display.");
		}
		
		session.close();
		closeSessionFactory();
		return accounts;
	}
	
	
	/**
	 * Gets all the client accounts from the database
	 */
	public List<Account> getClientAccounts(){
		List<Account> clientAccounts = new ArrayList<Account>();
		
		List<Account> allAccounts = getAllAccounts();
		for (Account a : allAccounts){
			if(a.isAdmin() == false){
				clientAccounts.add(a);
			}
		}
		
		return clientAccounts;
	}
	
	/**
	 * Gets all the admin accounts from the database
	 */
	public List<Account> getAdminAccounts(){
		List<Account> adminAccounts = new ArrayList<Account>();
		
		List<Account> allAccounts = getAllAccounts();
		for (Account a : allAccounts){
			if(a.isAdmin() == true){
				adminAccounts.add(a);
			}
		}
		
		return adminAccounts;
	}
	
	/**
	 * Adds an account to the database
	 */
	public void addAccountDetails(Account account){
		openSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(account);
		session.getTransaction().commit();
		session.close();
		closeSessionFactory();
	}
	
	/**
	 * Deletes an account from the database
	 */
	public Account removeAccount(int accountId){
		openSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		Account account = (Account) session.get(Account.class, accountId);
		
		if(account == null){
			closeSessionFactory();
			throw new DatabaseRetrievalException("Account with id " + accountId + " could not be found.");
		}
		
		session.delete(account);
		session.getTransaction().commit();
		session.close();
		closeSessionFactory();
		return account;
	}
	
	/**
	 * Add an account to an admin group
	 */
	public Account addAccountToAdminGroup(int adminGroupId, int accountId){
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
	public List<Account> getAllAccountsFromAdminGroup(int adminGroupId){
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
