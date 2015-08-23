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

import org.se761.project.onlineportfolio.model.Account;
import org.se761.project.onlineportfolio.model.AdminGroup;
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
	 * Getting all qualifications from admin group 
	 */
	@GET
	@Path("/admin/{adminGroupId}")
	public List<Qualification> getAdminGroupQualifications(@PathParam("adminGroupId") int adminGroupId){
		return qualificationService.getAllQualificationsFromAdminGroup(adminGroupId);
	}
	
	
	/**
	 * Delete an qualification
	 */
	@DELETE
	@Path("/{qualificationId}")
	public Qualification deleteQualification(@PathParam("qualificationId") int qualificationId){
		return qualificationService.deleteQual(qualificationId);
	}
	
	
	
	
}
