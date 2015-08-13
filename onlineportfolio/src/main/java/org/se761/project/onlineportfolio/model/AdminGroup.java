package org.se761.project.onlineportfolio.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
public class AdminGroup {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int adminGroupId;
	private String adminGroupName;
	
	@OneToMany(mappedBy = "adminGroup")
	private List<Qualification> quals = new ArrayList<Qualification>();
	
	public AdminGroup(){
		
	}
	
	public AdminGroup(int adminGroupId, String adminGroupName) {
		super();
		this.adminGroupId = adminGroupId;
		this.adminGroupName = adminGroupName;
	}

	public int getAdminGroupId() {
		return adminGroupId;
	}

	public void setAdminGroupId(int adminGroupId) {
		this.adminGroupId = adminGroupId;
	}

	public String getAdminGroupName() {
		return adminGroupName;
	}

	public void setAdminGroupName(String adminGroupName) {
		this.adminGroupName = adminGroupName;
	}
	
	
	
	

}
