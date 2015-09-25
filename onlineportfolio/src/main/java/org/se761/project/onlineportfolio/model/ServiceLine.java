package org.se761.project.onlineportfolio.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class ServiceLine {
	
	public enum DeloitteServiceLine{
		audit, deloittePrivate, financialAdvisory, humanCapital, operations,
		risk, strategy, tax, technology;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int serviceId;
	private DeloitteServiceLine serviceLineName;
	
	public int getServiceId() {
		return serviceId;
	}
	
	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}
	
	public DeloitteServiceLine getServiceLineName() {
		return serviceLineName;
	}
	
	public void setServiceLineName(DeloitteServiceLine serviceLineName) {
		this.serviceLineName = serviceLineName;
	}
	
	
}
