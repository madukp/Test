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

public class WebMessages {
	String baseUrl;
	String randomMessage;
	HttpURLConnection connection = null;
	
	

	public String createMessage(String webAccessToken, String clientId, String merchantId, String programId,
			String campaignId) {
		
		String messageId;
		messageId = setMessageInfo(webAccessToken, clientId, merchantId, programId, campaignId);
		//setDisplayInfo(webAccessToken, clientId, messageId);
		//setDistributionInfo(webAccessToken, clientId, merchantId, messageId);
		//setDeleteRules(webAccessToken, clientId, merchantId, messageId);
		return messageId;
	}

	public void deleteMessage(String webAccessToken, String clientId, String merchantId, String messageID) {
		try {
			baseUrl = Utilities.getDomain() + "incentivio-admin-api/incentivio-connect-domain/clients/" + clientId
					+ "/merchants/" + merchantId + "/messages/" + messageID;

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

			Utilities.printInfoMessage(" --- Deleted Message ");

		} catch (Exception e) {
			e.printStackTrace();
			// return null;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}

	// Private Methods -------------------------------------------------
	/**
	 * @param webAccessToken
	 * @param programId
	 * @param campaignId
	 * @ReturnType String : MessageID
	 */
	private String setMessageInfo(String webAccessToken, String clientId, String merchantId, String programId,
			String campaignId) {
		try {

			baseUrl = Utilities.getDomain() + "incentivio-admin-api/incentivio-connect-domain/clients/" + clientId
					+ "/merchants/" + merchantId + "/messages2";

			URL url = new URL(baseUrl);
			System.out.println("baseUrl " + baseUrl + "\n");		
			
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod(Constants.POST); // POST
			connection.setRequestProperty(Constants.ContentType, "application/json");
			connection.setRequestProperty(Constants.Authorization, "Bearer " + webAccessToken);
			connection.setRequestProperty(Constants.Accept, "application/json, text/plain, */*");
			connection.setUseCaches(false);
			connection.setDoOutput(true);

			// Request
			randomMessage = Utilities.getCurrentDate(); // Date Time Tag

			String messageTitle = "AUT MSG " + randomMessage;
			String adminID = "e56f8041-8b38-44fc-9f21-90d19c2dd22a";
			String messageType = Constants.PRIVATE;

			String jsonString = JsonMapper.createMessageInfoRequest(messageTitle, adminID, programId, campaignId,
					messageType);

			System.out.println("Request body - setMessageInfo " + jsonString + "\n" );
			
			//BLOCK2//////////////////////////////////////////////////
			
			String distributionChannel = "INAPP";
			String distributionMode = "AUTO";
			String redistributionMode = "ONCE";
			String distributionSchedule = "";
			String startDate = Utilities.getCurrentDate();
			String endDate = Utilities.getFutureDate(30);

			String scheduleDays = "[\"MONDAY\",\"TUESDAY\",\"WEDNESDAY\",\"THURSDAY\",\"FRIDAY\",\"SATURDAY\",\"SUNDAY\"]";
			String timeSlots = "{\"startTime\":\"00:00:00\",\"endTime\":\"01:00:00\"},{\"startTime\":\"01:00:00\",\"endTime\":\"02:00:00\"},{\"startTime\":\"02:00:00\",\"endTime\":\"03:00:00\"},{\"startTime\":\"03:00:00\",\"endTime\":\"04:00:00\"},{\"startTime\":\"04:00:00\",\"endTime\":\"05:00:00\"},{\"startTime\":\"05:00:00\",\"endTime\":\"06:00:00\"},{\"startTime\":\"06:00:00\",\"endTime\":\"07:00:00\"},{\"startTime\":\"07:00:00\",\"endTime\":\"08:00:00\"},{\"startTime\":\"08:00:00\",\"endTime\":\"09:00:00\"},{\"startTime\":\"09:00:00\",\"endTime\":\"10:00:00\"},{\"startTime\":\"10:00:00\",\"endTime\":\"11:00:00\"},{\"startTime\":\"11:00:00\",\"endTime\":\"12:00:00\"},{\"startTime\":\"12:00:00\",\"endTime\":\"13:00:00\"},{\"startTime\":\"13:00:00\",\"endTime\":\"14:00:00\"},{\"startTime\":\"14:00:00\",\"endTime\":\"15:00:00\"},{\"startTime\":\"15:00:00\",\"endTime\":\"16:00:00\"},{\"startTime\":\"16:00:00\",\"endTime\":\"17:00:00\"},{\"startTime\":\"17:00:00\",\"endTime\":\"18:00:00\"},{\"startTime\":\"18:00:00\",\"endTime\":\"19:00:00\"},{\"startTime\":\"19:00:00\",\"endTime\":\"20:00:00\"},{\"startTime\":\"20:00:00\",\"endTime\":\"21:00:00\"},{\"startTime\":\"21:00:00\",\"endTime\":\"22:00:00\"},{\"startTime\":\"22:00:00\",\"endTime\":\"23:00:00\"},{\"startTime\":\"23:00:00\",\"endTime\":\"24:00:00\"}";
			String numMessagesToDistribute = "300"; // parameterized
			String distributionStartDate = startDate;
			String distributionEndDate = endDate;
			String timezone = "Asia/Colombo";
			//String distributionRules = "{"\"ruleType"\:"EXPRESSION","expressionName"\:"EVERYONE","operator"\:"EQ","expressionValue"\:true}";

			// Send request
			String body = "distributionSettings\":{\"channels\":[\"" + distributionChannel + "\"],\"distributionMode\":\"" + distributionMode
					+ "\",\"redistributionMode\":\"" + redistributionMode +"\","
					//+ \"distributionRules\":{\"ruleType\":\"EXPRESSION\",\"expressionName\":\"EVERYONE\",\"operator\":\"EQ\",\"expressionValue\":true}
					+"\"distributionRules\":{\"ruleType\":\"EXPRESSION\",\"expressionName\":\"EVERYONE\",\"operator\":\"EQ\",\"expressionValue\":true}"
					//+ "distributionRules\":{\"ruleType\":\"EXPRESSION\",\"expressionName\":\"EVERYONE\",\"operator\":\"EQ\",\"expressionValue\":true}"
					//+ "\",\"distributionRules\":{\"ruleType\":\"EXPRESSION\",\"expressionName\":\"EVERYONE\",\"operator\":\"EQ\",\"expressionValue\":true}
					+ ",\"distributionSchedule\":{\"startDate\":\"" + startDate + "\",\"endDate\":\"" + endDate
					+ "\",\"scheduleDays\":" + scheduleDays + ",\"timeSlots\":[" + timeSlots
					+ "]},\"numMessagesToDistribute\":" + numMessagesToDistribute + ",\"distributionStartDate\":\""
					+ distributionStartDate + "\",\"distributionEndDate\":\"" + distributionEndDate
					+ "\",\"timezone\":\"" + timezone + "\"}";

			jsonString = jsonString.substring(0, jsonString.length()-1) +"," + "\""+  body;
			System.out.println("Request body with setDistributionInfo " + jsonString  + "\n");
			
/*
 * use https://jsonformatter.curiousconcept.com/
 * problems in channel
 * then distributionRules - end
 * } should be in Asia/Colombo"}
 * 
 * make this and message should be ok
 * */
			
			
			
			
			
			
			
			
			///////////////////////BLOCK3///////////////////////////////////////////////
			
					
			String languageCode = "EN";						
			String shortDescription = "Short Desc " + randomMessage;
			String longDescription = "Long Desc " + randomMessage;
			String smallImage = "https://s3.amazonaws.com/incentdev/94cf98f2-8514-40c0-bfb6-c04a52e32714/m/2_1e3bef233e3f40b390046defabdbc606.jpg";
			String mediumImage = "https://s3.amazonaws.com/incentdev/94cf98f2-8514-40c0-bfb6-c04a52e32714/m/3_bb499f354ca34bd0abca235bcf009c58.png";
			String displayinfo = JsonMapper.createDispalyInfoRequest(languageCode, messageTitle, shortDescription,longDescription, smallImage, mediumImage);
						
			jsonString = jsonString.substring(0, jsonString.length()) +"," + displayinfo.substring(1, displayinfo.length());

			
			System.out.println("Request body with  setDisplayInfo " + jsonString + "\n" );
			
						
			///////////////////////BLOCK3///////////////////////////////////////////////
						
			

			
			DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
			wr.writeBytes(jsonString);
			wr.close();

			// Response
			InputStream is = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			StringBuilder response = new StringBuilder();

			String line;
			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			rd.close();

			Utilities.printInfoMessage(" --- Created Message Title : " + messageTitle);
			return response.toString().split(":")[1].split("}")[0].replace("\"", "");

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}
	

	private String setDisplayInfo(String webAccessToken, String clientId, String messageId) {

		try {
			baseUrl = Utilities.getDomain() + "incentivio-admin-api/incentivio-connect-domain/clients/" + clientId
					+ "/messages/" + messageId + "/displayinfo";

			URL url = new URL(baseUrl);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod(Constants.PUT);
			connection.setRequestProperty("Content-Type", "application/json");

			connection.setRequestProperty("Authorization", "Bearer " + webAccessToken);
			connection.setRequestProperty("Accept", "application/json, text/plain, */*");

			connection.setUseCaches(false);
			connection.setDoOutput(true);

			// Request
			String languageCode = "EN";
			String messageTitle = "Display Title " + randomMessage;
			String shortDescription = "Short Desc " + randomMessage;
			String longDescription = "Long Desc " + randomMessage;
			String smallImage = "/media/57e77a2ae4b052d3af4609b0/content";
			String mediumImage = "/media/57e77a51e4b052d3af4609b1/content";

			String jsonString = JsonMapper.createDispalyInfoRequest(languageCode, messageTitle, shortDescription,
					longDescription, smallImage, mediumImage);

			
			System.out.println("Request body - setDisplayInfo " + jsonString + "\n" );
			
			
			Utilities.printInfoMessage("The Display info " +jsonString );
			DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
			wr.writeBytes(jsonString);
			wr.close();

			// Response
			InputStream is = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			StringBuilder response = new StringBuilder();

			String line;
			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			rd.close();

			return response.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}

	private String setDistributionInfo(String webAccessToken, String clientId, String merchantId, String messageId) {
		try {

			baseUrl = Utilities.getDomain() + "incentivio-admin-api/incentivio-connect-domain/clients/" + clientId
					+ "/merchants/" + merchantId + "/messages/" + messageId + "/distributionsettings";

			URL url = new URL(baseUrl);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod(Constants.PUT);
			connection.setRequestProperty("Content-Type", "application/json");

			connection.setRequestProperty("Authorization", "Bearer " + webAccessToken);
			connection.setRequestProperty("Accept", "application/json, text/plain, */*");

			connection.setUseCaches(false);
			connection.setDoOutput(true);

			String distributionChannel = "INAPP";
			String distributionMode = "AUTO";
			String redistributionMode = "ONCE";
			String distributionSchedule = "";
			String startDate = Utilities.getCurrentDate();
			String endDate = Utilities.getFutureDate(30);

			String scheduleDays = "[\"MONDAY\",\"TUESDAY\",\"WEDNESDAY\",\"THURSDAY\",\"FRIDAY\",\"SATURDAY\",\"SUNDAY\"]";
			String timeSlots = "{\"startTime\":\"00:00:00\",\"endTime\":\"01:00:00\"},{\"startTime\":\"01:00:00\",\"endTime\":\"02:00:00\"},{\"startTime\":\"02:00:00\",\"endTime\":\"03:00:00\"},{\"startTime\":\"03:00:00\",\"endTime\":\"04:00:00\"},{\"startTime\":\"04:00:00\",\"endTime\":\"05:00:00\"},{\"startTime\":\"05:00:00\",\"endTime\":\"06:00:00\"},{\"startTime\":\"06:00:00\",\"endTime\":\"07:00:00\"},{\"startTime\":\"07:00:00\",\"endTime\":\"08:00:00\"},{\"startTime\":\"08:00:00\",\"endTime\":\"09:00:00\"},{\"startTime\":\"09:00:00\",\"endTime\":\"10:00:00\"},{\"startTime\":\"10:00:00\",\"endTime\":\"11:00:00\"},{\"startTime\":\"11:00:00\",\"endTime\":\"12:00:00\"},{\"startTime\":\"12:00:00\",\"endTime\":\"13:00:00\"},{\"startTime\":\"13:00:00\",\"endTime\":\"14:00:00\"},{\"startTime\":\"14:00:00\",\"endTime\":\"15:00:00\"},{\"startTime\":\"15:00:00\",\"endTime\":\"16:00:00\"},{\"startTime\":\"16:00:00\",\"endTime\":\"17:00:00\"},{\"startTime\":\"17:00:00\",\"endTime\":\"18:00:00\"},{\"startTime\":\"18:00:00\",\"endTime\":\"19:00:00\"},{\"startTime\":\"19:00:00\",\"endTime\":\"20:00:00\"},{\"startTime\":\"20:00:00\",\"endTime\":\"21:00:00\"},{\"startTime\":\"21:00:00\",\"endTime\":\"22:00:00\"},{\"startTime\":\"22:00:00\",\"endTime\":\"23:00:00\"},{\"startTime\":\"23:00:00\",\"endTime\":\"24:00:00\"}";
			String numMessagesToDistribute = "300"; // parameterized
			String distributionStartDate = startDate;
			String distributionEndDate = endDate;
			String timezone = "Asia/Colombo";
			String distributionRules = "{ruleType: &quot;EXPRESSION&quot;, expressionName: &quot;EVERYONE&quot;, operator: &quot;EQ&quot;, expressionValue: true}";

			// Send request
			String body = "{\"channels\":[\"" + distributionChannel + "\"],\"distributionMode\":\"" + distributionMode
					+ "\",\"redistributionMode\":\"" + redistributionMode
					+ "\",\"distributionRules\":\"" + distributionRules
					+ "\",\"distributionSchedule\":{\"startDate\":\"" + startDate + "\",\"endDate\":\"" + endDate
					+ "\",\"scheduleDays\":" + scheduleDays + ",\"timeSlots\":[" + timeSlots
					+ "]},\"numMessagesToDistribute\":" + numMessagesToDistribute + ",\"distributionStartDate\":\""
					+ distributionStartDate + "\",\"distributionEndDate\":\"" + distributionEndDate
					+ "\",\"timezone\":\"" + timezone + "\"}";

			System.out.println("Request body - setDistributionInfo " + body  + "\n");
			
			
			// HERE!! : create the data map from JsonMapper

			DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
			wr.writeBytes(body);
			wr.close();

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

			return response.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}

	}

	private String setDeleteRules(String webAccessToken, String clientId, String merchantId, String messageID) {
		{
			try {
				baseUrl = Utilities.getDomain() + "incentivio-admin-api/incentivio-connect-domain/clients/" + clientId
						+ "/merchants/" + merchantId + "/messages/" + messageID + "/deleterules";

				URL url = new URL(baseUrl);
				connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod(Constants.DELETE);
				connection.setRequestProperty("Content-Type", "application/json");

				connection.setRequestProperty("Authorization", "Bearer " + webAccessToken);
				connection.setRequestProperty("Accept", "application/json, text/plain, */*");

				connection.setUseCaches(false);
				connection.setDoOutput(true);

				// Response
				InputStream is = connection.getInputStream();
				BufferedReader rd = new BufferedReader(new InputStreamReader(is));
				StringBuilder response = new StringBuilder();
				String line;
				while ((line = rd.readLine()) != null) {
					response.append(line);
					response.append('\r');
				}
				rd.close();

				return response.toString();
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			} finally {
				if (connection != null) {
					connection.disconnect();
				}
			}
		}
	}

}
