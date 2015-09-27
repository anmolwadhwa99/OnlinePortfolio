package org.se761.project.onlineportfolio.heroku;

import org.se761.project.onlineportfolio.database.AccountDatabase;
import org.se761.project.onlineportfolio.database.AdminGroupDatabase;
import org.se761.project.onlineportfolio.database.ProjectGroupDatabase;
import org.se761.project.onlineportfolio.database.QualificationDatabase;


public class InsertDummyData extends Server{

	public static void main(String[] args) {

		QualData.createQuals();
		AccountData.createAccounts();
		AdminData.createAdminGroups();
		ProjectData.createProjectGroups();
		createLinks();
	}


	private static void createLinks(){
		int proj_EA = 1;
		int proj_Apple = 2;
		int proj_Avg = 3;
		int proj_Gas = 4;

		int qual_EA = 1;
		int qual_Apple = 2;
		int qual_avg = 3;
		int qual_gas = 4;

		int ac_Super = 1;
		int ac_TechAdmin = 2;
		int ac_StratAdmin = 3;
		int ac_Client = 4;

		int ag_tech = 1;
		int ag_risk = 2;
		int ag_strategy = 3;

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
