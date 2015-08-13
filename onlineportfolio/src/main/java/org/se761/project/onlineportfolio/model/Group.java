package org.se761.project.onlineportfolio.model;

public class Group {
	
	private int groupId;
	private AdminGroup adminGroup;
	private Account account;
	
	public Group(){
		
	}

	public Group(int groupId, AdminGroup adminGroup, Account account) {
		super();
		this.groupId = groupId;
		this.adminGroup = adminGroup;
		this.account = account;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public AdminGroup getAdminGroup() {
		return adminGroup;
	}

	public void setAdminGroup(AdminGroup adminGroup) {
		this.adminGroup = adminGroup;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	
}
