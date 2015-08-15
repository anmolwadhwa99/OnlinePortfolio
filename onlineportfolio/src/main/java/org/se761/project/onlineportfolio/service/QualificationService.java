package org.se761.project.onlineportfolio.service;

import java.util.List;


import org.se761.project.onlineportfolio.database.QualificationDatabase;
import org.se761.project.onlineportfolio.model.Qualification;
import org.se761.project.onlineportfolio.model.helper.MetaData;

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
	 *Gets quals associated with an account
	 */
	public List<Qualification> getAllQualificationsFromAccount(int accountId){
		return qualDatabase.getAllQualificationsFromAccount(accountId);
	}
	
	/**
	 * Adds a qualification against an existing account
	 */
	public Qualification addQualificationToAccount(int accountId, Qualification qualification){
		Qualification qual = qualDatabase.addQualificationToAccount(accountId, qualification);
		return qual;
	}
		
	/**
	 * Adding a qualification 
	 */
	public void addQul(Qualification qual){
		qualDatabase.addQul(qual);
	}
	
	/**
	 * Add metadata against a qualification
	 */
	public MetaData addMetaData(int qualId, MetaData metaData){
		MetaData meta = qualDatabase.addMetaData(qualId, metaData);
		return meta;
	}
	
	/**
	 * Add qualification against an admin group
	 */
	public Qualification addQualificationToAdminGroup(int adminGroupId, Qualification qual){
		Qualification qualification = qualDatabase.addQualificationToAdminGroup(adminGroupId, qual);
		return qualification;
	}
	
	/**
	 * Get all qualifications associated with an admin group
	 */
	public List<Qualification> getAllQualificationsFromAdminGroup(int adminGroupId){
		List<Qualification> quals = qualDatabase.getAllQualificationsForAdminGroup(adminGroupId);
		return quals;
	}
	
	/**
	 * Delete a qualification 
	 */
	public Qualification deleteQual(int qualId){
		Qualification qual = qualDatabase.deleteQual(qualId);
		return qual;
	}
	



}
