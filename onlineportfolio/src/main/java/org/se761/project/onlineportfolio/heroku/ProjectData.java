package org.se761.project.onlineportfolio.heroku;

import org.json.JSONException;
import org.json.JSONObject;
import org.se761.project.onlineportfolio.model.ProjectGroup;

public class ProjectData extends Server{
	
	public static void addProjectData(ProjectGroup projectGroup){
		JSONObject jsonProject = new JSONObject();
		try {
			jsonProject.put("projGroupName", projectGroup.getProjGroupName());
			jsonProject.put("isActive", projectGroup.isActive());


		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String inputURL = SERVER_ADDRESS + PROJECT_URL;
		Server.HTTPPostMethod(inputURL, jsonProject);
		
	}
	

	public static void createProjectGroups() {
		ProjectGroup eaGaming = new ProjectGroup();
		eaGaming.setProjGroupName("EA Operations");
		addProjectData(eaGaming);
		
		ProjectGroup appleMobile = new ProjectGroup();
		appleMobile.setProjGroupName("Apple Mobile");
		addProjectData(appleMobile);
		
		ProjectGroup avgProject = new ProjectGroup();
		avgProject.setProjGroupName("AVG Project");
		addProjectData(avgProject);
		
		ProjectGroup gasProject = new ProjectGroup();
		gasProject.setProjGroupName("Gas Project");
		addProjectData(gasProject);
	}

}
