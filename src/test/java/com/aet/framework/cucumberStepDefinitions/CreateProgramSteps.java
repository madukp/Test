package com.aet.framework.cucumberStepDefinitions;

import org.junit.Assert;

import com.aet.framework.incentivioAutApi.utilities.Utilities;
import com.aet.framework.incentivioAutApi.web.WebCampaign;
import com.aet.framework.incentivioAutApi.web.WebItem;
import com.aet.framework.incentivioAutApi.web.WebLogin;
import com.aet.framework.incentivioAutApi.web.WebMerchant;
import com.aet.framework.incentivioAutApi.web.WebProgram;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class CreateProgramSteps {

	private WebProgram WebProgram1 = new WebProgram();
	private CommonSteps cm = new CommonSteps();
	private static String WebProgram_id;
	// Here we are using the client id specific to the testing company
	private String clientId = cm.clientId;
	// token is already cretaed in common steps when user logged in. we are
	// utilizing the same token here.
	private String webAccessToken = cm.webAccessToken;
	// merchant is already cretaed in common steps . we are utilizing the same
	// merchant id here.
	private String merchantId = cm.merchantId;
	private WebCampaign webCampaign = new WebCampaign();

	CreateItemSteps Cr = new CreateItemSteps();

	@Given("^user creates Program$")
	public void user_creates_Program() {
		try {
			WebProgram_id = WebProgram1.createProgram(webAccessToken, clientId, merchantId);
			Utilities.printMessage("\n==================  API Test : Item is Created ==================  ");
		} catch (Exception ex) {
			Assert.assertFalse("An Exception occured when creating Program", true);
		}
	}

	@Then("^user verify created Program$")
	public void user_verify_created_Program()  {
		// Write code here that turns the phrase above into concrete actions

	}

	@Then("^delete the created Program$")
	public void delete_the_created_Program() {
		// Write code here that turns the phrase above into concrete actions

	}

	@Given("^user creates Campaign$")
	public void user_creates_Campaign(){
		try {
			webCampaign.createCampaign(webAccessToken, clientId, merchantId, WebProgram_id);
		} catch (Exception ex) {
			Assert.assertFalse("An Exception occured when creating Campaign", true);
		}
	}

}
