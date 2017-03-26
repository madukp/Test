package com.aet.framework.cucumberStepDefinitions;

import com.aet.framework.incentivioAutApi.utilities.Utilities;
import com.aet.framework.incentivioAutApi.web.WebItem;
import com.aet.framework.incentivioAutApi.web.WebLogin;
import com.aet.framework.incentivioAutApi.web.WebMerchant;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class CreateItemSteps {
	
	String webAccessToken;	
	String clientId = "94cf98f2-8514-40c0-bfb6-c04a52e32714";
	String merchantId;
	String WebItem_id;
	WebItem WebItem1 = new WebItem();	
	WebMerchant webMerchant = new WebMerchant();
	
	@Given("^user authenticate to Incentivio$")
	public void user_authenticate_to_Incentivio() throws Exception {
		WebLogin webLogin = new WebLogin();		
		webAccessToken = webLogin.getAutherization();
	   
	}

	@Given("^user creates a merchant$")
	public void user_creates_a_merchant() throws Exception {
		
		merchantId = webMerchant.createMerchant(webAccessToken, clientId);
		Utilities.printMessage("\n==================  API Test : Merchant is Created ==================  ");
	    
	}

	@Then("^user creates Item$")
	public void user_creates_Item() throws Exception {
		
		
		WebItem_id = WebItem1.createItem(webAccessToken, clientId, merchantId);
		Utilities.printMessage("\n==================  API Test : Item is Created ==================  ");
	   
	}

	@Then("^user verify created Item$")
	public void user_verify_created_Item() throws Exception {
		System.out.println(WebItem_id);
	   
	}

	@Then("^delete the created Item$")
	public void delete_the_created_Item() throws Exception {
		WebItem1.deleteItem(webAccessToken, clientId, merchantId, WebItem_id);
		Utilities.printMessage("\n==================  API Test : Item is Deleted ==================  ");
	    
	}

}
