package com.aet.framework.incentivioAutApi.web;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.aet.framework.incentivioAutApi.utilities.Constants;
import com.aet.framework.incentivioAutApi.utilities.JsonMapper;
import com.aet.framework.incentivioAutApi.utilities.PropertyFile;
import com.aet.framework.incentivioAutApi.utilities.Utilities;

public class WebItem {
	final String propertyFileName = "web_access.properties";
	
	HttpURLConnection connection = null;
	
	public String createItem(String webAccessToken, String clientId, String merchantId ) {
		
		String itemId = setItemInfo(webAccessToken, clientId, merchantId);
		//setDisplayInfo(webAccessToken, clientId, itemId);
		return itemId;
	}
	
	private String setItemInfo(String webAccessToken, String clientId, String merchantId){
		
try{
			
			String baseUrl = Utilities.getDomain() + "incentivio-admin-api/incentivio-ordering-service/items";
			
			URL url = new URL(baseUrl);
			connection = (HttpURLConnection) url.openConnection();

			connection.setRequestMethod(Constants.PUT); 
			connection.setRequestProperty(Constants.ContentType, "application/json");

			connection.setRequestProperty(Constants.Authorization, "Bearer " + webAccessToken);
			
			connection.setRequestProperty(Constants.Accept, PropertyFile.readProperty("AcceptValue", propertyFileName));
			connection.setUseCaches(false);
			connection.setDoOutput(true);
			
			// Request
						

			String itemTitle = "AUT ITM " + Utilities.getCurrentDate();
			String itemOptionGroups= Utilities.getOptionGroups();
			String itemBrandId = Utilities.getBrandId();
			String itemCategoryId = Utilities.getCategoryId();
			String itemExternalId = Utilities.getExternalId();
			String itemStartDate =  Utilities.getCurrentDate();
			String itemPrice = Utilities.getPrice();
			
			//End date is set for 10 days after the start date
			String itemEndDate = Utilities.getFutureDate(10);
			
			// variable declaration for jsonMapper display info
			String itemDisplaytitle = "Language title " + Utilities.getCurrentDate();
			String itemDisplaySmallImage[]  = {"https://s3.amazonaws.com/incentdev/94cf98f2-8514-40c0-bfb6-c04a52e32714/i/qa_logo_sm_81975891b4a94a39a65ca32ddd8271d1.jpg"};
			String itemDisplayMediumImage[] = {"https://s3.amazonaws.com/incentdev/94cf98f2-8514-40c0-bfb6-c04a52e32714/i/qa_logo_sm_81975891b4a94a39a65ca32ddd8271d1.jpg"};
			String itemDisplayLangCode = "EN";
			String itemDisplayRank = "1" ;
			String itemDisplayStatus ="Active";
			String itemDispalyTaxable = "true";
						
			
			
			
			String jsonString = JsonMapper.createItemRequest(itemPrice, itemBrandId, itemCategoryId, itemTitle, clientId, merchantId, itemExternalId, itemStartDate, itemEndDate);
			
			
			String jsonBody = "{"+""+jsonString+",\""+Constants.discounts+"\":[],\""+Constants.displayInfo+"\":[{\""+Constants.title+"\":\"En\",\""+Constants.smallImage+"\":[\"https://s3.amazonaws.com/incentdev/94cf98f2-8514-40c0-bfb6-c04a52e32714/i/qa_logo_sm_81975891b4a94a39a65ca32ddd8271d1.jpg\"],\""+Constants.mediumImage+"\":[],\""+Constants.langCode+"\":\"EN\"}],\""+Constants.extendedAttributes+"\":{},\""+Constants.privateAttributes+"\":{},\""+Constants.displayRank+"\":"+itemDisplayRank+",\""+Constants.status+"\":\""+itemDisplayStatus+"\",\""+Constants.applicableOrderTypes+"\":[\"PICKUP\",\"DELIVERY\"],\""+Constants.taxable+"\":"+itemDispalyTaxable+",\""+Constants.orderingChannels+"\":[\"MOBILE\"]}";	
			
			
			
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
			Utilities.printInfoMessage(" --- Created Item Title : " + itemTitle);
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
	public void deleteItem(String webAccessToken, String clientId, String merchantId, String itemId) {
		{
		try {
			
			String baseUrl = Utilities.getDomain() + "incentivio-admin-api/incentivio-ordering-service/items/" + itemId;

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

		Utilities.printInfoMessage(" --- Deleted Item ");

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
