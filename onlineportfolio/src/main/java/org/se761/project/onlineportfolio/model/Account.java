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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

import org.se761.project.onlineportfolio.model.AdminGroup;

@XmlRootElement
@Entity
public class Account {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int accountId;
	private String userName;
	private String pin;
	private boolean isAdmin;

	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "accounts", cascade = CascadeType.PERSIST)
	private List<Qualification> quals = new ArrayList<Qualification>();

	//TODO: need mapping with adminGroup!
	
	public Account(){

	}
	
	public Account(int accountId, String userName, String pin, boolean isAdmin) {
		super();
		this.accountId = accountId;
		this.userName = userName;
		this.pin = pin;
		this.isAdmin = isAdmin;
	}



	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

}
