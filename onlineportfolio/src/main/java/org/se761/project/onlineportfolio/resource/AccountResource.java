package org.se761.project.onlineportfolio.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.se761.project.onlineportfolio.model.Account;
import org.se761.project.onlineportfolio.service.AccountService;


@Path("/account")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AccountResource {
	
	private AccountService accountService = new AccountService();
	
	/**
	 * Adding an account
	 */
	
	@POST
	public Account addAccount(Account account){
		accountService.addAccountDetails(account);
		return account;
	}
	
	/**
	 * Getting any account
	 */
	@GET
	@Path("/{accountId}")
	public Account getAccount(@PathParam("accountId") int accountId){
		return accountService.getAccountDetails(accountId);
	}
	
	/**
	 * Getting all accounts
	 */
	@GET
	public List<Account> getAllAccounts(){
		return accountService.getAllAccounts();
	}
	
	/**
	 * Getting all admin accounts
	 */
	@GET
	@Path("/admin")
	public List<Account> getAdminAccounts(){
		return accountService.getAdminAccounts();
	}
	
	/**
	 * Getting all client accounts
	 */
	@GET
	@Path("/client")
	public List<Account> getClientAccounts(){
		return accountService.getClientAccounts();
	}
	
	/**
	 * Delete an account
	 */
	@DELETE
	@Path("/{accountId}")
	public Account deleteAccount(@PathParam("userName") int accountId){
		return accountService.removeAccount(accountId);
	}
	
	
	
	

}
