package org.se761.project.onlineportfolio.database;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.se761.project.onlineportfolio.exception.DatabaseRetrievalException;
import org.se761.project.onlineportfolio.model.Image;

public class ImageDatabase {
	private SessionFactory sessionFactory;
	private Session session;
	
	public ImageDatabase(){
		
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
	 * Add an image
	 */
	public Image addImage(Image image){
		openSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(image);
		session.getTransaction().commit();
		session.close();
		closeSessionFactory();
		return image;
	}
	
	/**
	 * Retrieve an image
	 */
	public Image getImage(int imageId){
		openSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		Image image = (Image) session.get(Image.class, imageId);
		
		if(image == null){
			closeSessionFactory();
			throw new DatabaseRetrievalException("Unable to retrieve image with id " + imageId);
		}
		session.getTransaction().commit();
		session.close();
		closeSessionFactory();
		return image;
	}
	
	/**
	 * Retrieve all images from the database
	 */
	public List<Image> getAllImages(){
		openSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		String getAllQuery = "From Image i";
		List<Image> images = session.createQuery(getAllQuery).list();
		
		if(images == null){
			images = Collections.<Image> emptyList();
		}
		session.getTransaction().commit();
		session.close();
		closeSessionFactory();
		return images;
	}
	
	
}
