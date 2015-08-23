package org.se761.project.onlineportfolio.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class ProjectGroup {
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int projGroupId; 
	private String projGroupName;
	
	public int getProjGroupId() {
		return projGroupId;
	}
	
	public void setProjGroupId(int projGroupId) {
		this.projGroupId = projGroupId;
	}
	
	public String getProjGroupName() {
		return projGroupName;
	}
	
	public void setProjGroupName(String projGroupName) {
		this.projGroupName = projGroupName;
	}
	

}
