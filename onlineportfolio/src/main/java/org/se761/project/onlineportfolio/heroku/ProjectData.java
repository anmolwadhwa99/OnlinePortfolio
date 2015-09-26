package org.se761.project.onlineportfolio.heroku;

import org.json.JSONException;
import org.json.JSONObject;
import org.se761.project.onlineportfolio.model.ProjectGroup;

public class ProjectData extends Server{
	
	public void addProjectData(ProjectGroup projectGroup){
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
	

	public static void main(String[] args) {
		ProjectData projectData = new ProjectData();
		ProjectGroup eaGaming = new ProjectGroup();
		eaGaming.setProjGroupName("EA Operations");
		projectData.addProjectData(eaGaming);
		
		ProjectGroup appleMobile = new ProjectGroup();
		appleMobile.setProjGroupName("Apple Mobile");
		projectData.addProjectData(appleMobile);
		
		ProjectGroup avgProject = new ProjectGroup();
		avgProject.setProjGroupName("AVG Project");
		projectData.addProjectData(avgProject);
		
		ProjectGroup gasProject = new ProjectGroup();
		gasProject.setProjGroupName("Gas Project");
		projectData.addProjectData(gasProject);
	}

}
