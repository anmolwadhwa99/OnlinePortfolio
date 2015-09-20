package org.se761.project.onlineportfolio.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.se761.project.onlineportfolio.model.Image;
import org.se761.project.onlineportfolio.service.ImageService;



@Path("/image")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ImageResource {
	private ImageService imageService = new ImageService();
	
	/**
	 * Add an image
	 */
	@POST
	public Image addImage(Image image){
		return imageService.addImage(image);
	}
	
	/**
	 * Get all images that are stored in the database
	 */
	@GET 
	public List<Image> getAllImages(){
		return imageService.getAllImages();
	}
	
	/**
	 * Get all client logo images that are stored in the database
	 */
	@GET
	@Path("/logo")
	public List<Image> getAllLogoImages(){
		return imageService.getAllLogoImages();
	}
	
	/**
	 * Get all product service images that are stored in the database
	 */
	@GET
	@Path("/product")
	public List<Image> getAllProductImages(){
		return imageService.getAllProductImages();
	}
	
	/**
	 * Retrieve an image
	 */
	@GET
	@Path("/{imageId}")
	public Image getImage(@PathParam("imageId") int imageId){
		return imageService.getImage(imageId);
	}
	
	/**
	 * Delete an image
	 */
	@DELETE
	@Path("/{imageId}")
	public Image deleteImage(@PathParam("imageId") int imageId){
		return imageService.deleteImage(imageId);
	}
	
	/**
	 * Reactivate image
	 */
	@PUT
	@Path("/reactivate/{imageId}")
	public Image reactivateImage(@PathParam("imageId") int imageId){
		return imageService.reactivateImage(imageId);
	}
	
}
