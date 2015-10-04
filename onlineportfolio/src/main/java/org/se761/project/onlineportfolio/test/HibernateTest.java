package org.se761.project.onlineportfolio.test;


import java.util.List;

import org.se761.project.onlineportfolio.database.ImageDatabase;
import org.se761.project.onlineportfolio.model.Image;
import org.se761.project.onlineportfolio.model.Qualification;
import org.se761.project.onlineportfolio.service.AccountService;
import org.se761.project.onlineportfolio.service.AdminGroupService;
import org.se761.project.onlineportfolio.service.ProjectGroupService;
import org.se761.project.onlineportfolio.service.QualificationService;

public class HibernateTest {

	public static void main(String[] args){
		
		
		
		AccountService accountService = new AccountService();
		AdminGroupService adminGroupService = new AdminGroupService();
		QualificationService qualService = new QualificationService();
		ProjectGroupService projectService = new ProjectGroupService();
		ImageDatabase imageDatabase = new ImageDatabase();
		
		Qualification qual = new Qualification();
		qual.setClientName("Client 3");
		qual.setProblemStatement("This is a problem 3");
		qual.setProjectName("This is a project 3");
		qual.setRelevanceToClient("This is relevant 3");
//		
		qualService.addQual(qual);
//		qualService.deleteQual(1);
//		
//		Image image = new Image();
//		image.setImageName("Trial");
//		image.setImageType(ImageType.CLIENT);
//		image.setImageUrl("http://www.google.co.nz");
//		imageDatabase.addImage(image);
//		Image image = imageDatabase.getImage(1);
//		System.out.println(image.getImageId() + " " + image.getImageUrl());

//		imageDatabase.addImageToQualification(1, 1);
		
		
		//imageDatabase.deleteImage(1);
		
//		Account dheeraj = new Account();
//		dheeraj.setAdmin(true);
//		dheeraj.setAccountName("dgop");
//		dheeraj.setPassword("1234");
		
//		ProjectGroup pg = new ProjectGroup();
//		pg.setAccentColour("blue");
//		pg.setProjGroupName("Project group test");
//		pg.setSecondaryColour("red");
//		projectService.addProjectGroup(pg);
		
//		projectService.deleteProjGroup(1);
		
//		imageDatabase.addImageToQualification(1, 1);
		
//		AdminGroup group = new AdminGroup();
//		group.setAdminGroupName("trial");
//		adminGroupService.addAdminGroup(group);
		
//		adminGroupService.deleteAdminGroup(1); 
		
//		group.setAdminGroupName("Audit");
//		adminGroupService.addAdminGroup(group);
//		accountService.addAccountToAdminGroup(8, 6);
		
		
		
		
	}
	
	
}
