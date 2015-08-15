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
import org.se761.project.onlineportfolio.model.helper.MetaData;

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
		
		session.close();
		closeSessionFactory();
		return qual;
	}
	
	
	/**
	 * Gets all the quals from the database
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
			throw new DatabaseRetrievalException("No accounts in the database to display.");
		}
		
		session.close();
		closeSessionFactory();
		return quals;
	}
	
	/**
	 * Adding a qualification to the database
	 */
	public void addQual(Qualification qual, MetaData metaData){
		qual.setMetaData(metaData);
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
		
		session.delete(qual);
		session.getTransaction().commit();
		session.close();
		closeSessionFactory();
		return qual;
	}
	
	/**
	 * Adds a qualification against an existing account
	 */
	public Qualification addQualificationToAccount(int accountId, int qualId){

		openSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		Account account  = (Account) session.get(Account.class, accountId);
		
		if(account == null){
			closeSessionFactory();
			throw new DatabaseRetrievalException("Account with id " + accountId + " could not be found so qual could not be added");
		}
		
		Qualification qual = (Qualification) session.get(Qualification.class, qualId);
		
		if(qual == null){
			closeSessionFactory();
			throw new DatabaseRetrievalException("Qual with id " + qualId + " could not be found");
		}
		
		
		account.getQuals().add(qual);
		qual.getAccountsQual().add(account);
		session.save(qual);
		session.save(account);
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
	 * Get all qualifications associated it with an account
	 */
	public List<Qualification> getAllQualificationsFromAccount(int accountId){
		openSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		
		Account account  = (Account) session.get(Account.class, accountId);
		
		if(account == null){
			closeSessionFactory();
			throw new DatabaseRetrievalException("Account with id " + accountId + " could not be found so quals could not be retrieved");
		}
		
		List<Qualification> quals = account.getQuals();
		session.getTransaction().commit();
		session.close();
		closeSessionFactory();
		return quals;
		
	}
	
	/**
	 * Add metadata against a qualification
	 */
	public MetaData addMetaData(int qualId, MetaData metaData){
		openSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		Qualification qual = (Qualification) session.get(Qualification.class, qualId);
		
		if(qual == null){
			closeSessionFactory();
			throw new DatabaseRetrievalException("Qual with id " + qualId + " could not be found. Unable to add metadata");
		}
		
		qual.setMetaData(metaData);
		session.save(qual);
		session.getTransaction().commit();
		session.close();
		closeSessionFactory();
		return metaData;
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
		session.getTransaction().commit();
		session.close();
		closeSessionFactory();
		return quals;
	}

}
