package org.se761.project.onlineportfolio.service;

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
	
}
