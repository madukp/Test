package com.aet.framework.incentivioAutApi;

import org.junit.Test;

import com.aet.framework.incentivioAutApi.mob.MobLogin;
import com.aet.framework.incentivioAutApi.mob.MobMessages;
import com.aet.framework.incentivioAutApi.utilities.Utilities;
import com.aet.framework.incentivioAutApi.web.WebCampaign;
import com.aet.framework.incentivioAutApi.web.WebLogin;
import com.aet.framework.incentivioAutApi.web.WebLogout;
import com.aet.framework.incentivioAutApi.web.WebMerchant;
import com.aet.framework.incentivioAutApi.web.WebMessages;
import com.aet.framework.incentivioAutApi.web.WebProgram;

public class CreateMessagesWeb_Test {
	// public static void main(String args[]) throws Exception {
	public CreateMessagesWeb_Test() throws Exception {

	}


	public void test_method() throws Exception {

		String webAccessToken;
		String mobAccessToken;

		Utilities.printMessage("\n==================  API Test : Message Creation ==================  ");

		WebLogin webLogin = new WebLogin();
		WebLogout webLogout = new WebLogout();
		WebMerchant webMerchant = new WebMerchant();
		WebProgram webProgram = new WebProgram();
		WebCampaign webCampaign = new WebCampaign();
		WebMessages webMessages = new WebMessages();

		MobLogin mobLogin = new MobLogin();
		MobMessages mobMessages = new MobMessages();

		String clientId = "94cf98f2-8514-40c0-bfb6-c04a52e32714";

		// login to web
		webAccessToken = webLogin.getAutherization();
		Utilities.printInfoMessage("[Web Token] " + webAccessToken + "\n");

		// create merchant
		String merchantId = webMerchant.createMerchant(webAccessToken, clientId);
		Utilities.printInfoMessage("Merchant ID: " + merchantId + "\n");

		// create programme
		String programId; // = "d01d94cb-4bb9-457f-89f9-9d7374322bb1";
		programId = webProgram.createProgram(webAccessToken, clientId, merchantId);
		Utilities.printInfoMessage("Programe ID: " + programId + "\n");

		// create campaign
		String campaignId; // = "051c1100-4762-487c-9206-c7af1f9a0c07";
		campaignId = webCampaign.createCampaign(webAccessToken, clientId, merchantId, programId);
		Utilities.printInfoMessage("Campaign ID: " + campaignId + "\n");

		// create message from the web
		String messageID = webMessages.createMessage(webAccessToken, clientId, merchantId, programId, campaignId);
		Utilities.printInfoMessage("Message ID : " + messageID + "\n");

		Utilities.printInfoMessage("interval for manual verification\n");
		 Utilities.waitForSeconds(10);

		// login to mobile
		mobAccessToken = mobLogin.getAutherizationMobile();
		Utilities.printInfoMessage("[Mob Token] " + mobAccessToken + "\n");

		
		// verifyMessageList
		mobMessages.VerifyMessagesList(mobAccessToken, messageID);
		

		// ------------------- custom delay point
		// Utilities.showMessageBox("Check for the created message before
		// deleting");

		// delete the message from the web
		webMessages.deleteMessage(webAccessToken, clientId, merchantId, messageID);
		Utilities.printInfoMessage("Message ID : " + messageID + "\n");

		// delete the campaign from the web
		webCampaign.deleteCampaign(webAccessToken, clientId, merchantId, programId, campaignId);
		Utilities.printInfoMessage("Campaign ID: " + campaignId + "\n");

		// delete the programme from the web
		webProgram.deleteProgram(webAccessToken, clientId, merchantId, programId);
		Utilities.printInfoMessage("Programe ID: " + programId + "\n");

		// delete merchant from the web
		webMerchant.deleteMerchant(webAccessToken, clientId, merchantId);
		Utilities.printInfoMessage("Merchant ID: " + merchantId + "\n");

		// logout from web
		webLogout.getLogout(webAccessToken);
		Utilities.printInfoMessage("Logged out : " + webAccessToken + "\n");
	}
}
