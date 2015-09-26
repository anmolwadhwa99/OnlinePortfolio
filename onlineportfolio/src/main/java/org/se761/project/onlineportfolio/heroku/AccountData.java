package org.se761.project.onlineportfolio.heroku;

import org.json.JSONException;
import org.json.JSONObject;
import org.se761.project.onlineportfolio.model.Account;
import org.se761.project.onlineportfolio.model.Qualification;

public class AccountData extends Server {
	
	public AccountData(){
		
	}
	
	public void addAccount(Account account){
		JSONObject jsonAccount = new JSONObject();
		try {
			jsonAccount.put("accountName", account.getAccountName());
			jsonAccount.put("password", account.getPassword());
			jsonAccount.put("admin", account.isAdmin());
			jsonAccount.put("superUser", account.isSuperUser());
			jsonAccount.put("primaryColour", account.getPrimaryColour());
			jsonAccount.put("secondaryColour", account.getSecondaryColour());
			jsonAccount.put("accentColour", account.getAccentColour());
			jsonAccount.put("active", account.isActive());

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String inputURL = SERVER_ADDRESS + ACCOUNT_URL;
		Server.HTTPPostMethod(inputURL, jsonAccount);
		
	}

	public static void main(String[] args) {
		AccountData accountData = new AccountData();
		Account superUser = new Account();
		superUser.setAccountName("SuperUser");
		superUser.setPassword("super");
		superUser.setActive(true);
		superUser.setAdmin(true);
		superUser.setSuperUser(true);
		superUser.setPrimaryColour("red");
		superUser.setAccentColour("blue");
		superUser.setSecondaryColour("green");
		accountData.addAccount(superUser);
		
		Account admin = new Account();
		admin.setAccountName("Admin");
		admin.setPassword("admin");
		admin.setActive(true);
		admin.setAdmin(true);
		admin.setSuperUser(false);
		admin.setPrimaryColour("red");
		admin.setAccentColour("blue");
		admin.setSecondaryColour("green");
		accountData.addAccount(admin);
		
		Account client = new Account();
		client.setAccountName("Client");
		client.setPassword("client");
		client.setActive(true);
		client.setAdmin(false);
		client.setSuperUser(false);
		client.setPrimaryColour("red");
		client.setAccentColour("blue");
		client.setSecondaryColour("green");
		accountData.addAccount(client);

	}

}
