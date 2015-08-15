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
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;



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
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Account> accountsQual = new ArrayList<Account>();
	
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "Own", joinColumns = { 
			@JoinColumn(name = "qual_id")}, 
			inverseJoinColumns = { @JoinColumn(name = "admin_group_id") })
	@Fetch(value = FetchMode.SUBSELECT)
	private List<AdminGroup> adminGroups = new ArrayList<AdminGroup>();
	
	private String metaDataIndustry; //e.g. Financial Services, Education, etc.
	private String metaDataTag; 
	private String metaDataStatus; //either "open" or "confidential"
	private String metaDataDeloitteServiceLine; //e.g. auditing, consulting, etc
	private String metaDataColourScheme;
	
	
	//TO-DO
	//Client Logo
	//Product picture
	//Microsite link
	

	public Qualification(String projectName, String clientName,
			String problemStatement, String challengesFaced, String solution,
			String relevanceToClient, String metaDataIndustry,
			String metaDataTag, String metaDataStatus,
			String metaDataDeloitteServiceLine, String metaDataColourScheme) {
		super();
		this.projectName = projectName;
		this.clientName = clientName;
		this.problemStatement = problemStatement;
		this.challengesFaced = challengesFaced;
		this.solution = solution;
		this.relevanceToClient = relevanceToClient;
		this.metaDataIndustry = metaDataIndustry;
		this.metaDataTag = metaDataTag;
		this.metaDataStatus = metaDataStatus;
		this.metaDataDeloitteServiceLine = metaDataDeloitteServiceLine;
		this.metaDataColourScheme = metaDataColourScheme;
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

	@XmlTransient
	public List<Account> getAccountsQual() {
		return accountsQual;
	}


	public void setAccountsQual(List<Account> accountsQual) {
		this.accountsQual = accountsQual;
	}

	@XmlTransient
	public List<AdminGroup> getAdminGroups() {
		return adminGroups;
	}


	public void setAdminGroups(List<AdminGroup> adminGroups) {
		this.adminGroups = adminGroups;
	}



	public String getMetaDataIndustry() {
		return metaDataIndustry;
	}


	public void setMetaDataIndustry(String metaDataIndustry) {
		this.metaDataIndustry = metaDataIndustry;
	}


	public String getMetaDataTag() {
		return metaDataTag;
	}


	public void setMetaDataTag(String metaDataTag) {
		this.metaDataTag = metaDataTag;
	}


	public String getMetaDataStatus() {
		return metaDataStatus;
	}


	public void setMetaDataStatus(String metaDataStatus) {
		this.metaDataStatus = metaDataStatus;
	}


	public String getMetaDataDeloitteServiceLine() {
		return metaDataDeloitteServiceLine;
	}


	public void setMetaDataDeloitteServiceLine(String metaDataDeloitteServiceLine) {
		this.metaDataDeloitteServiceLine = metaDataDeloitteServiceLine;
	}


	public String getMetaDataColourScheme() {
		return metaDataColourScheme;
	}


	public void setMetaDataColourScheme(String metaDataColourScheme) {
		this.metaDataColourScheme = metaDataColourScheme;
	}
		
}
