package org.se761.project.onlineportfolio.service;

import org.se761.project.onlineportfolio.database.AdminGroupDatabase;
import org.se761.project.onlineportfolio.model.AdminGroup;

public class AdminGroupService {
	
	private AdminGroupDatabase adminGroupDatabase = new AdminGroupDatabase();
	
	/**
	 * Get a admin group from the database
	 */
	public AdminGroup getAdminGroup(int adminGroupId){
		AdminGroup adminGroup = adminGroupDatabase.getAdminGroup(adminGroupId);	
		return adminGroup;
	}
	
	/**
	 * Add a admin group to the database
	 */
	public void addAdminGroup(AdminGroup adminGroup){
		adminGroupDatabase.addAdminGroup(adminGroup);

	}
	
	/**
	 * Delete admin group from database
	 */
	public AdminGroup deleteAdminGroup(int adminGroupId){
		AdminGroup adminGroup = adminGroupDatabase.deleteAdminGroup(adminGroupId);
		return adminGroup;
	}

}
