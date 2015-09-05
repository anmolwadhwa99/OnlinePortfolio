package org.se761.project.onlineportfolio.heroku;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Server {
	
    private final static String SERVER_ADDRESS = "https://onlineportfolio.herokuapp.com/webapi/";
    private final static String QUALS_URL = "qual/";
    private final static String ACCOUNT_URL = "account/";
    private final static String ADMIN_URL = "admin/";
    private final static String PROJECT_URL = "projectGroup/";
    private final static String IMAGE_URL = "image/";
    
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

}
