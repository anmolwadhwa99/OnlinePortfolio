package org.se761.project.onlineportfolio.heroku;

import org.json.JSONException;
import org.json.JSONObject;
import org.se761.project.onlineportfolio.model.Account;
import org.se761.project.onlineportfolio.model.AdminGroup;
import org.se761.project.onlineportfolio.model.ServiceLine.DeloitteServiceLine;

public class AdminData extends Server {
	
	public static void addAdminGroup(AdminGroup adminGroup){
		JSONObject jsonAdmin = new JSONObject();
		try {
			jsonAdmin.put("adminGroupName", adminGroup.getAdminGroupName());
			jsonAdmin.put("active", adminGroup.isActive());


		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String inputURL = SERVER_ADDRESS + ADMIN_URL;
		Server.HTTPPostMethod(inputURL, jsonAdmin);
		
	}

	public static void createAdminGroups(){
		
		AdminGroup technology = new AdminGroup();
		technology.setAdminGroupName(DeloitteServiceLine.technology);
		addAdminGroup(technology);
		
		AdminGroup risk = new AdminGroup();
		risk.setAdminGroupName(DeloitteServiceLine.risk);
		addAdminGroup(risk);
		
		AdminGroup strategy = new AdminGroup();
		strategy.setAdminGroupName(DeloitteServiceLine.strategy);
		addAdminGroup(strategy);


	}

}
