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
import org.se761.project.onlineportfolio.model.Qualification;
import org.se761.project.onlineportfolio.service.QualificationService;

@Path("/qual")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class QualificationResource {
	
	private QualificationService qualificationService = new QualificationService();
	
	/**
	 * Adding a qualification
	 */
	
	@POST
	public Qualification addQualification(Qualification qualification){
		qualificationService.addQual(qualification);
		return qualification;
	}
	
	/**
	 * Adding qual to admin group 
	 */
	@POST
	@Path("/admin/{adminGroupId}/{qualId}")
	public Qualification addAdminGroupQualifications(@PathParam("adminGroupId") int adminGroupId, @PathParam("qualId") int qualId ){
		return qualificationService.addQualificationToAdminGroup(adminGroupId, qualId);
	}
	
	/**
	 * Adding qual to project group 
	 */
	@POST
	@Path("/project/{projectGroupId}/{qualId}")
	public Qualification addProjectGroupQualifications(@PathParam("projectGroupId") int projectGroupId, @PathParam("qualId") int qualId ){
		return qualificationService.addQualificationToProjectGroup(projectGroupId, qualId);
	}
	
	/**
	 * Add qual to account
	 */
	@POST
	@Path("/account/{accountId}/{qualId}")
	public Qualification addQualificationToAccount(@PathParam("accountId") int accountId, @PathParam("qualId") int qualId){
		return qualificationService.addQualificationToAccount(accountId, qualId);
	}
	
	/**
	 * Getting any qualification
	 */
	@GET
	@Path("/{qualificationId}")
	public Qualification getQualification(@PathParam("qualificationId") int qualificationId){
		return qualificationService.getQual(qualificationId);
	}
	
	/**
	 * Getting all qualifications
	 */
	@GET
	public List<Qualification> getAllQualifications(){
		return qualificationService.getAllQuals();
	}
	
	/**
	 * Getting all public qualifications
	 */
	@GET
	@Path("/public")
	public List<Qualification> getAllPublicQualifications(){
		return qualificationService.getAllPublicQuals();
	}
	

	/**
	 * Getting all qualifications from admin group 
	 */
	@GET
	@Path("/admin/{adminGroupId}")
	public List<Qualification> getAdminGroupQualifications(@PathParam("adminGroupId") int adminGroupId){
		return qualificationService.getAllQualificationsFromAdminGroup(adminGroupId);
	}
	
	/**
	 * Getting all qualifications from project group 
	 */
	@GET
	@Path("/project/{projectGroupId}")
	public List<Qualification> getProjectGroupQualifications(@PathParam("projectGroupId") int projectGroupId){
		return qualificationService.getAllQualificationsFromProjectGroup(projectGroupId);
	}
	
	/**
	 * Get all qualifications from account
	 */
	@GET
	@Path("/account/{accountId}")
	public List<Qualification> getQualificationsFromAccount(@PathParam("accountId") int accountId){
		return qualificationService.getAllQualificationsFromAccount(accountId);
	}
	
	
	/**
	 * Archive qualification
	 */
	@DELETE
	@Path("/{qualificationId}")
	public Qualification deleteQualification(@PathParam("qualificationId") int qualificationId){
		return qualificationService.deleteQual(qualificationId);
	}
	
	/**
	 * Delete an qualification
	 */
	@DELETE
	@Path("/delete/{qualificationId}")
	public Qualification deleteQualificationFromDB(@PathParam("qualificationId") int qualificationId){
		return qualificationService.deleteQualFromDB(qualificationId);
	}
	
	
	/**
	 * Edit a qualification
	 */
	@PUT
	public Qualification editQualification(Qualification qual){
		return qualificationService.editQual(qual);
	}
	
	/**
	 * Reactivate qualification
	 */
	@PUT
	@Path("/reactivate/{qualId}")
	public Qualification reactivateQualification(@PathParam("qualId") int qualId){
		return qualificationService.reactivateQual(qualId);
	}
}
