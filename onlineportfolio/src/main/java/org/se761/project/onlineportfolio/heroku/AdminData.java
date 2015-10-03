package org.se761.project.onlineportfolio.heroku;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.se761.project.onlineportfolio.model.Account;
import org.se761.project.onlineportfolio.model.AdminGroup;
import org.se761.project.onlineportfolio.model.ServiceLine.DeloitteServiceLine;

public class AdminData extends Server {
	
	private static Map<String, Integer> ids = new HashMap<>();
	
	public static void addAdminGroup(String name, AdminGroup adminGroup){
		JSONObject jsonAdmin = new JSONObject();
		try {
			jsonAdmin.put("adminGroupName", adminGroup.getAdminGroupName());
			jsonAdmin.put("active", adminGroup.isActive());


		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String inputURL = SERVER_ADDRESS + ADMIN_URL;
		String response = Server.HTTPPostMethod(inputURL, jsonAdmin);
		try {
			JSONObject json = new JSONObject(response);
			ids.put(name, json.getInt("adminGroupId"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public static Map<String, Integer> createAdminGroups(){
		
		AdminGroup technology = new AdminGroup();
		technology.setAdminGroupName(DeloitteServiceLine.technology);
		addAdminGroup("ag_tech", technology);
		
		AdminGroup risk = new AdminGroup();
		risk.setAdminGroupName(DeloitteServiceLine.risk);
		addAdminGroup("ag_risk", risk);
		
		AdminGroup strategy = new AdminGroup();
		strategy.setAdminGroupName(DeloitteServiceLine.strategy);
		addAdminGroup("ag_strategy", strategy);

		return ids;

	}

}
