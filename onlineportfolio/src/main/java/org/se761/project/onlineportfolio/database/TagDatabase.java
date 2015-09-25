package org.se761.project.onlineportfolio.database;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.se761.project.onlineportfolio.exception.DatabaseRetrievalException;
import org.se761.project.onlineportfolio.model.Tag;

public class TagDatabase {
	private SessionFactory sessionFactory;
	private Session session;
	
	public TagDatabase(){

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
	 * Get all tags from the database
	 */
	public List<Tag> getAllTags(){
		openSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		
		String getAllQuery = "FROM Tag t";
		Query query = session.createQuery(getAllQuery);

		List<Tag> tags = query.list();
		
		if(tags.size() == 0){
			closeSessionFactory();
			throw new DatabaseRetrievalException("No tags in the database to display.");
		}
		
		session.getTransaction().commit();
		session.close();
		closeSessionFactory();
		return tags;
		
	}
	
	/**
	 * Add a tag to the database
	 */
	public Tag addTag(Tag tag){
		openSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		
		String getAllQuery = "FROM Tag t";
		Query query = session.createQuery(getAllQuery);

		List<Tag> tags = query.list();
		
		if(tags.size() > 0 ){
			
			for(Tag t : tags){
				if(tag.getTagName().equals(t.getTagName())){
					closeSessionFactory();
					throw new DatabaseRetrievalException("Tag with the same name already exists in the database");
				}
			}
		}
		session.save(tag);
		session.getTransaction().commit();
		session.close();
		closeSessionFactory();
		return tag;
	}
}
