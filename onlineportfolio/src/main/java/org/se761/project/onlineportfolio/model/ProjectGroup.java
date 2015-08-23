package org.se761.project.onlineportfolio.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@XmlRootElement
public class ProjectGroup {
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int projGroupId; 
	private String projGroupName;
	
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "projGroups", cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Qualification> quals = new ArrayList<Qualification>();
	
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
