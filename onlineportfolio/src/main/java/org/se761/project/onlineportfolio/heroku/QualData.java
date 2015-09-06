package org.se761.project.onlineportfolio.heroku;

import org.json.JSONException;
import org.json.JSONObject;
import org.se761.project.onlineportfolio.model.Qualification;

public class QualData extends Server{
	 
	
	public void addQual(Qualification qual){
		JSONObject jsonQual = new JSONObject();
		try {
			jsonQual.put("projectName", qual.getProjectName());
			jsonQual.put("clientName", qual.getClientName());
			jsonQual.put("problemStatement", qual.getProblemStatement());
			jsonQual.put("challengesFaced", qual.getChallengesFaced());
			jsonQual.put("solutionStatement", qual.getSolutionStatement());
			jsonQual.put("relavenceToClient", qual.getRelevanceToClient());
			jsonQual.put("outcomeStatement", qual.getOutcomeStatement());
			jsonQual.put("subtitle", qual.getSubtitle());
			jsonQual.put("isAnonymous", qual.isAnonymous());
			jsonQual.put("anonymousName", qual.getAnonymousName());
			jsonQual.put("isActive", qual.isActive());
			jsonQual.put("emailButton", qual.getEmailButton());
			jsonQual.put("websiteButton", qual.getWebsiteButton());
			jsonQual.put("industry", qual.getIndustry());
			jsonQual.put("tags", qual.getTags());
			jsonQual.put("status", qual.getStatus());
			jsonQual.put("serviceLine", qual.getServiceLine());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String inputURL = SERVER_ADDRESS + QUALS_URL;
		Server.HTTPPostMethod(inputURL, jsonQual);
		
	}

	public static void main(String[] args) {
		QualData qualData = new QualData();
		Qualification qualification = new Qualification();
		qualification.setProjectName("Dheeraj Test");
		qualification.setClientName("Dheeraj");
		qualification.setProblemStatement("Dhee");
		qualData.addQual(qualification);

	}

}
