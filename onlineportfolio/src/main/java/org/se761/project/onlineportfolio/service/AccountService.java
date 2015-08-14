package org.se761.project.onlineportfolio.service;


import java.util.List;

import org.se761.project.onlineportfolio.database.AccountDatabase;
import org.se761.project.onlineportfolio.model.Account;
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
	 * Adds an account to the database
	 */
	public void addAccountDetails(Account account){
		accountDatabase.addAccountDetails(account);
	}
	
	/**
	 * Deletes an account 
	 */
	public Account removeAccount(int accountId){
		Account account = accountDatabase.removeAccount(accountId);
		return account;
	}
	
	/**
	 * Adds a qualification against an existing account
	 */
	public Qualification addQualification(int accountId, Qualification qualification){
		Qualification qual = accountDatabase.addQualification(accountId, qualification);
		return qual;
	}
	
	public List<Qualification> getAllQualifications(int accountId){
		return accountDatabase.getAllQualifications(accountId);
	}
	
	
}
