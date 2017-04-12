package com.aet.framework.cucumberStepDefinitions;

import org.junit.Assert;

import com.aet.framework.incentivioAutApi.utilities.Utilities;
import com.aet.framework.incentivioAutApi.web.WebItem;
import com.aet.framework.incentivioAutApi.web.WebLogin;
import com.aet.framework.incentivioAutApi.web.WebMerchant;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class CreateMerchantSteps {
	
		
	private WebMerchant webMerchant = new WebMerchant();
	private CommonSteps cm = new CommonSteps();

	// Here we are using the client id specific to the testing company
	private String clientId = cm.clientId;
	// token is already cretaed in common steps when user logged in. we are utilizing the same token here. 
	private String webAccessToken = cm.webAccessToken;
	// merchant is already cretaed in common steps . we are utilizing the same merchant id here. 
	private String merchantId = cm.merchantId;
	
	@Then("^user verify merchat$")
	public void user_verify_merchat() throws Exception  {
		System.out.println("merchantId " + merchantId);
	}

	@Then("^delete merchant$")
	public void delete_merchant() {
		try{
		webMerchant.deleteMerchant(webAccessToken, clientId, merchantId);
		}
		catch (Exception ex) {
			Assert.assertFalse("An Exception occured when deleting  merchant", true);
		}
	}

}
