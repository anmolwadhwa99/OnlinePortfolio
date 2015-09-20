package org.se761.project.onlineportfolio.service;

import java.util.List;

import org.se761.project.onlineportfolio.database.QualificationDatabase;
import org.se761.project.onlineportfolio.model.Image;
import org.se761.project.onlineportfolio.model.Qualification;


public class QualificationService {
	
	private QualificationDatabase qualDatabase = new QualificationDatabase();
	
	/**
	 * Getting a particular qualification 
	 */
	public Qualification getQual(int qualId){
		Qualification qual = qualDatabase.getQual(qualId);
		return qual;
	}
	
	
	/**
	 * Gets all the qualifications 
	 */
	public List<Qualification> getAllQuals(){
		List<Qualification> quals = qualDatabase.getAllQuals();
		return quals;
	}
	
	
	/**
	 * Get all qualifications associated with an admin group
	 */
	public List<Qualification> getAllQualificationsFromAdminGroup(int adminGroupId){
		List<Qualification> quals = qualDatabase.getAllQualificationsForAdminGroup(adminGroupId);
		return quals;
	}
	
	/**
	 * Get all qualifications associated with a project group
	 */
	public List<Qualification> getAllQualificationsFromProjectGroup(int projectGroupId){
		List<Qualification> quals = qualDatabase.getAllQualificationsForProjectGroup(projectGroupId);
		return quals;
	}
	
	/**
	 * Get all qualifications associated with an account
	 */
	public List<Qualification> getAllQualificationsFromAccount(int accountId){
		List<Qualification> quals = qualDatabase.getAllQualificationsForAccount(accountId);
		return quals;
	}
	
	/**
	 * Adding a qualification 
	 */
	public void addQual(Qualification qual){
		qualDatabase.addQual(qual);
	}
	
	/**
	 * Add qualification against an admin group
	 */
	public Qualification addQualificationToAdminGroup(int adminGroupId, int qualId){
		Qualification qualification = qualDatabase.addQualificationToAdminGroup(adminGroupId, qualId);
		return qualification;
	}
	
	/**
	 * Add qualification against a project group
	 */
	public Qualification addQualificationToProjectGroup(int projectGroupId, int qualId){
		Qualification qualification = qualDatabase.addQualificationToProjectGroup(projectGroupId, qualId);
		return qualification;
	}
	
	
	/**
	 * Delete a qualification 
	 */
	public Qualification deleteQual(int qualId){
		Qualification qual = qualDatabase.deleteQual(qualId);
		return qual;
	}
	
	/**
	 * Edit a qualification
	 */
	public Qualification editQual(Qualification qual){
		Qualification modifiedQual = qualDatabase.editQualification(qual);
		return modifiedQual;
	}

	/**
	 * Reactivate qualification
	 */
	public Qualification reactivateQual(int qualId){
		return qualDatabase.reactivateQual(qualId);
	}
	
	/**
	 * Add qualification against account
	 */
	public Qualification addQualificationToAccount(int accountId, int qualId){
		return qualDatabase.addQualificationToAccount(accountId, qualId);
	}
}
