package com.aet.framework.cucumberStepDefinitions;

import org.junit.Assert;

import com.aet.framework.incentivioAutApi.utilities.Utilities;
import com.aet.framework.incentivioAutApi.web.WebItem;
import com.aet.framework.incentivioAutApi.web.WebLogin;
import com.aet.framework.incentivioAutApi.web.WebMerchant;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class CreateItemSteps {
	
	private WebItem WebItem1 = new WebItem();	
	private WebMerchant webMerchant = new WebMerchant();
	private CommonSteps cm = new CommonSteps();
	private String WebItem_id;
	// Here we are using the client id specific to the testing company
	private String clientId = cm.clientId;
	// token is already created in common steps when user logged in. we are utilizing the same token here. 
	private String webAccessToken = cm.webAccessToken;
	// merchant is already created in common steps . we are utilizing the same merchant id here. 
	private String merchantId = cm.merchantId;
	
	@Then("^user creates Item$")
	public void user_creates_Item() throws Exception {
		try{
		WebItem_id = WebItem1.createItem(webAccessToken, clientId, merchantId);
		Utilities.printMessage("\n==================  API Test : Item is Created ==================  ");
		}
		catch(Exception ex){
			Assert.assertFalse("An Exception occured when creating an item", true);
		}
	}

	@Then("^user verify created Item$")
	public void user_verify_created_Item() throws Exception {
		System.out.println(WebItem_id);
	   
	}

	@Then("^delete the created Item$")
	public void delete_the_created_Item() throws Exception {
		try{
		WebItem1.deleteItem(webAccessToken, clientId, merchantId, WebItem_id);
		Utilities.printMessage("\n==================  API Test : Item is Deleted ==================  ");
		}
		catch(Exception ex){
			Assert.assertFalse("An Exception occured when deleting an item", true);
		}
	}

}
