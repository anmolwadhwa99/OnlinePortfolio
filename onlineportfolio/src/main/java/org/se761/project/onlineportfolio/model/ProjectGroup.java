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

@Entity
@XmlRootElement
public class ProjectGroup {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int projGroupId; 
	private String projGroupName;

	
	private boolean isActive = true; //be  default

	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "projGroups", cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Qualification> quals = new ArrayList<Qualification>();
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "Access", joinColumns = { 
			@JoinColumn(name = "proj_group_id")}, 
			inverseJoinColumns = { @JoinColumn(name = "account_id") })
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Account> accountsProj = new ArrayList<Account>();
	
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "projectGroups", cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<AdminGroup> adminGroups = new ArrayList<AdminGroup>();

	
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public int getProjGroupId() {
		return projGroupId;
	}

	public void setProjGroupId(int projGroupId) {
		this.projGroupId = projGroupId;
	}

	public String getProjGroupName() {
		return projGroupName;
	}

	public void setProjGroupName(String projGroupName) {
		this.projGroupName = projGroupName;
	}

	
	@XmlTransient
	public List<Qualification> getQuals() {
		return quals;
	}

	public void setQuals(List<Qualification> quals) {
		this.quals = quals;
	}
	
	@XmlTransient
	public List<Account> getAccountsProj() {
		return accountsProj;
	}

	public void setAccountsProj(List<Account> accountsProj) {
		this.accountsProj = accountsProj;
	}

	@XmlTransient
	public List<AdminGroup> getAdminGroups() {
		return adminGroups;
	}

	public void setAdminGroups(List<AdminGroup> adminGroups) {
		this.adminGroups = adminGroups;
	}
	
	
	
}
