package org.se761.project.onlineportfolio.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.se761.project.onlineportfolio.model.Tag;
import org.se761.project.onlineportfolio.service.TagService;

@Path("/tag")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TagResource {
	private TagService tagService = new TagService();
	
	/**
	 * Get all tags from the database
	 */
	@GET
	public List<Tag> getAllTags(){
		return tagService.getAllTags();
	}
	
	/**
	 * Add a tag to the database
	 */
	@POST
	public Tag addTag(Tag tag){
		return tagService.addTag(tag);
	}
}
