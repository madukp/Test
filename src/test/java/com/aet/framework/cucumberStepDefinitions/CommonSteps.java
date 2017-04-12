package com.aet.framework.cucumberStepDefinitions;

import org.junit.Assert;

import com.aet.framework.incentivioAutApi.mob.MobLogin;
import com.aet.framework.incentivioAutApi.utilities.Utilities;
import com.aet.framework.incentivioAutApi.web.WebCampaign;
import com.aet.framework.incentivioAutApi.web.WebItem;
import com.aet.framework.incentivioAutApi.web.WebLogin;
import com.aet.framework.incentivioAutApi.web.WebMerchant;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class CommonSteps {
	
	public static String webAccessToken;	
	public static String mobileAccessToken;	
	public static String clientId = "94cf98f2-8514-40c0-bfb6-c04a52e32714";
	public static String merchantId;
	private WebMerchant webMerchant = new WebMerchant();

	
	@Given("^user authenticate to Incentivio$")
	public void user_authenticate_to_Incentivio() {
		try{
		WebLogin webLogin = new WebLogin();	
		webAccessToken = webLogin.getAutherization();
		}
		catch(Exception ex){
			Assert.assertFalse("An Exception occured when authenticate to Web ", true);
		}
	}
	
	@Then("^user authenticate to IncentivioMobileAPI$")
	public void user_authenticate_to_IncentivioMobileAPI() {
		try{
		MobLogin mobLogin1 = new MobLogin();
		mobileAccessToken = mobLogin1.getAutherizationMobile();
		}
		catch(Exception ex){
			Assert.assertFalse("An Exception occured when authenticate to mobile", true);
		}
	}


	@Given("^user creates a merchant$")
	public void user_creates_a_merchant(){
		try{
		merchantId = webMerchant.createMerchant(webAccessToken, clientId);
		Utilities.printMessage("\n==================  API Test : Merchant is Created ==================  ");
		}
		catch(Exception ex){
			Assert.assertFalse("An Exception occured when creating merchant", true);
		}
	    
	}



}
