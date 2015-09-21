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
			jsonAccount.put("isAdmin", account.isAdmin());
			jsonAccount.put("isSuperUser", account.isSuperUser());
			jsonAccount.put("primaryColour", account.getPrimaryColour());
			jsonAccount.put("secondaryColour", account.getSecondaryColour());
			jsonAccount.put("accentColour", account.getAccentColour());
			jsonAccount.put("isActive", account.isActive());

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String inputURL = SERVER_ADDRESS + ACCOUNT_URL;
		Server.HTTPPostMethod(inputURL, jsonAccount);
		
	}

	public static void main(String[] args) {
		AccountData accountData = new AccountData();
		Account account = new Account();
		account.setAccountName("OnlyAdmin");
		account.setPassword("1234");
		account.setActive(true);
		account.setAdmin(true);
		account.setPrimaryColour("red");
		account.setAccentColour("blue");
		account.setSecondaryColour("green");
		accountData.addAccount(account);

	}

}
