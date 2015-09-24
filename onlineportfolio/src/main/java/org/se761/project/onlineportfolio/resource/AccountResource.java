package org.se761.project.onlineportfolio.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
		return accountService.addAccountDetails(account);
	}
	
	/**
	 * Setting account to a particular admin group
	 */
	@POST
	@Path("/admin/{adminGroupId}/{accountId}")
	public Account getAdminGroupAccounts(@PathParam("adminGroupId") int adminGroupId, @PathParam("accountId") int accountId ){
		return accountService.addAccountToAdminGroup(adminGroupId, accountId);
	}
	
	/**
	 * Setting account to particular project group
	 */
	@POST
	@Path("/project/{projectGroupId}/{accountId}")
	public Account addAccountToProjectGroup(@PathParam("projectGroupId") int projGroupId, @PathParam("accountId") int accountId){
		return accountService.addAccountToProjectGroup(projGroupId, accountId);
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
	public List<Account> getAllAdminAccounts(){
		return accountService.getAdminAccounts();
	}
	
	/**
	 * Getting all admin accounts from a particular group
	 */
	@GET
	@Path("/admin/{adminGroupId}")
	public List<Account> getAdminGroupAccounts(@PathParam("adminGroupId") int adminGroupId){
		return accountService.getAllAccountsFromAdminGroup(adminGroupId);
	}
	
	/**
	 * Getting all accounts from a particular project group
	 */
	@GET
	@Path("/project/{projectGroupId}")
	public List<Account> getProjectGroupAccounts(@PathParam("projectGroupId") int projGroupId){
		return accountService.getAllAccountsFromProjectGroup(projGroupId);
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
	public Account deleteAccount(@PathParam("accountId") int accountId){
		return accountService.removeAccount(accountId);
	}
	
	/**
	 * Edit an account
	 */
	@PUT
	public Account editAccountDetails(Account account){
		return accountService.editAccountDetails(account);
	}
	
	/**
	 * Verify account
	 */
	@GET
	@Path("/verify/{password}")
	public Account verifyAccount(@PathParam("password") String password){
		return accountService.verifyAccount(password);
	}
	
	/**
	 * Reactivate account
	 */
	@PUT
	@Path("/reactivate/{accountId}")
	public Account reactivateAccount(@PathParam("accountId") int accountId){
		return accountService.reactivateAccount(accountId);
	}
	
}
