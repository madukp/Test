package com.aet.framework.cucumberStepDefinitions;

import org.junit.Assert;

import com.aet.framework.incentivioAutApi.mob.MobMessages;
import com.aet.framework.incentivioAutApi.utilities.Utilities;
import com.aet.framework.incentivioAutApi.web.WebCampaign;
import com.aet.framework.incentivioAutApi.web.WebItem;
import com.aet.framework.incentivioAutApi.web.WebLogin;
import com.aet.framework.incentivioAutApi.web.WebMerchant;
import com.aet.framework.incentivioAutApi.web.WebMessages;
import com.aet.framework.incentivioAutApi.web.WebProgram;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class CreateMessageSteps {
	
	

	private WebMessages webMessages1 = new WebMessages();	
	private WebMerchant webMerchant = new WebMerchant();
	private MobMessages mobMessages1 = new MobMessages();
	private WebProgram webProgram1 = new WebProgram();
	private WebCampaign webCampaign1 = new WebCampaign();
	private CommonSteps cm = new CommonSteps();
	private String WebItem_id;
	// Here we are using the client id specific to the testing company
	private String clientId = cm.clientId;
	private String  programId = webProgram1.getProgramID();
	private String campaignId = webCampaign1.getCampaignId();
	// token is already created in common steps when user logged in. we are utilizing the same token here. 
	private String webAccessToken = cm.webAccessToken;
	private String  mobAccessToken1 = cm.mobileAccessToken;
	// merchant is already created in common steps . we are utilizing the same merchant id here. 
	private String merchantId = cm.merchantId;
	private String messageID;	
	
	
	@Then("^user creates message$")
	public void user_creates_message()  {
		// create message from the web
		try{
		messageID = webMessages1.createMessage(webAccessToken, clientId, merchantId, programId, campaignId);
		Utilities.printInfoMessage("Message ID : " + messageID + "\n");
		Utilities.printInfoMessage("interval for manual verification\n");
		 Utilities.waitForSeconds(3);
		}
		 catch (Exception ex) {
				Assert.assertFalse("An Exception occured when creating message", true);
			}
	}
	

@Then("^user verify created message in Mobile$")
public void user_verify_created_message_in_Mobile() {
			
			// verifyMessageList
	try{
	mobMessages1.VerifyMessagesList(mobAccessToken1, messageID);
	}
	catch (Exception ex) {
		Assert.assertFalse("An Exception occured when Verify  message", true);
	}
	}




	@Then("^user verify created message$")
	public void user_verify_created_message()  {
	    
	}

	@Given("^delete the created message$")
	public void delete_the_created_message() {
		
		// delete the message from the web
		try{
		 webMessages1.deleteMessage(webAccessToken, clientId, merchantId, messageID);
		Utilities.printInfoMessage("Message ID : " + messageID + "\n");
		}
		catch (Exception ex) {
			Assert.assertFalse("An Exception occured when deleting message", true);
		}
	}

}
