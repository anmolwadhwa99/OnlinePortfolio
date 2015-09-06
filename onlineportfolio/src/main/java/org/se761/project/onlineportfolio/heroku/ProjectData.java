package org.se761.project.onlineportfolio.heroku;

import org.json.JSONException;
import org.json.JSONObject;
import org.se761.project.onlineportfolio.model.ProjectGroup;

public class ProjectData extends Server{
	
	public void addProjectData(ProjectGroup projectGroup){
		JSONObject jsonProject = new JSONObject();
		try {
			jsonProject.put("projGroupName", projectGroup.getProjGroupName());
			jsonProject.put("primaryColour", projectGroup.getPrimaryColour());
			jsonProject.put("secondaryColour", projectGroup.getSecondaryColour());
			jsonProject.put("accentColour", projectGroup.getAccentColour());
			jsonProject.put("isActive", projectGroup.isActive());


		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String inputURL = SERVER_ADDRESS + PROJECT_URL;
		Server.HTTPPostMethod(inputURL, jsonProject);
		
	}
	

	public static void main(String[] args) {
		ProjectData projectData = new ProjectData();
		ProjectGroup projectGroup = new ProjectGroup();
		projectGroup.setProjGroupName("Test Group");
		projectGroup.setPrimaryColour("Blue");
		projectGroup.setAccentColour("Red");
		projectGroup.setSecondaryColour("Black");
		projectData.addProjectData(projectGroup);
	}

}
