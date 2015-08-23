package org.se761.project.onlineportfolio.service;

import java.util.List;

import org.se761.project.onlineportfolio.database.ProjectGroupDatabase;
import org.se761.project.onlineportfolio.model.ProjectGroup;

public class ProjectGroupService {
	ProjectGroupDatabase projGroupDb = new ProjectGroupDatabase();
	
	/**
	 * Get all project groups
	 */
	public void addProjectGroup(ProjectGroup projGroup){
		projGroupDb.addProjectGroup(projGroup);
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
	 * Get a project group
	 */
	public ProjectGroup getProjectGroup(int projGroupId){
		ProjectGroup projGroup = projGroupDb.getProjectGroup(projGroupId);
		return projGroup;
	}

}
