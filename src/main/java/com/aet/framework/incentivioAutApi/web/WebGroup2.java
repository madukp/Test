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

public class WebGroup2 {
	
	final String propertyFileName = "web_access.properties";
	
HttpURLConnection connection = null;
	
	public String createGroup(String webAccessToken, String clientId, String merchantId, String itemId ) {
		
		String groupId = setGroupInfo(webAccessToken, clientId, merchantId, itemId);
		return groupId;
	}
	
	
public String createGroup_forCatalog(String webAccessToken, String clientId, String merchantId, String itemId , String[] subgroupId , String[] optiongroupId) {
		
		
	String subgroupIds="";
	String optiongroupIds="";
	int subGroupSize = Integer.parseInt(Utilities.getsubGroupSize());
	int optionGroupSize = Integer.parseInt(Utilities.getsubGroupSize());
	
	
	
	
	for(int i=0;i<subGroupSize;i++)
	{
		if(i<1)
		subgroupIds = "" +subgroupId[i]+ "\"," ;	
		if(i==1)
			subgroupIds =  "\""+subgroupIds +"\""+subgroupId[i]+ "" ;	
	}
	subgroupIds =  "["+subgroupIds+"\"]";
	Utilities.printInfoMessage(" --- subgroupIds " + subgroupIds);
	for(int i=0;i<optionGroupSize;i++)
	{
		
		if(i<1)
			optiongroupIds = "" +optiongroupId[i]+ "\"," ;	
			if(i==1)
				optiongroupIds = "\"" +optiongroupIds + "\""+ optiongroupId[i]+ "" ;	
		}
		
	optiongroupIds =  "["+optiongroupIds+"\"]";
	Utilities.printInfoMessage(" --- optiongroupIds " + optiongroupIds);
	
	String mainGroupId = setGroupInfo_withsubGroups(webAccessToken, clientId, merchantId, itemId,subgroupIds,optiongroupIds);
		return mainGroupId;
		
}
	
	
	
	
	
