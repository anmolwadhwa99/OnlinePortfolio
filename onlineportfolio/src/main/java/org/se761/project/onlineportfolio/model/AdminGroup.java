package org.se761.project.onlineportfolio.model;

public class AdminGroup {
	
	private int adminGroupId;
	private String adminGroupName;
	
	public AdminGroup(){
		
	}
	
	public AdminGroup(int adminGroupId, String adminGroupName) {
		super();
		this.adminGroupId = adminGroupId;
		this.adminGroupName = adminGroupName;
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
