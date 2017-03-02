package com.aet.framework.incentivioAutApi.utilities;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonMapper {
	public static String createMessageInfoRequest(String randomMessageName, String adminID, String programId,
			String campaignId, String messageType) throws JsonProcessingException {

		Map<String, String> jsonRequestDataMap = new LinkedHashMap<String, String>();
		jsonRequestDataMap.put(Constants.title, randomMessageName);
		jsonRequestDataMap.put(Constants.adminId, adminID);
		jsonRequestDataMap.put(Constants.programId, programId);
		jsonRequestDataMap.put(Constants.campaignId, campaignId);
		jsonRequestDataMap.put(Constants.messageType, messageType);

		String jsonString = new ObjectMapper().writeValueAsString(jsonRequestDataMap);
		return jsonString;
	}

	public static String createDispalyInfoRequest(String languageCode, String randomMessageName,
			String shortDescription, String longDescription, String smallImage, String mediumImage)
			throws JsonProcessingException {
		Map<String, String> jsonRequestDataMap = new LinkedHashMap<String, String>();
		jsonRequestDataMap.put(Constants.languageCode, languageCode);
		jsonRequestDataMap.put(Constants.title, randomMessageName);
		jsonRequestDataMap.put(Constants.shortDescription, shortDescription);
		jsonRequestDataMap.put(Constants.longDescription, longDescription);
		jsonRequestDataMap.put(Constants.smallImage, smallImage);
		jsonRequestDataMap.put(Constants.mediumImage, mediumImage);

		String jsonString = "{\"displayInfo\":[" + new ObjectMapper().writeValueAsString(jsonRequestDataMap) + "]}";
		return jsonString;
	}

	public static String createDistributionInfoRequest() {
		return null;
	}

	public static String createItemInfoRequest(String itemDisplaytitle, String itemDisplaySmallImage, String itemDisplayMediumImage, String itemDisplayLangCode) 
	throws JsonProcessingException {
		
		
		Map<String, String> jsonRequestDataMap = new LinkedHashMap<String, String>();
		jsonRequestDataMap.put(Constants.title, itemDisplaytitle);
		jsonRequestDataMap.put(Constants.smallImage, itemDisplaySmallImage);
		jsonRequestDataMap.put(Constants.mediumImage, itemDisplayMediumImage);
		jsonRequestDataMap.put(Constants.langCode, itemDisplayLangCode);
		

		String jsonString = new ObjectMapper().writeValueAsString(jsonRequestDataMap) ;
		return jsonString;
		
	}

	public static String createItemInfoRequest(String itemDisplaytitle, String[] itemDisplaySmallImage,
			String[] itemDisplayMediumImage, String itemDisplayLangCode) throws JsonProcessingException{
		// TODO Auto-generated method stub
		return null;
	}

	public static String createItemRequest(String itemPrice, String itembrandId,String itemCategoryId,String itemTitle, String clientId, String merchantId, String itemExternalId, String itemStartDate, String itemEndDate ) 
			throws JsonProcessingException{
		
		
		Map<String, String> jsonRequestDataMap = new LinkedHashMap<String, String>();
		jsonRequestDataMap.put(Constants.price, itemPrice);
		jsonRequestDataMap.put(Constants.brandId, itembrandId);
		jsonRequestDataMap.put(Constants.categoryId, itemCategoryId);
		jsonRequestDataMap.put(Constants.title, itemTitle);
		jsonRequestDataMap.put(Constants.clientId, clientId);
		jsonRequestDataMap.put(Constants.merchantId, merchantId);
		jsonRequestDataMap.put(Constants.itemExternalId, itemExternalId);
		jsonRequestDataMap.put(Constants.startDate, itemStartDate);
		jsonRequestDataMap.put(Constants.endDate, itemEndDate);
		
		String jsonString = new ObjectMapper().writeValueAsString(jsonRequestDataMap) ;
		jsonString = jsonString.replace("{", "").replace("}", "");
		return jsonString;
	}
	

	public static String createGroupRequest(String groupTitle, String groupGroupType,String clientId,String groupStartDate, String groupEndDate, String merchantId, String groupExternalId) 
			throws JsonProcessingException{
		

		
		Map<String, String> jsonRequestDataMap = new LinkedHashMap<String, String>();
		jsonRequestDataMap.put(Constants.title, groupTitle);
		jsonRequestDataMap.put(Constants.groupType, groupGroupType);
		jsonRequestDataMap.put(Constants.clientId, clientId);
		jsonRequestDataMap.put(Constants.startDate, groupStartDate);
		jsonRequestDataMap.put(Constants.endDate, groupEndDate);		
		jsonRequestDataMap.put(Constants.merchantId, merchantId);
		jsonRequestDataMap.put(Constants.groupExternalId, groupExternalId);
				
		
		String jsonString = new ObjectMapper().writeValueAsString(jsonRequestDataMap) ;
		jsonString = jsonString.replace("{", "").replace("}", "");
		return jsonString;
	}
	
	
	public static String createCatalogRequest(String catalogTitle, String clientId,String catalogDisplayStatus,String catalogStartDate, String catalogEndDate, String merchantId) 
			throws JsonProcessingException{
		
		
		Map<String, String> jsonRequestDataMap = new LinkedHashMap<String, String>();
		jsonRequestDataMap.put(Constants.title, catalogTitle);
		jsonRequestDataMap.put(Constants.clientId, clientId);
		jsonRequestDataMap.put(Constants.status, catalogDisplayStatus);
		jsonRequestDataMap.put(Constants.startDate, catalogStartDate);
		jsonRequestDataMap.put(Constants.endDate, catalogEndDate);		
		jsonRequestDataMap.put(Constants.merchantId, merchantId);
		//jsonRequestDataMap.put(Constants.externalId, catalogExternalID);
						
		
		String jsonString = new ObjectMapper().writeValueAsString(jsonRequestDataMap) ;
		jsonString = jsonString.replace("{", "").replace("}", "");
		return jsonString;
	}
	
	
	
	
	
}
