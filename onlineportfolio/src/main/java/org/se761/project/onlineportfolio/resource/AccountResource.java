package org.se761.project.onlineportfolio.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
		return null;
	}
	
	/**
	 * Getting any account
	 */
	@GET
	@Path("/{accountId}")
	public Account getAccount(int accountId){
		return null;
	}
	
	/**
	 * Getting all accounts
	 */
	@GET
	public List<Account> getAllAccounts(){
		return null;
	}
	
	/**
	 * Getting all admin accounts
	 */
	@GET
	@Path("/admin")
	public List<Account> getAdminAccounts(){
		return null;
	}
	
	/**
	 * Getting all client accounts
	 */
	@GET
	@Path("/client")
	public List<Account> getClientAccounts(){
		return null;
	}
	
	/**
	 * Delete an account
	 */
	@DELETE
	@Path("/{accountId}")
	public Account deleteAccount(int accountId){
		return null;
	}
	
	
	
	

}
