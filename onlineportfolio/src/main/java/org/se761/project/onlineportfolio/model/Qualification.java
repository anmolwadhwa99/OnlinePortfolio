package org.se761.project.onlineportfolio.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

import org.se761.project.onlineportfolio.model.helper.MetaData;


@XmlRootElement
@Entity
public class Qualification {
	@Id
	private int qualId;
	private String projectName;
	private String clientName;
	private String problemStatement;
	private String challengesFaced;
	private String solution;
	private String relevanceToClient;
	private MetaData metaData;	
	
	//TO-DO
	//Client Logo
	//Product picture
	//Microsite link
	
	public Qualification(int qualId, String projectName, String clientName,
			String problemStatement, String challengesFaced, String solution,
			String relevanceToClient, MetaData metaData) {
		super();
		this.qualId = qualId;
		this.projectName = projectName;
		this.clientName = clientName;
		this.problemStatement = problemStatement;
		this.challengesFaced = challengesFaced;
		this.solution = solution;
		this.relevanceToClient = relevanceToClient;
		this.metaData = metaData;
	}
	

	public Qualification(){
		
	}
	
	public int getQualId() {
		return qualId;
	}
	public void setQualId(int qualId) {
		this.qualId = qualId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getProblemStatement() {
		return problemStatement;
	}
	public void setProblemStatement(String problemStatement) {
		this.problemStatement = problemStatement;
	}
	public String getChallengesFaced() {
		return challengesFaced;
	}
	public void setChallengesFaced(String challengesFaced) {
		this.challengesFaced = challengesFaced;
	}
	public String getSolution() {
		return solution;
	}
	public void setSolution(String solution) {
		this.solution = solution;
	}
	public String getRelevanceToClient() {
		return relevanceToClient;
	}
	public void setRelevanceToClient(String relevanceToClient) {
		this.relevanceToClient = relevanceToClient;
	}
	public MetaData getMetaData() {
		return metaData;
	}

	public void setMetaData(MetaData metaData) {
		this.metaData = metaData;
	}
	
}
