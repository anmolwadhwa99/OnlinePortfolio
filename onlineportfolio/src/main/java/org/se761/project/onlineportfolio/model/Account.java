package org.se761.project.onlineportfolio.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@XmlRootElement
@Entity
public class Account {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int accountId;
	private String accountName;
	private String password;
	private boolean isAdmin;
	private boolean isSuperUser;

	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "accountsQual", cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Qualification> quals = new ArrayList<Qualification>();
	
	
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "accounts", cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<AdminGroup> adminGroup = new ArrayList<AdminGroup>();
	
	
	public Account() {
		
	}
	
	
	public Account(int accountId, String accountName, String password,
			boolean isAdmin, boolean isSuperUser, List<Qualification> quals,
			List<AdminGroup> adminGroup) {
		super();
		this.accountId = accountId;
		this.accountName = accountName;
		this.password = password;
		this.isAdmin = isAdmin;
		this.isSuperUser = isSuperUser;
		this.quals = quals;
		this.adminGroup = adminGroup;
	}


	public int getAccountId() {
		return accountId;
	}


	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}


	public String getAccountName() {
		return accountName;
	}


	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public boolean isAdmin() {
		return isAdmin;
	}


	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}


	public boolean isSuperUser() {
		return isSuperUser;
	}


	public void setSuperUser(boolean isSuperUser) {
		this.isSuperUser = isSuperUser;
	}

	@XmlTransient
	public List<Qualification> getQuals() {
		return quals;
	}


	public void setQuals(List<Qualification> quals) {
		this.quals = quals;
	}

	@XmlTransient
	public List<AdminGroup> getAdminGroup() {
		return adminGroup;
	}


	public void setAdminGroup(List<AdminGroup> adminGroup) {
		this.adminGroup = adminGroup;
	}
	
}
