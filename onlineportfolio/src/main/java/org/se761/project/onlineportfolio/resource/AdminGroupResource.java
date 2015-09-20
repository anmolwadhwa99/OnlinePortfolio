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

import org.se761.project.onlineportfolio.model.Account;
import org.se761.project.onlineportfolio.model.AdminGroup;
import org.se761.project.onlineportfolio.service.AccountService;
import org.se761.project.onlineportfolio.service.AdminGroupService;

@Path("/admin")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AdminGroupResource {
	
	private AdminGroupService adminGroupService = new AdminGroupService();
	
	/**
	 * Adding a admin group
	 */
	
	@POST
	public AdminGroup addAdminGroup(AdminGroup adminGroup){
		adminGroupService.addAdminGroup(adminGroup);
		return adminGroup;
	}
	
	/**
	 * Getting all admin groups
	 */
	
	@GET
	public List<AdminGroup> getAdminGroups(){
		return adminGroupService.getAllAdminGroups();
	}
		
	/**
	 * Getting a admin group
	 */
	@GET
	@Path("/{adminGroupId}")
	public AdminGroup getAdminGroup(@PathParam("adminGroupId") int adminGroupId){
		return adminGroupService.getAdminGroup(adminGroupId);
	}
	
	/**
	 * Delete admin group
	 */
	@DELETE
	@Path("/{adminGroupId}")
	public AdminGroup deleteAdminGroup(@PathParam("adminGroupId") int adminGroupId){
		return adminGroupService.deleteAdminGroup(adminGroupId);
	}
	
	/**
	 * Get all admin groups associated with an account
	 */
	@GET
	@Path("/account/{accountId}")
	public List<AdminGroup> getAllAdminGroupsFromAccount(@PathParam("accountId") int accountId){
		return adminGroupService.getAllAdminGroupsFromAccount(accountId);
	}

	/**
	 * Get all admin groups associated with a qualification
	 */
	@GET
	@Path("/qual/{qualificationId}")
	public List<AdminGroup> getAllAdminGroupsFromQualification(@PathParam("qualificationId") int qualificationId){
		return adminGroupService.getAllAdminGroupsFromQualification(qualificationId);
	}
	
	/**
	 * Edit admin group
	 */
	@PUT
	public AdminGroup editAdminGroupDetails(AdminGroup adminGroup){
		return adminGroupService.editAdminGroupDetails(adminGroup);
	}
	
	/**
	 * Reactivate admin group
	 */
	@PUT
	@Path("/reactivate/{adminGroupId}")
	public AdminGroup reactivateAdminGroup(int adminGroupId){
		return adminGroupService.reactivateAdminGroup(adminGroupId);
	}
}
