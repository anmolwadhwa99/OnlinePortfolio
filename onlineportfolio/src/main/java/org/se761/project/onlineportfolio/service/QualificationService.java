package org.se761.project.onlineportfolio.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
	 * Adding a qualification 
	 */
	public void addQul(Qualification qual){
		qualDatabase.addQul(qual);
	}
	
	/**
	 * Delete a qualification 
	 */
	public Qualification deleteQual(int qualId){
		Qualification qual = qualDatabase.deleteQual(qualId);
		return qual;
	}
	
	/**
	 * Add metadata against a qualification
	 */
	public MetaData addMetaData(int qualId, MetaData metaData){
		MetaData meta = qualDatabase.addMetaData(qualId, metaData);
		return meta;
	}


}
