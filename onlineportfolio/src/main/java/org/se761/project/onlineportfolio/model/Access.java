package org.se761.project.onlineportfolio.model;

public class Access {
	
	private int accessId;
	private Qualification qualification;
	private Account account;
	
	public Access(){
		
	}
	
	public Access(int accessId, Qualification qualification, Account account) {
		super();
		this.accessId = accessId;
		this.qualification = qualification;
		this.account = account;
	}

	public int getAccessId() {
		return accessId;
	}

	public void setAccessId(int accessId) {
		this.accessId = accessId;
	}

	public Qualification getQualification() {
		return qualification;
	}

	public void setQualification(Qualification qualification) {
		this.qualification = qualification;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
	
	
	
	

}
