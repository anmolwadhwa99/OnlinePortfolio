package org.se761.project.onlineportfolio.database;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.se761.project.onlineportfolio.exception.DatabaseRetrievalException;
import org.se761.project.onlineportfolio.model.Industry;


public class IndustryDatabase {
	private SessionFactory sessionFactory;
	private Session session;
	
	public IndustryDatabase(){

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
	 * Get all industries stored in the database
	 */
	public List<Industry> getAllIndustries(){
		openSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		
		String getAllQuery = "FROM Industry i";
		Query query = session.createQuery(getAllQuery);

		List<Industry> industries = query.list();
		
		if(industries.size() == 0){
			closeSessionFactory();
			throw new DatabaseRetrievalException("No industries in the database to display.");
		}
		session.getTransaction().commit();
		session.close();
		closeSessionFactory();
		return industries;
	}
	
	/**
	 * Add an industry to the database
	 */
	public Industry addIndustry(Industry industry){
		openSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		
		String getAllQuery = "FROM Industry i";
		Query query = session.createQuery(getAllQuery);

		List<Industry> industries = query.list();
		
		if(industries.size() > 0 ){
			
			for(Industry i : industries){
				if(industry.getIndustryName().equals(i.getIndustryName())){
					closeSessionFactory();
					throw new DatabaseRetrievalException("Industry with the same name already exists in the database");
				}
			}
		}
		session.save(industry);
		session.getTransaction().commit();
		session.close();
		closeSessionFactory();
		return industry;
	}
}
