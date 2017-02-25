package com.aet.framework.incentivioAutApi.web;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.aet.framework.incentivioAutApi.utilities.Constants;
import com.aet.framework.incentivioAutApi.utilities.PropertyFile;
import com.aet.framework.incentivioAutApi.utilities.Utilities;

public class WebGroup {
	
	final String propertyFileName = "web_access.properties";
	
HttpURLConnection connection = null;
	
	public String createGroup(String webAccessToken, String clientId, String merchantId, String itemId ) throws IOException {
		
		String groupId = setGroupInfo(webAccessToken, clientId, merchantId, itemId);
		return groupId;
	}
	
	public String setGroupInfo(String webAccessToken, String clientId, String merchantId, String itemId) throws IOException{
		
			
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
			String groupDisplayStatus ="Active";
			String groupDispalyTaxable = "true";
			String grouopSubGroups = Utilities.getsubGroups();
			
			
		//	 String jsonBody = "{\""+Constants.title+"\":\""+ groupTitle + "\",\""+Constants.groupType+"\":\""+groupGroupType+"\",\""+Constants.clientId+"\":\""+clientId+ "\",\""+Constants.startDate+"\":\""+groupStartDate+"\",\""+Constants.endDate+"\":\""+groupEndDate+"\",\""+Constants.displayInfo+"\":[{\""+Constants.title+"\":\"english\",\""+Constants.shortDescription+"\":\""+groupShortDescription+"\",\""+Constants.longDescription+"\":\""+groupLongDescription+"\",\""+Constants.smallImage+"\":[\"https://s3.amazonaws.com/incentdev/94cf98f2-8514-40c0-bfb6-c04a52e32714/i/qa_logo_sm_81975891b4a94a39a65ca32ddd8271d1.jpg\"],\""+Constants.mediumImage+"\":[],\""+Constants.LangCode+"\":\"EN\"}],\""+Constants.extendedAttributes+"\":{\"ExKey\":\"1\"},\""+Constants.privateAttributes+"\":{\"PvtKey\":\"1\"},\""+Constants.displayRank+"\":"+groupDisplayRank+",\""+Constants.status+"\":\""+groupDisplayStatus+"\",\""+ Constants.itemIds+"\":[\""+itemId+"\"],\""+Constants.subGroups+"\":"+grouopSubGroups+",\""+externalId":"2177240d-2472-41aa-abeb-eb88aead1255","defaultSelectedItemIds":[],"mandatoryItemIds":[],"optionGroups":["57bc8407e4b011d6f84123b9","57be338fe4b0193b815e3014"],"applicableOrderTypes":["PICKUP","DELIVERY"],"minItemSelections":0,"maxItemSelections":0,"merchantId":"407ce15e-6096-446b-9d6d-867fe1536948"}
				// Constants.displayInfo+"\":[{\""+Constants.title+"\":\"En\",\""+Constants.smallImage+"\":[\"https://s3.amazonaws.com/incentdev/94cf98f2-8514-40c0-bfb6-c04a52e32714/i/qa_logo_sm_81975891b4a94a39a65ca32ddd8271d1.jpg\"],\""+Constants.mediumImage+"\":[],\""+Constants.LangCode+"\":\"EN\"}],\""+Constants.extendedAttributes+"\":{},\""+Constants.privateAttributes+"\":{},\""+Constants.displayRank+"\":"+itemDisplayRank+",\""+Constants.status+"\":\""+itemDisplayStatus+"\",\""+Constants.applicableOrderTypes+"\":[\"PICKUP\",\"DELIVERY\"],\""+Constants.taxable+"\":"+itemDispalyTaxable+",\""+Constants.orderingChannels+"\":[\"MOBILE\"]}";	
			 
		
		//}
			return null;
	}
	
	
	
	
	

}
