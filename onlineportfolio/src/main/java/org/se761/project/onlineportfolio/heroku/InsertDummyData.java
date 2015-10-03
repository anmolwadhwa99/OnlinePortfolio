package org.se761.project.onlineportfolio.heroku;

import java.util.HashMap;
import java.util.Map;

import org.se761.project.onlineportfolio.database.AccountDatabase;
import org.se761.project.onlineportfolio.database.AdminGroupDatabase;
import org.se761.project.onlineportfolio.database.ProjectGroupDatabase;
import org.se761.project.onlineportfolio.database.QualificationDatabase;


public class InsertDummyData extends Server{
	

	private static Map<String, Integer> q_ids = new HashMap<>();
	private static Map<String, Integer> ac_ids = new HashMap<>();
	private static Map<String, Integer> ag_ids = new HashMap<>();
	private static Map<String, Integer> pg_ids = new HashMap<>();

	public static void main(String[] args) {

		q_ids = QualData.createQuals();
		ac_ids = AccountData.createAccounts();
		ag_ids = AdminData.createAdminGroups();
		pg_ids = ProjectData.createProjectGroups();
		
//		
//		for (Map.Entry<String, Integer> entry : q_ids.entrySet()){
//		    System.out.println(entry.getKey() + "/" + entry.getValue());
//		}
//		
//		for (Map.Entry<String, Integer> entry : ac_ids.entrySet()){
//		    System.out.println(entry.getKey() + "/" + entry.getValue());
//		}
//		
//		for (Map.Entry<String, Integer> entry : ag_ids.entrySet()){
//		    System.out.println(entry.getKey() + "/" + entry.getValue());
//		}
//		
//		for (Map.Entry<String, Integer> entry : pg_ids.entrySet()){
//		    System.out.println(entry.getKey() + "/" + entry.getValue());
//		}
		
		createLinks();
	}


	private static void createLinks(){
		int proj_EA = pg_ids.get("proj_EA");
		int proj_Apple = pg_ids.get("proj_Apple");
		int proj_Avg = pg_ids.get("proj_Avg");
		int proj_Gas = pg_ids.get("proj_Gas");

		int qual_EA = q_ids.get("qual_EA");
		int qual_Apple = q_ids.get("qual_Apple");
		int qual_avg = q_ids.get("qual_avg");
		int qual_gas = q_ids.get("qual_gas");

		int ac_Super = ac_ids.get("ac_Super");
		int ac_TechAdmin = ac_ids.get("ac_TechAdmin");
		int ac_StratAdmin = ac_ids.get("ac_StratAdmin");
		int ac_Client = ac_ids.get("ac_Client");

		int ag_tech = ag_ids.get("ag_tech");
		int ag_risk = ag_ids.get("ag_risk");
		int ag_strategy = ag_ids.get("ag_strategy");

		QualificationDatabase qDB = new QualificationDatabase();
		AccountDatabase aDB = new AccountDatabase();
		AdminGroupDatabase agDB = new AdminGroupDatabase();
		ProjectGroupDatabase pgDB = new ProjectGroupDatabase();

		//Assign Quals to Projects-------------------------
		qDB.addQualificationToProjectGroup(proj_EA, qual_EA);
		qDB.addQualificationToProjectGroup(proj_Apple, qual_Apple);
		qDB.addQualificationToProjectGroup(proj_Avg, qual_avg);
		qDB.addQualificationToProjectGroup(proj_Gas, qual_gas);

		//Assign Quals to AdminGroup-----------------------
		qDB.addQualificationToAdminGroup(ag_tech, qual_Apple);
		qDB.addQualificationToAdminGroup(ag_tech, qual_avg);

		qDB.addQualificationToAdminGroup(ag_strategy, qual_EA);
		qDB.addQualificationToAdminGroup(ag_strategy, qual_gas);

		//Assign Quals to Client---------------------------
		qDB.addQualificationToAccount(ac_Client, qual_EA);
		qDB.addQualificationToAccount(ac_Client, qual_Apple);

		//Assign Account to AdminGroup---------------------
		aDB.addAccountToAdminGroup(ag_tech, ac_TechAdmin);
		aDB.addAccountToAdminGroup(ag_strategy, ac_StratAdmin);

		//Assign Project to AdminGroup---------------------
		pgDB.addProjectGroupToAdmin(ag_tech, proj_Avg);
		pgDB.addProjectGroupToAdmin(ag_tech, proj_Apple);

		pgDB.addProjectGroupToAdmin(ag_strategy, proj_EA);
		pgDB.addProjectGroupToAdmin(ag_strategy, proj_Gas);
	}

}
