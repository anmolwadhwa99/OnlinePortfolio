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

import org.se761.project.onlineportfolio.model.ProjectGroup;
import org.se761.project.onlineportfolio.service.ProjectGroupService;

@Path("/projectGroup")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProjectGroupResource {
	
	private ProjectGroupService projectGroupService = new ProjectGroupService();
	
	@POST
	public ProjectGroup addProjectGroup(ProjectGroup projectGroup){
		return projectGroupService.addProjectGroup(projectGroup);
		
	}
	
	@GET
	@Path("/{projectGroupId}")
	public ProjectGroup getProjectGroup(@PathParam("projectGroupId") int projectGroupId){
		return projectGroupService.getProjectGroup(projectGroupId);
		
	}
	
	@GET
	@Path("/account/{accountId}")
	public List<ProjectGroup> getProjectGroupForAccount(@PathParam("accountId") int accountId){
		return projectGroupService.getAllProjectGroupsForAccount(accountId);
		
	}
	
	@GET
	public List<ProjectGroup> getAllProjectGroups(){
		return projectGroupService.getAllProjectGroups();
		
	}
	
	@DELETE
	@Path("/{projectGroupId}")
	public ProjectGroup deleteProjectGroup(@PathParam("projectGroupId") int projectGroupId){
		return projectGroupService.deleteProjGroup(projectGroupId);
		
	}
	
	@PUT
	public ProjectGroup editProjectGroupDetails(ProjectGroup projectGroup){
		return projectGroupService.editProjectGroupDetails(projectGroup);
	}
	
	

}
