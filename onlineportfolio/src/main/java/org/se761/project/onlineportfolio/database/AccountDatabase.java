package org.se761.project.onlineportfolio.database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.se761.project.onlineportfolio.exception.DatabaseRetrievalException;
import org.se761.project.onlineportfolio.model.Account;

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
	 * Retrieves details for a particular account 
	 */
	public Account getUserDetails(int accountId){
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

}
