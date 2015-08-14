package org.se761.project.onlineportfolio.service;


import java.util.List;

import org.se761.project.onlineportfolio.database.AccountDatabase;
import org.se761.project.onlineportfolio.model.Account;

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
	 * Gets all the accounts from the database
	 */
	public List<Account> getAllAccounts(){
		List<Account> accounts = accountDatabase.getAllAccounts();	
		return accounts;
	}
	
	
	/**
	 * Gets all the client accounts from the database
	 */
	public List<Account> getClientAccounts(){
		List<Account> clientAccounts = accountDatabase.getClientAccounts();
		return clientAccounts;
	}
	
	/**
	 * Gets all the admin accounts from the database
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
	 * Deletes an account from the database
	 */
	public Account removeAccount(int accountId){
		Account account = accountDatabase.removeAccount(accountId);
		return account;
	}
}
