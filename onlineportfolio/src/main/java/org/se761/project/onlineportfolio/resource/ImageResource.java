package org.se761.project.onlineportfolio.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
	 * Adding an image
	 */
	@POST
	public Image addImage(Image image){
		return imageService.addImage(image);
	}
	
	/**
	 * Get all images stored in the database
	 */
	@GET 
	public List<Image> getAllImages(){
		return imageService.getAllImages();
	}
	
	/**
	 * Get all client images stored in the database
	 */
	@GET
	@Path("/client")
	public List<Image> getAllClientImages(){
		return imageService.getAllClientImages();
	}
	
	/**
	 * Get all project images stored in the database
	 */
	@GET
	@Path("/project")
	public List<Image> getAllProjectImages(){
		return imageService.getAllProjectImages();
	}
	
	/**
	 * Getting any image
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
	 * Add an existing image against a qualification
	 */
	@POST
	@Path("/qual/{imageId}/{qualId}")
	public Image addImageToQualification(@PathParam("imageId") int imageId, @PathParam("qualId") int qualId){
		return imageService.addImageToQualification(imageId, qualId);
	}
	
}
