package org.se761.project.onlineportfolio.model;

public class Own {
	
	private int ownId;
	private Qualification qualification;
	private AdminGroup adminGroup;
	
	public Own(){
		
	}
	
	

	public Own(int ownId, Qualification qualification, AdminGroup adminGroup) {
		super();
		this.ownId = ownId;
		this.qualification = qualification;
		this.adminGroup = adminGroup;
	}



	public int getOwnId() {
		return ownId;
	}

	public void setOwnId(int ownId) {
		this.ownId = ownId;
	}

	public Qualification getQualification() {
		return qualification;
	}

	public void setQualification(Qualification qualification) {
		this.qualification = qualification;
	}

	public AdminGroup getAdminGroup() {
		return adminGroup;
	}

	public void setAdminGroup(AdminGroup adminGroup) {
		this.adminGroup = adminGroup;
	}
	

}
