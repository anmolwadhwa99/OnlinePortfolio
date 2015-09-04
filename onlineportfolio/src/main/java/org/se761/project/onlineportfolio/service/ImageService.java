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
	 * Add an image to a qualification
	 */
	public Image addImageToQualification(int imageId, int qualId){
		return imageDatabase.addImageToQualification(imageId, qualId);
	}
	
	/**
	 * Get all images that are client logos
	 */
	public List<Image> getAllClientImages(){
		return imageDatabase.getAllClientImages();
	}
	
	/**
	 * Get all images that are associated with a project
	 */
	public List<Image> getAllProjectImages(){
		return imageDatabase.getAllProjectImages();
	}
	
	/**
	 * Get all images that are stored in the database
	 */
	public List<Image> getAllImages(){
		return imageDatabase.getAllImages();
	}
}
