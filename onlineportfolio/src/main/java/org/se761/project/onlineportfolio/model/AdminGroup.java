package org.se761.project.onlineportfolio.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.se761.project.onlineportfolio.model.ServiceLine.DeloitteServiceLine;

@XmlRootElement
@Entity
public class AdminGroup {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int adminGroupId;
	
	private DeloitteServiceLine adminGroupName;
	private boolean isActive = true; //be default
	
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "Permission", joinColumns = { 
			@JoinColumn(name = "admin_group_id")}, 
			inverseJoinColumns = { @JoinColumn(name = "account_id") })
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Account> accounts = new ArrayList<Account>();
	
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "adminGroups", cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Qualification> quals = new ArrayList<Qualification>();
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "AdminGroupAccess", joinColumns = { 
			@JoinColumn(name = "admin_group_id")}, 
			inverseJoinColumns = { @JoinColumn(name = "proj_group_id") })
	@Fetch(value = FetchMode.SUBSELECT)
	private List<ProjectGroup> projectGroups = new ArrayList<ProjectGroup>();

	
	public AdminGroup(){

	}

	public AdminGroup(int adminGroupId, DeloitteServiceLine adminGroupName) {
		super();
		this.adminGroupId = adminGroupId;
		this.adminGroupName = adminGroupName;
	}
	
	

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@XmlTransient
	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	
	@XmlTransient
	public List<Qualification> getQuals() {
		return quals;
	}

	public void setQuals(List<Qualification> quals) {
		this.quals = quals;
	}

	public int getAdminGroupId() {
		return adminGroupId;
	}

	public void setAdminGroupId(int adminGroupId) {
		this.adminGroupId = adminGroupId;
	}

	public DeloitteServiceLine getAdminGroupName() {
		return adminGroupName;
	}

	public void setAdminGroupName(DeloitteServiceLine adminGroupName) {
		this.adminGroupName = adminGroupName;
	}
	
	@XmlTransient
	public List<ProjectGroup> getProjectGroups() {
		return projectGroups;
	}

	public void setProjectGroups(List<ProjectGroup> projectGroups) {
		this.projectGroups = projectGroups;
	}
	
	

}
