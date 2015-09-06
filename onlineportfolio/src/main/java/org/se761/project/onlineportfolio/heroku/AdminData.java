package org.se761.project.onlineportfolio.heroku;

import org.json.JSONException;
import org.json.JSONObject;
import org.se761.project.onlineportfolio.model.Account;
import org.se761.project.onlineportfolio.model.AdminGroup;

public class AdminData extends Server {
	
	public AdminData(){
		
	}
	
	public void addAdminGroup(AdminGroup adminGroup){
		JSONObject jsonAdmin = new JSONObject();
		try {
			jsonAdmin.put("adminGroupName", adminGroup.getAdminGroupName());
			jsonAdmin.put("isActive", adminGroup.isActive());


		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String inputURL = SERVER_ADDRESS + ADMIN_URL;
		Server.HTTPPostMethod(inputURL, jsonAdmin);
		
	}

	public static void main(String[] args) {
		AdminData adminData = new AdminData();
		AdminGroup adminGroup = new AdminGroup();
		adminGroup.setAdminGroupName("Dheeraj Group");
		adminData.addAdminGroup(adminGroup);

	}

}
