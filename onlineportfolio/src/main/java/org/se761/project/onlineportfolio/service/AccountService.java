package org.se761.project.onlineportfolio.service;


import java.util.List;

import org.se761.project.onlineportfolio.database.AccountDatabase;
import org.se761.project.onlineportfolio.model.Account;
import org.se761.project.onlineportfolio.model.AdminGroup;
import org.se761.project.onlineportfolio.model.Qualification;

public class AccountService {

	private AccountDatabase accountDatabase = new AccountDatabase();
	
	/**
	 * Gets details for a particular account 
	 */
	public Account getAccountDetails(int accountId){
		Account account = accountDatabase.getAccountDetails(accountId);
		return account;
	}
	
	/**
	 * Gets all the accounts 
	 */
	public List<Account> getAllAccounts(){
		List<Account> accounts = accountDatabase.getAllAccounts();	
		return accounts;
	}
	
	
	/**
	 * Gets all the client accounts 
	 */
	public List<Account> getClientAccounts(){
		List<Account> clientAccounts = accountDatabase.getClientAccounts();
		return clientAccounts;
	}
	
	/**
	 * Gets all the admin accounts 
	 */
	public List<Account> getAdminAccounts(){
		List<Account> adminAccounts = accountDatabase.getAdminAccounts();
		return adminAccounts;
	}
	
	/**
	 * Get all the accounts associated with an admin group
	 */
	public List<Account> getAllAccountsFromAdminGroup(int adminGroupId){
		List<Account> accounts = accountDatabase.getAllAccountsFromAdminGroup(adminGroupId);
		return accounts;
	}
	
	/**
	 * Adds an account to the database
	 */
	public void addAccountDetails(Account account){
		accountDatabase.addAccountDetails(account);
	}
	
	/**
	 * Add existing account to an admin group
	 */
	public Account addAccountToAdminGroup(int adminGroupId, int accountId){
		Account account = accountDatabase.addAccountToAdminGroup(adminGroupId, accountId);
		return account;
	}
	
	/**
	 * Add existing account against project group
	 */
	public Account addAccountToProjectGroup(int projGroupId, int accountId){
		Account account = accountDatabase.addAccountToProjectGroup(projGroupId, accountId);
		return account;
	}
	
	/**
	 * Deletes an account 
	 */
	public Account removeAccount(int accountId){
		Account account = accountDatabase.removeAccount(accountId);
		return account;
	}
	
	/**
	 * Get all accounts associated with a project group
	 */
	public List<Account> getAllAccountsFromProjectGroup(int projGroupId){
		List<Account> accounts = accountDatabase.getAllAccountsFromProjectGroup(projGroupId);
		return accounts;
	}
	
	/**
	 * Edit account details
	 */
	public Account editAccountDetails(Account modifiedAccount){
		Account account = accountDatabase.editAccountDetails(modifiedAccount);
		return account;
	}
	
	/**
	 * Get account details based on the password that is given
	 */
	public Account verifyAccount(String password){
		Account account = accountDatabase.verifyAccount(password);
		return account;
	}
	
	
}
