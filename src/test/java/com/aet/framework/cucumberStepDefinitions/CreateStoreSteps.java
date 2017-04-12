package com.aet.framework.cucumberStepDefinitions;

import com.aet.framework.incentivioAutApi.utilities.Utilities;
import com.aet.framework.incentivioAutApi.web.WebItem;
import com.aet.framework.incentivioAutApi.web.WebLogin;
import com.aet.framework.incentivioAutApi.web.WebMerchant;
import com.aet.framework.incentivioAutApi.web.WebStore;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class CreateStoreSteps {
	
	

	private WebStore WebStore1 = new WebStore();	
	private CommonSteps cm = new CommonSteps();
	private WebMerchant webMerchant = new WebMerchant();
	// Here we are using the client id specific to the testing company
	private String clientId = cm.clientId;
	// token is already created in common steps when user logged in. we are utilizing the same token here. 
	private String webAccessToken = cm.webAccessToken;
	// merchant is already created in common steps . we are utilizing the same merchant id here. 
		private String merchantId = cm.merchantId;
	private static String storeId;
	
	
	
	@And("^user creates a store$")
	public void user_creates_a_store() throws Exception {
		
		storeId = WebStore1.createStore(webAccessToken, clientId, merchantId);
		System.out.println("created "+ storeId);
		Utilities.printMessage("\n==================  API Test : Store is Created ==================  ");
	}

	@Then("^user verify created store$")
	public void user_verify_created_store() throws Exception {
		System.out.println("storeId " + storeId);
	}

	@Then("^delete the created store$")
	public void delete_the_created_store() throws Exception {
		WebStore1.deleteStore(webAccessToken, clientId, merchantId, storeId);
		Utilities.printMessage("\n==================  API Test : Store is Deleted ==================  ");
	 
	}


}
