package org.se761.project.onlineportfolio.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.se761.project.onlineportfolio.model.ServiceLine;
import org.se761.project.onlineportfolio.service.ServiceLineService;

@Path("/service")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ServiceLineResource {
	
	private ServiceLineService serviceLineService = new ServiceLineService();
	
	/**
	 * Get all service lines
	 */
	@GET
	public List<ServiceLine> getAllServices(){
		return serviceLineService.getAllServices();
	}
	
	/**
	 * Add service line
	 */
	@POST
	public ServiceLine addServiceLine(ServiceLine serviceLine){
		return serviceLineService.addService(serviceLine);
	}
	
}
