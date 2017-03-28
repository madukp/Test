package com.aet.framework.incentivioAutApi;

import org.junit.Test;


import com.aet.framework.incentivioAutApi.utilities.Utilities;
import com.aet.framework.incentivioAutApi.web.WebGroup;
import com.aet.framework.incentivioAutApi.web.WebItem;
import com.aet.framework.incentivioAutApi.web.WebLogin;
import com.aet.framework.incentivioAutApi.web.WebMerchant;

public class CreateGroupWeb_Test {
	
	@Test
	public void test_method() throws Exception {

		String webAccessToken;
	

		Utilities.printMessage("\n==================  API Test : Group Creation ==================  ");

		WebLogin webLogin = new WebLogin();
		WebMerchant webMerchant = new WebMerchant();
		WebItem WebItem1 = new WebItem();
		WebGroup WebGroup1 = new WebGroup();


		
		String clientId = "94cf98f2-8514-40c0-bfb6-c04a52e32714";

		// login to web
		webAccessToken = webLogin.getAutherization();
		Utilities.printInfoMessage("[Web Token] " + webAccessToken + "\n");

		// create merchant
		String merchantId = webMerchant.createMerchant(webAccessToken, clientId);
		Utilities.printInfoMessage("Merchant ID: " + merchantId + "\n");

		// create item from the web
		String itemId = WebItem1.createItem(webAccessToken, clientId, merchantId);
		Utilities.printInfoMessage("Item ID: "+itemId+"\n");
		
				
			// create group from the web - optiongroups
		String groupId = WebGroup1.createGroup(webAccessToken, clientId, merchantId, itemId );
		Utilities.printInfoMessage("interval for manual verification\n");
		System.out.println(groupId);
		Utilities.waitForSeconds(5);

		//delete the group from the web_option groups
		WebGroup1.deleteGroup(webAccessToken, clientId, merchantId, itemId, groupId);
		Utilities.printInfoMessage("Group ID : " + groupId + "\n");

	}



}

