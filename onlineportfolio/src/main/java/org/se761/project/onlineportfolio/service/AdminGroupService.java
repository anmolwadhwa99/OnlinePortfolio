package org.se761.project.onlineportfolio.service;

import java.util.List;

import org.se761.project.onlineportfolio.database.AdminGroupDatabase;
import org.se761.project.onlineportfolio.model.Account;
import org.se761.project.onlineportfolio.model.AdminGroup;
import org.se761.project.onlineportfolio.model.Qualification;

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
	 * Add qualification against an admin group
	 */
	public Qualification addQualification(int adminGroupId, Qualification qual){
		Qualification qualification = adminGroupDatabase.addQualification(adminGroupId, qual);
		return qualification;
	}
	
	/**
	 * Get all qualifications associated with an admin group
	 */
	public List<Qualification> getAllQualifications(int adminGroupId){
		List<Qualification> quals = adminGroupDatabase.getAllQualifications(adminGroupId);
		return quals;
	}
	
	/**
	 * Add account against an admin group
	 */
	public Account addAccount(int adminGroupId, int accountId){
		Account account = adminGroupDatabase.addAccount(adminGroupId, accountId);
		return account;
	}
	
	/**
	 * Get all the accounts associated with an admin group
	 */
	public List<Account> getAllAccounts(int adminGroupId){
		List<Account> accounts = adminGroupDatabase.getAllAccounts(adminGroupId);
		return accounts;
	}
}
