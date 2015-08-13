package org.se761.project.onlineportfolio.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
public class AdminGroup {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int adminGroupId;
	private String adminGroupName;

	@OneToMany(mappedBy = "adminGroup", orphanRemoval = true, fetch = FetchType.EAGER)
	private List<Qualification> quals = new ArrayList<Qualification>();

	@OneToMany(mappedBy = "adminGroup", orphanRemoval = true, fetch = FetchType.EAGER)
	private List<Account> accounts = new ArrayList<Account>();

	public AdminGroup(){

	}

	public AdminGroup(int adminGroupId, String adminGroupName) {
		super();
		this.adminGroupId = adminGroupId;
		this.adminGroupName = adminGroupName;
	}


	public List<Qualification> getQuals() {
		return quals;
	}

	public void setQuals(List<Qualification> quals) {
		this.quals = quals;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public int getAdminGroupId() {
		return adminGroupId;
	}

	public void setAdminGroupId(int adminGroupId) {
		this.adminGroupId = adminGroupId;
	}

	public String getAdminGroupName() {
		return adminGroupName;
	}

	public void setAdminGroupName(String adminGroupName) {
		this.adminGroupName = adminGroupName;
	}





}
