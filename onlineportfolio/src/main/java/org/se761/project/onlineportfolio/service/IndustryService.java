package org.se761.project.onlineportfolio.service;

import java.util.List;

import org.se761.project.onlineportfolio.database.IndustryDatabase;
import org.se761.project.onlineportfolio.model.Industry;

public class IndustryService {
	
	private IndustryDatabase industryDatabase = new IndustryDatabase();
	/**
	 * Get all industries
	 */
	public List<Industry> getAllIndustries(){
		return industryDatabase.getAllIndustries();
	}
	
	/**
	 * Add an industry
	 */
	public Industry addIndustry(Industry industry){
		return industryDatabase.addIndustry(industry);
	}
	
}
