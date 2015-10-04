package org.se761.project.onlineportfolio.heroku;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

public class Server {
	/*"https://onlineportfolio.herokuapp.com/webapi/";//*/

	public final static String SERVER_ADDRESS = "http://onlineportfolio.herokuapp.com/webapi/";
	public final static String QUALS_URL = "qual/";
	public final static String ACCOUNT_URL = "account/";
	public final static String ADMIN_URL = "admin/";
	public final static String PROJECT_URL = "projectGroup/";
	public final static String IMAGE_URL = "image/";

	public final static String dummyEmail = "thus056@aucklanduni.ac.nz";

	public Server(){

	}

	public static String HTTPGetMethod(String inputURL){

		try {

			URL url = new URL(inputURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));


			String output = br.readLine();
			conn.disconnect();
			return output;

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
		return null;
	}

	public static String HTTPDeleteMethod(String inputURL){

		try {

			URL url = new URL(inputURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("DELETE");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));


			String output = br.readLine();

			conn.disconnect();
			return output;

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
		return null;
	}

	public static String HTTPPostMethod(String inputURL, JSONObject object){

		try {

			URL url = new URL(inputURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");

			if (object != null){
				String input = object.toString();           
				OutputStream os = conn.getOutputStream();
				os.write(input.getBytes());
				os.flush();
			}

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			String output = br.readLine(); 


			conn.disconnect();
			return output;

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
		return null;
	}

}