	private String setGroupInfo_withsubGroups(String webAccessToken, String clientId, String merchantId, String itemId,
		String subgroupIds, String optiongroupIds) {
		try{
		String baseUrl = Utilities.getDomain() + "incentivio-admin-api/incentivio-ordering-service/groups";
		
		URL url = new URL(baseUrl);
		connection = (HttpURLConnection) url.openConnection();

		connection.setRequestMethod(Constants.PUT); 
		connection.setRequestProperty(Constants.ContentType, "application/json");

		connection.setRequestProperty(Constants.Authorization, "Bearer " + webAccessToken);
		
		connection.setRequestProperty(Constants.Accept, PropertyFile.readProperty("AcceptValue", propertyFileName));
		connection.setUseCaches(false);
		connection.setDoOutput(true);
		
		
		//Request
		
		String groupTitle = "AUT GRP " + Utilities.getCurrentDate();
		String groupGroupType = Utilities.getgroupType();
		String groupStartDate =  Utilities.getCurrentDate();
		//End date is set for 10 days after the start date
		String groupEndDate = Utilities.getFutureDate(10);
		String groupShortDescription = "This is the short description for the group language";
		String groupLongDescription = "This is the detailed description for the group language";
		String groupDisplayRank = "1";
		String groupDisplayStatus ="Inactive";
		String groupDispalyTaxable = "true";		
		String groupExternalId = Utilities.getgroupExternalId();
	
		
					
		String jsonBody ="{\""+Constants.title+"\":\""+ groupTitle + "\",\""+Constants.groupType+"\":\""+groupGroupType+"\",\""+Constants.clientId+"\":\""+clientId+ "\",\""+Constants.startDate+"\":\""+groupStartDate+"\",\""+Constants.endDate+"\":\""+groupEndDate+"\",\""+Constants.merchantId+"\":\""+merchantId+"\",\""+Constants.groupExternalId+"\":\""+groupExternalId+"\",\""+Constants.minItemSelections+"\":0,\""+Constants.maxItemSelections+"\":0"+",\""+Constants.displayRank+"\":"+groupDisplayRank+",\""+Constants.status+"\":\""+groupDisplayStatus+"\",\""+Constants.displayInfo+"\":[{\""+Constants.title+"\":\"english\",\""+Constants.shortDescription+"\":\""+groupShortDescription+"\",\""+Constants.longDescription+"\":\""+groupLongDescription+"\",\""+Constants.smallImage+"\":[\"https://s3.amazonaws.com/incentdev/94cf98f2-8514-40c0-bfb6-c04a52e32714/i/qa_logo_sm_81975891b4a94a39a65ca32ddd8271d1.jpg\"],\""+Constants.mediumImage+"\":[],\""+Constants.langCode+"\":\"EN\"}],\""+Constants.extendedAttributes+"\":{\"ExKey\":\"1\"},\""+Constants.privateAttributes+"\":{\"PvtKey\":\"1\"}" +",\""+ Constants.itemIds+"\":[\""+itemId+"\"],\""+Constants.defaultSelectedItemIds+"\":[],\""+Constants.mandatoryItemIds+"\":[],\""+Constants.applicableOrderTypes+"\":[\"PICKUP\",\"DELIVERY\"],\""+Constants.subGroups+"\":"+subgroupIds+",\""+Constants.optionGroups+"\":"+optiongroupIds+"}";
		Utilities.printInfoMessage(" --- GROUP WITH SUB AND OPTIONAL body " + jsonBody);	
		
		
		
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
			Utilities.printInfoMessage(" --- Created Group Title : " + groupTitle);
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


	public String setGroupInfo(String webAccessToken, String clientId, String merchantId, String itemId){
		try{
			
			String baseUrl = Utilities.getDomain() + "incentivio-admin-api/incentivio-ordering-service/groups";
			
			URL url = new URL(baseUrl);
			connection = (HttpURLConnection) url.openConnection();

			connection.setRequestMethod(Constants.PUT); 
			connection.setRequestProperty(Constants.ContentType, "application/json");

			connection.setRequestProperty(Constants.Authorization, "Bearer " + webAccessToken);
			
			connection.setRequestProperty(Constants.Accept, PropertyFile.readProperty("AcceptValue", propertyFileName));
			connection.setUseCaches(false);
			connection.setDoOutput(true);
			
			
			//Request
			
			String groupTitle = "AUT GRP " + Utilities.getCurrentDate();
			String groupGroupType = Utilities.getgroupType();
			String groupStartDate =  Utilities.getCurrentDate();
			//End date is set for 10 days after the start date
			String groupEndDate = Utilities.getFutureDate(10);
			String groupShortDescription = "This is the short description for the group language";
			String groupLongDescription = "This is the detailed description for the group language";
			String groupDisplayRank = "1";
			String groupDisplayStatus ="Inactive";
			String groupDispalyTaxable = "true";
			String grouopSubGroups = Utilities.getsubGroups();
			String groupExternalId = Utilities.getgroupExternalId();
			String groupOptionGroups= Utilities.getOptionGroups();
			
			 
			String jsonString = JsonMapper.createGroupRequest(groupTitle,groupGroupType,clientId,groupStartDate,groupEndDate,merchantId,groupExternalId);
			
			
			String jsonBody ="{"+""+jsonString+",\""+Constants.title+"\":\""+ groupTitle + "\",\""+Constants.groupType+"\":\""+groupGroupType+"\",\""+Constants.clientId+"\":\""+clientId+ "\",\""+Constants.startDate+"\":\""+groupStartDate+"\",\""+Constants.endDate+"\":\""+groupEndDate+"\",\""+Constants.merchantId+"\":\""+merchantId+"\",\""+Constants.groupExternalId+"\":\""+groupExternalId+"\",\""+Constants.minItemSelections+"\":0,\""+Constants.maxItemSelections+"\":0"+",\""+Constants.displayRank+"\":"+groupDisplayRank+",\""+Constants.status+"\":\""+groupDisplayStatus+"\",\""+Constants.displayInfo+"\":[{\""+Constants.title+"\":\"english\",\""+Constants.shortDescription+"\":\""+groupShortDescription+"\",\""+Constants.longDescription+"\":\""+groupLongDescription+"\",\""+Constants.smallImage+"\":[\"https://s3.amazonaws.com/incentdev/94cf98f2-8514-40c0-bfb6-c04a52e32714/i/qa_logo_sm_81975891b4a94a39a65ca32ddd8271d1.jpg\"],\""+Constants.mediumImage+"\":[],\""+Constants.langCode+"\":\"EN\"}],\""+Constants.extendedAttributes+"\":{\"ExKey\":\"1\"},\""+Constants.privateAttributes+"\":{\"PvtKey\":\"1\"}" +",\""+ Constants.itemIds+"\":[\""+itemId+"\"],\""+Constants.defaultSelectedItemIds+"\":[],\""+Constants.mandatoryItemIds+"\":[],\""+Constants.applicableOrderTypes+"\":[\"PICKUP\",\"DELIVERY\"]}";
			Utilities.printInfoMessage(" --- body " + jsonBody);
						
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
				Utilities.printInfoMessage(" --- Created Group Title : " + groupTitle);
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
	public void deleteGroup(String webAccessToken, String clientId, String merchantId, String itemId, String groupId) {
		{
		try {
			
			String baseUrl = Utilities.getDomain() + "incentivio-admin-api/incentivio-ordering-service/groups/" + groupId;
			

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

		Utilities.printInfoMessage(" --- Deleted Group ");

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
