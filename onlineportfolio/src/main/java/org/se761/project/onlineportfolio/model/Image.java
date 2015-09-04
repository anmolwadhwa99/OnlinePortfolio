package org.se761.project.onlineportfolio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

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
	
	public Image(int imageId, String imageUrl, ImageType imageType,
			String imageName) {
		this.imageId = imageId;
		this.imageUrl = imageUrl;
		this.imageType = imageType;
		this.imageName = imageName;
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
	
}


