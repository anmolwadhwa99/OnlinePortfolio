package org.se761.project.onlineportfolio.heroku;



import org.json.JSONException;
import org.json.JSONObject;
import org.se761.project.onlineportfolio.model.Image;

public class ImageData extends Server{
	
	public void addImage(Image image){
		JSONObject jsonImage = new JSONObject();
		try {
			jsonImage.put("imageUrl", image.getImageUrl());
			jsonImage.put("imageType", image.getImageType());
			jsonImage.put("publicId", image.getPublicId());
			jsonImage.put("isActive", image.isActive());


		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String inputURL = SERVER_ADDRESS + IMAGE_URL;
		Server.HTTPPostMethod(inputURL, jsonImage);
	}

	public static void main(String[] args) {
		ImageData imageData = new ImageData();
		Image image = new Image();
		imageData.addImage(image);

	}

}
