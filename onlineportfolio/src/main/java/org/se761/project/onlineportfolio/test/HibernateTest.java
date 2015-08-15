package org.se761.project.onlineportfolio.test;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.se761.project.onlineportfolio.model.Account;
import org.se761.project.onlineportfolio.model.AdminGroup;
import org.se761.project.onlineportfolio.model.Qualification;
import org.se761.project.onlineportfolio.service.AccountService;
import org.se761.project.onlineportfolio.service.AdminGroupService;
import org.se761.project.onlineportfolio.service.QualificationService;

public class HibernateTest {

	public static void main(String[] args){
		
		
		
		AccountService accountService = new AccountService();
		AdminGroupService adminGroupService = new AdminGroupService();
		QualificationService qualService = new QualificationService();
		
		Qualification qual = new Qualification();
		qual.setClientName("Client 3");
		qual.setProblemStatement("This is a problem 3");
		qual.setProjectName("This is a project 3");
		qual.setRelevanceToClient("This is relevant 3");
		
		
		
		

		
//		Account dheeraj = new Account();
//		dheeraj.setAdmin(true);
//		dheeraj.setUserName("dgop");
//		dheeraj.setPin("1234");
//		accountService.addAccountDetails(dheeraj);
//		
//		AdminGroup group = new AdminGroup();
//		group.setAdminGroupName("Audit");
//		adminGroupService.addAdminGroup(group);
//		accountService.addAccountToAdminGroup(8, 6);
		
		
		
		
	}
	
	
}
