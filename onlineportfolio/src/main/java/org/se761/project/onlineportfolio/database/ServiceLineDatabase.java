package org.se761.project.onlineportfolio.database;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.se761.project.onlineportfolio.exception.DatabaseRetrievalException;
import org.se761.project.onlineportfolio.model.ServiceLine;

public class ServiceLineDatabase {

	private SessionFactory sessionFactory;
	private Session session;
	
	public ServiceLineDatabase(){

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
	 * Get all service lines stored in the database
	 */
	public List<ServiceLine> getAllServices(){
		openSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		
		String getAllQuery = "FROM ServiceLine s";
		Query query = session.createQuery(getAllQuery);

		List<ServiceLine> services = query.list();
		
		if(services.size() == 0){
			closeSessionFactory();
			throw new DatabaseRetrievalException("No service lines in the database to display.");
		}
		session.getTransaction().commit();
		session.close();
		closeSessionFactory();
		return services;
	}
	
	/**
	 * Add a service line to the database
	 */
	public ServiceLine addService(ServiceLine service){
		openSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		
		String getAllQuery = "FROM ServiceLine s";
		Query query = session.createQuery(getAllQuery);

		List<ServiceLine> services = query.list();
		
		if(services.size() > 0 ){
			for(ServiceLine s : services){
				if(service.getServiceLineName().equals(s.getServiceLineName())){
					closeSessionFactory();
					return null;
				}
			}
		}
		session.save(service);
		session.getTransaction().commit();
		session.close();
		closeSessionFactory();
		return service;
	}
	
}
