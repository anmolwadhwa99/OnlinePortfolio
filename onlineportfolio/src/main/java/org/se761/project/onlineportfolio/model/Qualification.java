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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;



@XmlRootElement
@Entity
public class Qualification {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int qualId;
	private String projectName;
	private String clientName;
	
	@Column(columnDefinition="TEXT")
	private String problemStatement;
	
	@Column(columnDefinition="TEXT")
	private String challengesFaced;
	
	@Column(columnDefinition="TEXT")
	private String solutionStatement;
	
	@Column(columnDefinition="TEXT")
	private String relevanceToClient;
	
	@Column(columnDefinition="TEXT")
	private String outcomeStatement;
	
	@Column(columnDefinition="TEXT")
	private String subtitle;
	
	private boolean isAnonymous;
	
	private boolean isActive;
	
	@Column(columnDefinition="TEXT")
	private String primaryColour;
	@Column(columnDefinition="TEXT")
	private String secondaryColour;
	@Column(columnDefinition="TEXT")
	private String accentColour;
	
	private String emailButton;
	private String websiteButton;
	
	
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
	
	private String industry; //e.g. Financial Services, Education, etc.
	
	@Column(columnDefinition="TEXT")
	private String tags;
	
	private String status; //either "open" or "confidential"
	private String serviceLine; //e.g. auditing, consulting, etc
	private String metaDataColourScheme;
	
	
	//TODO
	//Client Logo
	//Product service image

	




	public Qualification(){
		
	}


	public Qualification(int qualId, String projectName, String clientName,
			String problemStatement, String challengesFaced,
			String solutionStatement, String relevanceToClient,
			String outcomeStatement, String subtitle, boolean isAnonymous,
			boolean isActive, String primaryColour, String secondaryColour,
			String accentColour, String emailButton, String clientLogo,
			List<Account> accountsQual, List<AdminGroup> adminGroups,
			String industry, String tags, String status,
			String serviceLine, String metaDataColourScheme) {
		super();
		this.qualId = qualId;
		this.projectName = projectName;
		this.clientName = clientName;
		this.problemStatement = problemStatement;
		this.challengesFaced = challengesFaced;
		this.solutionStatement = solutionStatement;
		this.relevanceToClient = relevanceToClient;
		this.outcomeStatement = outcomeStatement;
		this.subtitle = subtitle;
		this.isAnonymous = isAnonymous;
		this.isActive = isActive;
		this.primaryColour = primaryColour;
		this.secondaryColour = secondaryColour;
		this.accentColour = accentColour;
		this.emailButton = emailButton;
		this.websiteButton = clientLogo;
		this.accountsQual = accountsQual;
		this.adminGroups = adminGroups;
		this.industry = industry;
		this.tags = tags;
		this.status = status;
		this.serviceLine = serviceLine;
		this.metaDataColourScheme = metaDataColourScheme;
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
		return solutionStatement;
	}


	public void setSolution(String solution) {
		this.solutionStatement = solution;
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
		return industry;
	}


	public void setMetaDataIndustry(String metaDataIndustry) {
		this.industry = metaDataIndustry;
	}


	public String getMetaDataStatus() {
		return status;
	}


	public void setMetaDataStatus(String metaDataStatus) {
		this.status = metaDataStatus;
	}


	public String getMetaDataDeloitteServiceLine() {
		return serviceLine;
	}


	public void setMetaDataDeloitteServiceLine(String metaDataDeloitteServiceLine) {
		this.serviceLine = metaDataDeloitteServiceLine;
	}


	public String getMetaDataColourScheme() {
		return metaDataColourScheme;
	}


	public void setMetaDataColourScheme(String metaDataColourScheme) {
		this.metaDataColourScheme = metaDataColourScheme;
	}
	
	public String getSolutionStatement() {
		return solutionStatement;
	}


	public void setSolutionStatement(String solutionStatement) {
		this.solutionStatement = solutionStatement;
	}


	public String getOutcomeStatement() {
		return outcomeStatement;
	}


	public void setOutcomeStatement(String outcomeStatement) {
		this.outcomeStatement = outcomeStatement;
	}


	public String getSubtitle() {
		return subtitle;
	}


	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}


	public boolean isAnonymous() {
		return isAnonymous;
	}


	public void setAnonymous(boolean isAnonymous) {
		this.isAnonymous = isAnonymous;
	}


	public boolean isActive() {
		return isActive;
	}


	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}


	public String getPrimaryColour() {
		return primaryColour;
	}


	public void setPrimaryColour(String primaryColour) {
		this.primaryColour = primaryColour;
	}


	public String getSecondaryColour() {
		return secondaryColour;
	}


	public void setSecondaryColour(String secondaryColour) {
		this.secondaryColour = secondaryColour;
	}


	public String getAccentColour() {
		return accentColour;
	}


	public void setAccentColour(String accentColour) {
		this.accentColour = accentColour;
	}


	public String getEmailButton() {
		return emailButton;
	}


	public void setEmailButton(String emailButton) {
		this.emailButton = emailButton;
	}


	public String getWebsiteButton() {
		return websiteButton;
	}


	public void setWebsiteButton(String websiteButton) {
		this.websiteButton = websiteButton;
	}


	public String getIndustry() {
		return industry;
	}


	public void setIndustry(String industry) {
		this.industry = industry;
	}


	public String getTags() {
		return tags;
	}


	public void setTags(String tags) {
		this.tags = tags;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getServiceLine() {
		return serviceLine;
	}


	public void setServiceLine(String serviceLine) {
		this.serviceLine = serviceLine;
	}



		
}
