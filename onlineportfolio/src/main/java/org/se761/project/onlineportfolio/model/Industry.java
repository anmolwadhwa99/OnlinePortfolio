package org.se761.project.onlineportfolio.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
public class Industry {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int industryId;
	private String industryName;
	
	public int getIndustryId() {
		return industryId;
	}
	
	public void setIndustryId(int industryId) {
		this.industryId = industryId;
	}
	
	public String getIndustryName() {
		return industryName;
	}
	
	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}
	
}
