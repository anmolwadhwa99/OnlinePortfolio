package org.se761.project.onlineportfolio.database;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.se761.project.onlineportfolio.exception.DatabaseRetrievalException;
import org.se761.project.onlineportfolio.model.Image;
import org.se761.project.onlineportfolio.model.Image.ImageType;
import org.se761.project.onlineportfolio.model.Qualification;

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



	/**
	 * Make an image inactive (like delete)
	 */
	public Image deleteImage(int imageId){
		openSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		Image image = (Image) session.get(Image.class, imageId);

		if(image == null){
			closeSessionFactory();
			throw new DatabaseRetrievalException("Unable to find image with id " +imageId + " so unable to delete image");
		}
		image.setActive(false);
		session.saveOrUpdate(image);
		session.getTransaction().commit();
		session.close();
		closeSessionFactory();
		return image;
	}
	
	/**
	 * Make an image reactive
	 */
	public Image reactivateImage(int imageId){
		openSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		Image image = (Image) session.get(Image.class, imageId);

		if(image == null){
			closeSessionFactory();
			throw new DatabaseRetrievalException("Unable to find image with id " +imageId + " so unable to reactivate image");
		}
		image.setActive(true);
		session.saveOrUpdate(image);
		session.getTransaction().commit();
		session.close();
		closeSessionFactory();
		return image;
	}
	
	/**
	 * Get all images that are client logos
	 */
	public List<Image> getAllLogoImages(){
		openSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		String getAllQuery = "From Image i";
		List<Image> images = session.createQuery(getAllQuery).list();
		List<Image> returnImages = new ArrayList<Image>();

		for(Image i : images){
			if(i.getImageType() == ImageType.LOGO){
				returnImages.add(i);
			}
		}
		session.getTransaction().commit();
		session.close();
		closeSessionFactory();
		return returnImages;
	}
	
	/**
	 * Get all images that are associated with a product service
	 */
	public List<Image> getAllProductImages(){
		openSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		String getAllQuery = "From Image i";
		List<Image> images = session.createQuery(getAllQuery).list();
		List<Image> returnImages = new ArrayList<Image>();

		for(Image i : images){
			if(i.getImageType() == ImageType.PRODSERV){
				returnImages.add(i);
			}
		}
		session.getTransaction().commit();
		session.close();
		closeSessionFactory();
		return returnImages;
	}
}
