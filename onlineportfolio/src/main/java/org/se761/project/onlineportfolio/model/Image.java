package org.se761.project.onlineportfolio.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@XmlRootElement
public class Image {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int imageId;
	@Column(columnDefinition="TEXT")
	private String imageUrl;
	private ImageType imageType;
	private String imageName;
	private boolean isActive = true; //by default
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "qualImages")
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Qualification> quals = new ArrayList<Qualification>();
	
	public Image(int imageId, String imageUrl, ImageType imageType,
			String imageName) {
		this.imageId = imageId;
		this.imageUrl = imageUrl;
		this.imageType = imageType;
		this.imageName = imageName;
	}
	
	public Image(){
		
	}

	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public ImageType getImageType() {
		return imageType;
	}

	public void setImageType(ImageType imageType) {
		this.imageType = imageType;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public enum ImageType{
		CLIENT, PROJECT;
	}

	@XmlTransient
	public List<Qualification> getQuals() {
		return quals;
	}

	public void setQuals(List<Qualification> quals) {
		this.quals = quals;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
}
