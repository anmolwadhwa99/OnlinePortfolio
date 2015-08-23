package org.se761.project.onlineportfolio.service;

import java.util.List;

import org.se761.project.onlineportfolio.database.ProjectGroupDatabase;
import org.se761.project.onlineportfolio.model.ProjectGroup;

public class ProjectGroupService {
	ProjectGroupDatabase projGroupDb = new ProjectGroupDatabase();
	
	public void addProjectGroup(ProjectGroup projGroup){
		projGroupDb.addProjectGroup(projGroup);
	}
	
	public ProjectGroup deleteProjGroup(int projGroupId){
		ProjectGroup projGroup = projGroupDb.deleteProjGroup(projGroupId);
		return projGroup;
	}
	
	public List<ProjectGroup> getAllProjectGroups(){
		List<ProjectGroup> projGroups = projGroupDb.getAllProjectGroups();
		return projGroups;
	}

}
