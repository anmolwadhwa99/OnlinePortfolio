package org.se761.project.onlineportfolio.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class ServiceLine {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int serviceId;
	private String serviceLineName;
	
	public int getServiceId() {
		return serviceId;
	}
	
	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}
	
	public String getServiceLineName() {
		return serviceLineName;
	}
	
	public void setServiceLineName(String serviceLineName) {
		this.serviceLineName = serviceLineName;
	}
	
	
}
