package org.se761.project.onlineportfolio.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.se761.project.onlineportfolio.model.Account;
import org.se761.project.onlineportfolio.model.AdminGroup;

@Path("/admin")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AdminGroupResource {
	
	/**
	 * Adding a admin group
	 */
	
	@POST
	public AdminGroup addAdminGroup(AdminGroup adminGroup){
		return null;
	}
	
	/**
	 * Getting a admin group
	 */
	@GET
	@Path("/{adminGroupId}")
	public AdminGroup getAdminGroup(int adminGroupId){
		return null;
	}
	
	/**
	 * Delete admin group
	 */
	@DELETE
	@Path("/{adminGroupId}")
	public AdminGroup deleteAccount(int adminGroupId){
		return null;
	}

}
