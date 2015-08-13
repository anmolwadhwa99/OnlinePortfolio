package org.se761.project.onlineportfolio.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.se761.project.onlineportfolio.model.Qualification;

@Path("/qual")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class QualificationResource {
	
	/**
	 * Adding a qualification
	 */
	
	@POST
	public Qualification addQualification(Qualification qualification){
		return null;
	}
	
	/**
	 * Getting any qualification
	 */
	@GET
	@Path("/{qualificationId}")
	public Qualification getQualification(int qualificationId){
		return null;
	}
	
	/**
	 * Getting all qualifications
	 */
	@GET
	public List<Qualification> getAllQualifications(){
		return null;
	}
	
	/**
	 * Getting all qualifications for admin group account
	 */
	@GET
	@Path("/admin/{adminGroupId}")
	public List<Qualification> getAdminGroupQualifications(){
		return null;
	}
	
	/**
	 * Getting clients' qualifications
	 */
	@GET
	@Path("/client/{clientId}")
	public List<Qualification> getClientQualifications(){
		return null;
	}
	
	/**
	 * Delete an qualification
	 */
	@DELETE
	@Path("/{qualificationId}")
	public Qualification deleteQualification(int qualificationId){
		return null;
	}

}
