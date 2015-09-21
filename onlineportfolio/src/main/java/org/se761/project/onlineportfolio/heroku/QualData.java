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
			jsonQual.put("clientImage", qual.getClientImage());
			jsonQual.put("projectImage", qual.getProjectImage());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String inputURL = SERVER_ADDRESS + QUALS_URL;
		Server.HTTPPostMethod(inputURL, jsonQual);
		
	}

	public static void main(String[] args) {
		
		//EA Sports 
		QualData EASportsQual = new QualData();
		Qualification qualification = new Qualification();
		qualification.setProjectName("Capability and Strategy Assessment and Sales Analytics");
		qualification.setClientName("EA Sports");
		qualification.setProblemStatement("EA Sports, a gaming software developer and distributor, sought to align the entire organization on CRM vision, goals and establishment of high-priority CRM capabilities to build consistency across multiple brands and groups.");
		qualification.setSolutionStatement("We performed a capability assessment of current state and identified gaps between existing processes and other leading industry practices in 50+ best-in-class CRM organizations. The assessment identified CRM capability gaps in insights and analytics, customer experience, marketing effectiveness, sales effectiveness, and service effectiveness. Using this assessment, we developed a multi-year roadmap that outlined initiatives to achieve the end vision.");
		qualification.setRelevanceToClient("We used our assessment to develop a comprehensive 3-year CRM roadmap and incorporate it into the annual operating plan. The roadmap created internal alignment on CRM definition, vision, and priorities and extended vision to the technical environment required to enable and support the priorities and gained CIO support and sponsorship.");
		qualification.setServiceLine("Technology");
		EASportsQual.addQual(qualification);
		
		
		

	}

}
