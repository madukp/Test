package com.aet.framework.incentivioAutApi;


import org.junit.Test;

import com.aet.framework.incentivioAutApi.mob.MobLogin;
import com.aet.framework.incentivioAutApi.mob.MobMessages;
import com.aet.framework.incentivioAutApi.utilities.Utilities;
import com.aet.framework.incentivioAutApi.web.WebLogin;
import com.aet.framework.incentivioAutApi.web.WebLogout;
import com.aet.framework.incentivioAutApi.web.WebMerchant;
import com.aet.framework.incentivioAutApi.web.WebStore;
import com.aet.framework.incentivioAutApi.web.WebItem;

public class CreateItemWeb_Test {
	
	@Test
	public void create_Item() throws Exception {

		String webAccessToken;	
		Utilities.printMessage("\n==================  API Test : Item Creation ==================  ");
		WebLogin webLogin = new WebLogin();
		WebMerchant webMerchant = new WebMerchant();	
		WebItem WebItem1 = new WebItem();
		//String clientId
		String clientId = "94cf98f2-8514-40c0-bfb6-c04a52e32714";
		// login to web
		webAccessToken = webLogin.getAutherization();
		Utilities.printInfoMessage("[Web Token] " + webAccessToken + "\n");
		
		// create merchant
		String merchantId = webMerchant.createMerchant(webAccessToken, clientId);
		Utilities.printInfoMessage("Merchant ID: " + merchantId + "\n");

		// create item from the web
		String WebItem_id = WebItem1.createItem(webAccessToken, clientId, merchantId);

		Utilities.printInfoMessage("interval for manual verification\n");
		System.out.println(WebItem_id);
		 Utilities.waitForSeconds(5);

		//delete the item from the web
		WebItem1.deleteItem(webAccessToken, clientId, merchantId, WebItem_id);
		Utilities.printInfoMessage("Item ID : " + WebItem_id + "\n");
		webMerchant.deleteMerchant(webAccessToken, clientId, merchantId);
		
	}



}


