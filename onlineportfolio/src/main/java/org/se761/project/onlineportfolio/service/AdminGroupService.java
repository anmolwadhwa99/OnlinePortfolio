package org.se761.project.onlineportfolio.service;


import java.util.List;

import org.se761.project.onlineportfolio.database.AdminGroupDatabase;
import org.se761.project.onlineportfolio.model.AdminGroup;

public class AdminGroupService {
	
	private AdminGroupDatabase adminGroupDatabase = new AdminGroupDatabase();
	
	/**
	 * Get an admin group 
	 */
	public AdminGroup getAdminGroup(int adminGroupId){
		AdminGroup adminGroup = adminGroupDatabase.getAdminGroup(adminGroupId);	
		return adminGroup;
	}
	
	/**
	 * Get an admin group 
	 */
	public List<AdminGroup> getAllAdminGroups(){
		return adminGroupDatabase.getAllAdminGroups();			
	}
	
	/**
	 * Add an admin group 
	 */
	public void addAdminGroup(AdminGroup adminGroup){
		adminGroupDatabase.addAdminGroup(adminGroup);

	}
	
	/**
	 * Delete admin group 
	 */
	public AdminGroup deleteAdminGroup(int adminGroupId){
		AdminGroup adminGroup = adminGroupDatabase.deleteAdminGroup(adminGroupId);
		return adminGroup;
	}
	
	/**
	 * Get all admin groups associated with an account
	 */
	public List<AdminGroup> getAllAdminGroupsFromAccount(int accountId){
		List<AdminGroup> adminGroups = adminGroupDatabase.getAllAdminGroupsFromAccount(accountId);
		return adminGroups;
	}
	
	/**
	 * Get all admin groups associated with a qualification
	 */
	public List<AdminGroup> getAllAdminGroupsFromQualification(int qualId){
		List<AdminGroup> adminGroups = adminGroupDatabase.getAllAdminGroupsFromQualification(qualId);
		return adminGroups;
	}
	
	

}
