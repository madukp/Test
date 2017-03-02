package com.aet.framework.incentivioAutApi;

import org.junit.Test;

import com.aet.framework.incentivioAutApi.utilities.Utilities;
import com.aet.framework.incentivioAutApi.web.WebGroup;
import com.aet.framework.incentivioAutApi.web.WebItem;
import com.aet.framework.incentivioAutApi.web.WebLogin;
import com.aet.framework.incentivioAutApi.web.WebStore;
import com.aet.framework.incentivioAutApi.web.WebMerchant;
import com.aet.framework.incentivioAutApi.web.WebCatalog;

public class CreateCatalogWeb_Test {
	
	@Test
	public void test_method() throws Exception {

		String webAccessToken;
	

		Utilities.printMessage("\n==================  API Test : Catalog Creation ==================  ");

		WebLogin webLogin = new WebLogin();
		WebMerchant webMerchant = new WebMerchant();
		//WebProgram webProgram = new WebProgram();
		//WebCampaign webCampaign = new WebCampaign();
		WebItem webItem = new WebItem();
		WebStore  webStore = new WebStore();
		WebGroup webGroup = new WebGroup();
		WebCatalog webCatalog = new WebCatalog();
		String groupId ="";
		
		
		int subGroupSize = Integer.parseInt(Utilities.getsubGroupSize());
		int optionGroupSize = Integer.parseInt(Utilities.getsubGroupSize());
		
		
		String optionGroupId[]=new String[optionGroupSize];
		String subGroupId[]=new String[subGroupSize];
		
		String clientId = Utilities.getclientId();

		// login to web
		webAccessToken = webLogin.getAutherization();
		Utilities.printInfoMessage("[Web Token] " + webAccessToken + "\n");

		// create merchant
		String merchantId = webMerchant.createMerchant(webAccessToken, clientId);
		Utilities.printInfoMessage("Merchant ID: " + merchantId + "\n");

		
		// create item from the web
		String itemId = webItem.createItem(webAccessToken, clientId, merchantId);
		Utilities.printInfoMessage("Item ID: "+itemId+"\n");
		
		// create store from the web
		String storeId = webStore.createStore(webAccessToken, clientId, merchantId);
		Utilities.printInfoMessage("Store ID: "+storeId+"\n");
		


		
		 for (int i=0; i<subGroupSize ; i++) {
				
		// create sub group from the web
		String groupId_sub = webGroup.createGroup(webAccessToken, clientId, merchantId,itemId );
		Utilities.printInfoMessage("Group ID"+i+ ":"+groupId_sub+"\n");
		subGroupId[i]=groupId_sub;		
		Utilities.waitForSeconds(3);	
		 }
		
		  for (int i=0;i<optionGroupSize  ; i++) {
				
					// create option Group from the we
					String groupId_op = webGroup.createGroup(webAccessToken, clientId, merchantId,itemId);
					Utilities.printInfoMessage("Group ID"+i+ ":"+groupId_op+"\n");
					optionGroupId[i]=groupId_op;
					Utilities.waitForSeconds(3);
				
		  }
				
		
			//sometimes HTTP response code: 409 - conflic
		
			// create group for catalog from the web
		groupId = webGroup.createGroup_forCatalog(webAccessToken, clientId, merchantId,itemId, subGroupId,optionGroupId);
		Utilities.printInfoMessage("Group ID: "+groupId+"\n");
				
		
		
						
			// create catalog from the web
		String catalogId = webCatalog.createCatalog(webAccessToken, clientId, merchantId, storeId, groupId);
		Utilities.printInfoMessage("interval for manual verification\n");
		System.out.println(catalogId);
		Utilities.waitForSeconds(5);

		//delete the catalog from the web
		webCatalog.deleteCatalog(webAccessToken, clientId, merchantId,  storeId, groupId, catalogId);
		Utilities.printInfoMessage("Catalog ID : " + catalogId + "\n");



}
	
}
