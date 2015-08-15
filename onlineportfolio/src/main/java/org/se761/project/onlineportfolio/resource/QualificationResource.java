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

import org.se761.project.onlineportfolio.model.Qualification;
import org.se761.project.onlineportfolio.model.helper.MetaData;
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
		MetaData metaData = new MetaData(); //this needs to be changed
		qualificationService.addQual(qualification, metaData);
		return qualification;
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
	
//TODO
//	/**
//	 * Getting all qualifications for admin group account
//	 */
//	@GET
//	@Path("/admin/{adminGroupName}")
//	public List<Qualification> getAdminGroupQualifications(){
//		return null;
//	}
//	
//	/**
//	 * Getting clients' qualifications
//	 */
//	@GET
//	@Path("/client/{clientName}")
//	public List<Qualification> getClientQualifications(){
//		return null;
//	}
	
	/**
	 * Delete an qualification
	 */
	@DELETE
	@Path("/{qualificationId}")
	public Qualification deleteQualification(@PathParam("qualificationId") int qualificationId){
		return qualificationService.deleteQual(qualificationId);
	}

}
