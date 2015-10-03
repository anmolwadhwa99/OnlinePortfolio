package org.se761.project.onlineportfolio.heroku;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.se761.project.onlineportfolio.model.ProjectGroup;

public class ProjectData extends Server{
	
	private static Map<String, Integer> ids = new HashMap<>();
	
	public static void addProjectData(String name, ProjectGroup projectGroup){
		JSONObject jsonProject = new JSONObject();
		try {
			jsonProject.put("projGroupName", projectGroup.getProjGroupName());
			jsonProject.put("isActive", projectGroup.isActive());


		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String inputURL = SERVER_ADDRESS + PROJECT_URL;
		String response = Server.HTTPPostMethod(inputURL, jsonProject);
		try {
			JSONObject json = new JSONObject(response);
			ids.put(name, json.getInt("projGroupId"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
	}
	

	public static Map<String, Integer> createProjectGroups() {
		ProjectGroup eaGaming = new ProjectGroup();
		eaGaming.setProjGroupName("EA Operations");
		addProjectData("proj_EA", eaGaming);
		
		ProjectGroup appleMobile = new ProjectGroup();
		appleMobile.setProjGroupName("Apple Mobile");
		addProjectData("proj_Apple", appleMobile);
		
		ProjectGroup avgProject = new ProjectGroup();
		avgProject.setProjGroupName("AVG Project");
		addProjectData("proj_Avg", avgProject);
		
		ProjectGroup gasProject = new ProjectGroup();
		gasProject.setProjGroupName("Gas Project");
		addProjectData("proj_Gas", gasProject);
		
		return ids;
	}

}
