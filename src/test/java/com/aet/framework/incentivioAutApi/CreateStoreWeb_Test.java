package com.aet.framework.incentivioAutApi;

import org.junit.Test;

import com.aet.framework.incentivioAutApi.mob.MobLogin;
import com.aet.framework.incentivioAutApi.mob.MobMessages;
import com.aet.framework.incentivioAutApi.utilities.Utilities;
import com.aet.framework.incentivioAutApi.web.WebLogin;
import com.aet.framework.incentivioAutApi.web.WebLogout;
import com.aet.framework.incentivioAutApi.web.WebMerchant;

import com.aet.framework.incentivioAutApi.web.WebStore;

public class CreateStoreWeb_Test {



	@Test
	public void test_method() throws Exception {

		String webAccessToken;
	

		Utilities.printMessage("\n==================  API Test : Store Creation ==================  ");

		WebLogin webLogin = new WebLogin();
		WebMerchant webMerchant = new WebMerchant();
		//WebProgram webProgram = new WebProgram();
		//WebCampaign webCampaign = new WebCampaign();
		WebStore  WebStore1 = new WebStore();



		String clientId = "94cf98f2-8514-40c0-bfb6-c04a52e32714";

		// login to web
		webAccessToken = webLogin.getAutherization();
		Utilities.printInfoMessage("[Web Token] " + webAccessToken + "\n");

		// create merchant
		//String merchantId = webMerchant.createMerchant(webAccessToken, clientId);
		//Utilities.printInfoMessage("Merchant ID: " + merchantId + "\n");

		// create store from the web
		String WebStore_id = WebStore1.createStore(webAccessToken, clientId, "77ae0b4c-0df8-4e64-bb15-49b27aa7257f");

		Utilities.printInfoMessage("interval for manual verification\n");
		System.out.println(WebStore_id);
		 Utilities.waitForSeconds(5);

		// delete the store from the web
		WebStore1.deleteStore(webAccessToken, clientId, "77ae0b4c-0df8-4e64-bb15-49b27aa7257f", WebStore_id);
		Utilities.printInfoMessage("StoreID : " + WebStore_id + "\n");

		
	}



}
