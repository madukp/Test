package com.aet.framework.incentivioAutApi.utilities;

public final class Constants {
	// --- request method types
	public static String POST = "POST";
	public static String GET = "GET";
	public static String PUT = "PUT";
	public static String DELETE = "DELETE";

	// -- message types
	public static String PRIVATE = "PRIVATE";
	public static String SHAREABLE = "POST";
	public static String REFFERAL = "POST";

	// -- request properties
	public static String ContentType = "Content-Type";
	public static String Authorization = "Authorization";
	public static String Accept = "Accept";

	// --- JsonMapper
	public static String title = "title";
	public static String adminId = "adminId";
	public static String programId = "programId";
	public static String campaignId = "campaignId";
	public static String messageType = "messageType";

	public static String languageCode = "languageCode";
	public static String shortDescription = "shortDescription";
	public static String longDescription = "longDescription";
	public static String smallImage = "smallImage";
	public static String mediumImage = "mediumImage";

	// --- resource file names

	public static String FILE_BASE_URLS = "base_url.properties";
	public static String FILE_WEB_CREDENTIALS = "web_access.properties";
	public static String FILE_MOB_CREDENTAILS = "mob_access.properties";
	
	//-----JsonMapper for items
	
	public static String discounts = "discounts";
	public static String clientId = "clientId";
	public static String itemExternalId = "externalId";
	public static String merchantId = "merchantId";
	public static String brandId = "brandId";
	public static String categoryId = "categoryId";
	public static String langCode = "langCode";
	public static String optionGroups ="optionGroups";
	public static String price ="price";
	public static String startDate = "startDate";
	public static String endDate = "endDate";
	public static String displayInfo = "displayInfo";
	public static String extendedAttributes = "extendedAttributes";
	public static String privateAttributes = "privateAttributes";
	public static String displayRank ="displayRank";
	public static String status ="status";
	public static String applicableOrderTypes = "applicableOrderTypes";
	public static String taxable ="taxable";
	public static String orderingChannels = "orderingChannels";		
	
	//------------Web Group
	
	public static String groupType = "groupType";
	public static String itemIds = "itemIds";
	public static String subGroups ="subGroups";
	public static String defaultSelectedItemIds ="defaultSelectedItemIds";
	public static String mandatoryItemIds= "mandatoryItemIds";
	public static String maxItemSelections = "maxItemSelections";
	public static String minItemSelections = "minItemSelections";
	public static String groupExternalId = "externalId";
	
	//-----------Web Catalog
	public static String storeIds = "storeIds";
	public static String groupIds = "groupIds";
	public static String catalogexternalId = "externalId";
}
