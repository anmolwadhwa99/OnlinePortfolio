package org.se761.project.onlineportfolio.model;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

import org.se761.project.onlineportfolio.model.helper.MetaData;


@XmlRootElement
@Entity
public class Qualification {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int qualId;
	private String projectName;
	private String clientName;
	
	@Column(columnDefinition="TEXT")
	private String problemStatement;
	
	@Column(columnDefinition="TEXT")
	private String challengesFaced;
	
	@Column(columnDefinition="TEXT")
	private String solution;
	
	@Column(columnDefinition="TEXT")
	private String relevanceToClient;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "Access", joinColumns = { 
			@JoinColumn(name = "qual_id")}, 
			inverseJoinColumns = { @JoinColumn(name = "account_id") })
	private List<Account> accountsQual = new ArrayList<Account>();
	
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "Own", joinColumns = { 
			@JoinColumn(name = "qual_id")}, 
			inverseJoinColumns = { @JoinColumn(name = "admin_group_id") })
	private List<AdminGroup> adminGroups = new ArrayList<AdminGroup>();
	
//	private MetaData metaData;	
	
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
		//this.metaData = metaData;
	}
	

	public Qualification(){
		
	}
	
	public List<Account> getAccounts() {
		return accountsQual;
	}


	public void setAccounts(List<Account> accountsQual) {
		this.accountsQual = accountsQual;
	}

	
	public List<AdminGroup> getAdminGroup() {
		return adminGroups;
	}


	public void setAdminGroup(List<AdminGroup> adminGroups) {
		this.adminGroups = adminGroups;
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
	public List<AdminGroup> getAdminGroups() {
		return adminGroups;
	}


	public void setAdminGroups(List<AdminGroup> adminGroups) {
		this.adminGroups = adminGroups;
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
	

	
//	public MetaData getMetaData() {
//		return metaData;
//	}
//
//	public void setMetaData(MetaData metaData) {
//		this.metaData = metaData;
//	}
	
}
