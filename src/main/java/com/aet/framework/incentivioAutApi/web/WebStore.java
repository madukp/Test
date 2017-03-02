package com.aet.framework.incentivioAutApi.web;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.aet.framework.incentivioAutApi.utilities.Constants;
import com.aet.framework.incentivioAutApi.utilities.Utilities;

public class WebStore {
	
	HttpURLConnection connection = null;

	public String createStore(String webAccessToken, String clientId, String merchantId) {
		
		String storeId = setStoreInfo(webAccessToken, clientId, merchantId);
		return storeId;
	}
	
	private String setStoreInfo(String webAccessToken, String clientId, String merchantId) {
		try{
			
			String baseUrl = Utilities.getDomain() + "incentivio-admin-api/incentivio-client-domain/clients/" + clientId
					+ "/merchants/" + merchantId + "/stores";
			                                             
			URL url = new URL(baseUrl);
			connection = (HttpURLConnection) url.openConnection();

			connection.setRequestMethod(Constants.POST); 
			connection.setRequestProperty(Constants.ContentType, "application/json");

			connection.setRequestProperty(Constants.Authorization, "Bearer " + webAccessToken);
			connection.setRequestProperty(Constants.Accept, "application/json, text/plain, */*");

			connection.setUseCaches(false);
			connection.setDoOutput(true);
			
			// Request
			String storeTitle = "AUT STR " + Utilities.getCurrentDate();
			String storeRegionId = "d8366b64-c342-11e5-8a63-0aa9f0965c75";
			String shortDesc = "Store Description";
			String longDesc = "Detailed description";
			String storesType = "11c0be0c-efa2-11e3-b939-02cbd19e9b93";
			String storeSmalleImage ="https://s3.amazonaws.com/incentdev/94cf98f2-8514-40c0-bfb6-c04a52e32714/s/qa_logo_sm_52b3fc3ee67449118eb2739649c4729d.jpg";
			String storeStreetOne = "Main Street";
			String storeStreetTwo = "1st Lane";
			String storeCity = "Ohio";
			String storeRegion = "Iowa";
			String storePostalcode = "1214";
			String storeCountry = "United States";
			String storeRegionTitle = "IA";
			String regionShortDescription = "Iowa";
			String storephoneNumber = "777";
			
			
			
			String jsonBody= "{\"regionId\":\"" + storeRegionId + "\",\"title\":\"" + storeTitle + "\",\"shortDescription\":\"" + shortDesc + "\",\"longDescription\":\"" + longDesc + "\",\"storeType\":\"" + storesType + "\",\"smallImage\":\"" + storeSmalleImage + "\",\"address\":{\"streetAddress1\":\"" +storeStreetOne +"\",\"streetAddress2\":\"" + storeStreetTwo +"\",\"city\":\""+ storeCity +"\",\"region\":\""+ storeRegion +"\",\"postalCode\":\""+storePostalcode+"\",\"country\":\""+storeCountry+"\"},\"region\":{\"regionId\":\""+ storeRegionId +"\",\"title\":\""+storeRegionTitle+"\",\"shortDescription\":\""+regionShortDescription+"\"},\"phoneNumber\":\""+storephoneNumber+"\"}";      
			
			
			
			// request
			DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
			wr.writeBytes(jsonBody);
			wr.close();

			// response
			InputStream is = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			StringBuilder response = new StringBuilder();

			String line;
			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			rd.close();
			Utilities.printInfoMessage(" --- Created Store Title : " + storeTitle);
			String st = response.toString().split(":")[1].split("}")[0].replace("\"", "");
			System.out.println(st);
			return st;
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}

		}

	}
	
	public void deleteStore(String webAccessToken, String clientId, String merchantId, String storeId) {
		{
		try {
			
			String baseUrl = Utilities.getDomain() + "incentivio-admin-api/incentivio-client-domain/clients/" + clientId
				+ "/merchants/" + merchantId + "/stores/" + storeId;
    
			
			
		URL url = new URL(baseUrl);
		connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod(Constants.DELETE);
		connection.setRequestProperty(Constants.ContentType, "application/json");
		connection.setRequestProperty("Authorization", "Bearer " + webAccessToken);

		connection.setRequestProperty("Accept", "application/json, text/plain, */*");

		connection.setUseCaches(false);
		connection.setDoOutput(true);

		// Get Response
		InputStream is = connection.getInputStream();
		BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		StringBuilder response = new StringBuilder();

		String line;
		while ((line = rd.readLine()) != null) {
			response.append(line);
			response.append('\r');
		}
		rd.close();

		Utilities.printInfoMessage(" --- Deleted Store ");

	} catch (Exception e) {
		e.printStackTrace();
		// return null;
	} finally {
		if (connection != null) { 
			connection.disconnect();
		}
	}
}
}
}
