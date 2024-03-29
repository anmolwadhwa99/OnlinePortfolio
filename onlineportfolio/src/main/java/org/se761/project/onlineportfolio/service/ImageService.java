package org.se761.project.onlineportfolio.service;

import java.util.List;

import org.se761.project.onlineportfolio.database.ImageDatabase;
import org.se761.project.onlineportfolio.model.Image;

public class ImageService {
	
	private ImageDatabase imageDatabase = new ImageDatabase();
	
	/**
	 * Add an image
	 */
	public Image addImage(Image image){
		return imageDatabase.addImage(image);
	}
	
	/**
	 * Retrieve an image
	 */
	public Image getImage(int imageId){
		return imageDatabase.getImage(imageId);
	}
	
	/**
	 * Mark an image as inactive
	 */
	public Image deleteImage(int imageId){
		return imageDatabase.deleteImage(imageId);
	}
	
	/**
	 * Get all images that are client logos
	 */
	public List<Image> getAllLogoImages(){
		return imageDatabase.getAllLogoImages();
	}
	
	/**
	 * Get all images that are associated with a product service
	 */
	public List<Image> getAllProductImages(){
		return imageDatabase.getAllProductImages();
	}
	
	/**
	 * Get all images that are stored in the database
	 */
	public List<Image> getAllImages(){
		return imageDatabase.getAllImages();
	}
	
	/**
	 * Reactivate image
	 */
	public Image reactivateImage(int imageId){
		return imageDatabase.reactivateImage(imageId);
	}
}
