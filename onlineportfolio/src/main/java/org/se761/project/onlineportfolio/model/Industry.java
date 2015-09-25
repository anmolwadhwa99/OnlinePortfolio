package org.se761.project.onlineportfolio.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
public class Industry {
	
	public enum IndustryName{
		consumerBusiness, energyAndResources, financialServices, lifeScienceAndHealthCare, 
		manufacturing, primary, publicSector, realEstate, technologyMediaAndTelecommunications
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int industryId;
	private IndustryName industryName;
	
	public int getIndustryId() {
		return industryId;
	}
	
	public void setIndustryId(int industryId) {
		this.industryId = industryId;
	}
	
	public IndustryName getIndustryName() {
		return industryName;
	}
	
	public void setIndustryName(IndustryName industryName) {
		this.industryName = industryName;
	}
	
}
