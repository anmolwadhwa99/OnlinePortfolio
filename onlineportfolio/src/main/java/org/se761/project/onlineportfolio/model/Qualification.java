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
	private String projectName; //mandatory
	private String clientName; //mandatory
	
	@Column(columnDefinition="TEXT")
	private String problemStatement; //mandatory
	
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
	
	private boolean isAnonymous; //if true need to ensure that client name isnt mentioned anywhere else
	
	private String anonymousName; //mandatory if isAnonymous = true
	
	private boolean isActive = true; //default is true
	
	
	private String emailButton;
	private String websiteButton;
	private String industry; //e.g. Financial Services, Education, etc.
	
	@Column(columnDefinition="TEXT")
	private String tags;
	
	@Column(columnDefinition="TEXT")
	private String clientImage;
	
	private String status; //either "open" or "confidential"
	private String serviceLine; //e.g. auditing, consulting, etc


	//TODO
	//client logo
	//product services image	
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "Own", joinColumns = { 
			@JoinColumn(name = "qual_id")}, 
			inverseJoinColumns = { @JoinColumn(name = "admin_group_id") })
	@Fetch(value = FetchMode.SUBSELECT)
	private List<AdminGroup> adminGroups = new ArrayList<AdminGroup>();
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "Include", joinColumns = { 
			@JoinColumn(name = "qual_id")}, 
			inverseJoinColumns = { @JoinColumn(name = "proj_group_id") })
	@Fetch(value = FetchMode.SUBSELECT)
	private List<ProjectGroup> projGroups = new ArrayList<ProjectGroup>();
	
	
	
	
	public Qualification(){
		
	}
	


	public Qualification(int qualId, String projectName, String clientName,
			String problemStatement, String challengesFaced,
			String solutionStatement, String relevanceToClient,
			String outcomeStatement, String subtitle, boolean isAnonymous,
			String anonymousName, boolean isActive, String emailButton,
			String websiteButton, List<AdminGroup> adminGroups,
			List<ProjectGroup> projGroups, String industry, String tags,
			String status, String serviceLine) {
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
		this.anonymousName = anonymousName;
		this.isActive = isActive;
		this.emailButton = emailButton;
		this.websiteButton = websiteButton;
		this.adminGroups = adminGroups;
		this.projGroups = projGroups;
		this.industry = industry;
		this.tags = tags;
		this.status = status;
		this.serviceLine = serviceLine;

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



	public String getSolutionStatement() {
		return solutionStatement;
	}



	public void setSolutionStatement(String solutionStatement) {
		this.solutionStatement = solutionStatement;
	}



	public String getRelevanceToClient() {
		return relevanceToClient;
	}



	public void setRelevanceToClient(String relevanceToClient) {
		this.relevanceToClient = relevanceToClient;
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



	public String getAnonymousName() {
		return anonymousName;
	}



	public void setAnonymousName(String anonymousName) {
		this.anonymousName = anonymousName;
	}



	public boolean isActive() {
		return isActive;
	}



	public void setActive(boolean isActive) {
		this.isActive = isActive;
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


	@XmlTransient
	public List<AdminGroup> getAdminGroups() {
		return adminGroups;
	}



	public void setAdminGroups(List<AdminGroup> adminGroups) {
		this.adminGroups = adminGroups;
	}


	@XmlTransient
	public List<ProjectGroup> getProjGroups() {
		return projGroups;
	}



	public void setProjGroups(List<ProjectGroup> projGroups) {
		this.projGroups = projGroups;
	}



	public String getClientImage() {
		return clientImage;
	}



	public void setClientImage(String clientImage) {
		this.clientImage = clientImage;
	}


	

}
