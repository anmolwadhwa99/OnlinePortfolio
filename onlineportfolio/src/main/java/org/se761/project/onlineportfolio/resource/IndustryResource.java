package org.se761.project.onlineportfolio.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.se761.project.onlineportfolio.model.Industry;
import org.se761.project.onlineportfolio.service.IndustryService;

@Path("/industry")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class IndustryResource {
	private IndustryService industryService = new IndustryService();
	
	/**
	 * Get all industries
	 */
	@GET
	public List<Industry> getAllIndustries(){
		return industryService.getAllIndustries();
	}
	
	/**
	 * Add an industry
	 */
	@POST
	public Industry addIndustry(Industry industry){
		return industryService.addIndustry(industry);
	}
}
