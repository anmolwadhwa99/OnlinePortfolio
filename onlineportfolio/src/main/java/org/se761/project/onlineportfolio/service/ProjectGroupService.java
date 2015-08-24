package org.se761.project.onlineportfolio.service;

import java.util.List;

import org.se761.project.onlineportfolio.database.ProjectGroupDatabase;
import org.se761.project.onlineportfolio.model.ProjectGroup;

public class ProjectGroupService {
	ProjectGroupDatabase projGroupDb = new ProjectGroupDatabase();
	
	/**
	 * Get all project groups
	 */
	public ProjectGroup addProjectGroup(ProjectGroup projGroup){
		ProjectGroup pg = projGroupDb.addProjectGroup(projGroup);
		return pg;
	}
	
	/**
	 * Delete project group
	 */
	public ProjectGroup deleteProjGroup(int projGroupId){
		ProjectGroup projGroup = projGroupDb.deleteProjGroup(projGroupId);
		return projGroup;
	}
	
	/**
	 * Get all project groups
	 */
	public List<ProjectGroup> getAllProjectGroups(){
		List<ProjectGroup> projGroups = projGroupDb.getAllProjectGroups();
		return projGroups;
	}
	
	/**
	 * Get all project groups for an account
	 */
	public List<ProjectGroup> getAllProjectGroupsForAccount(int accountId){
		List<ProjectGroup> projectGroups = projGroupDb.getProjectGroupForAccount(accountId);
		return projectGroups;
	}
	
	/**
	 * Get a project group
	 */
	public ProjectGroup getProjectGroup(int projGroupId){
		ProjectGroup projGroup = projGroupDb.getProjectGroup(projGroupId);
		return projGroup;
	}
	
	/**
	 * Edit project group
	 */
	public ProjectGroup editProjectGroupDetails(int projectGroupId, ProjectGroup newProjectGroup){
		ProjectGroup projectGroup = projGroupDb.editProjectGroupDetails(projectGroupId, newProjectGroup);
		return projectGroup;
	}

}
