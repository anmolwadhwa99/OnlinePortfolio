package org.se761.project.onlineportfolio.model.helper;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.se761.project.onlineportfolio.model.Qualification;

@XmlRootElement
@Entity
public class MetaData {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int metaDataId;
	private String industry; //e.g. Financial Services, Education, etc.
	private String tag; 
	private String status; //either "open" or "confidential"
	private String deloitteServiceLine; //e.g. auditing, consulting, etc
	private String colourScheme;
	
	@OneToOne(mappedBy = "metaData", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Qualification qualification;
	
	public MetaData(){
		
	}
	
	public MetaData(String industry, String tag, String status,
			String deloitteServiceLine, String colourScheme) {
		super();
		this.industry = industry;
		this.tag = tag;
		this.status = status;
		this.deloitteServiceLine = deloitteServiceLine;
		this.colourScheme = colourScheme;
	}
	
	public String getIndustry() {
		return industry;
	}
	
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	
	public String getTag() {
		return tag;
	}
	
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getDeloitteServiceLine() {
		return deloitteServiceLine;
	}
	
	public void setDeloitteServiceLine(String deloitteServiceLine) {
		this.deloitteServiceLine = deloitteServiceLine;
	}
	
	public String getColourScheme() {
		return colourScheme;
	}
	
	public void setColourScheme(String colourScheme) {
		this.colourScheme = colourScheme;
	}

	public int getMetaDataId() {
		return metaDataId;
	}

	public void setMetaDataId(int metaDataId) {
		this.metaDataId = metaDataId;
	}
	
	@XmlTransient
	public Qualification getQualification() {
		return qualification;
	}

	public void setQualification(Qualification qualification) {
		this.qualification = qualification;
	}
	
}
