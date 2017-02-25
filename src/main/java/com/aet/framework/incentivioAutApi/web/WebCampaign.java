package com.aet.framework.incentivioAutApi.web;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.aet.framework.incentivioAutApi.utilities.Constants;
import com.aet.framework.incentivioAutApi.utilities.PropertyFile;
import com.aet.framework.incentivioAutApi.utilities.Utilities;

public class WebCampaign {
	HttpURLConnection connection = null;

	public String createCampaign(String webAccessToken, String clientId, String merchantId, String programId) { // "d01d94cb-4bb9-457f-89f9-9d7374322bb1"

		String campaignId = "";
		campaignId = setCampaignInfo(webAccessToken, clientId, merchantId, programId);
		return campaignId;
	}

	private String setCampaignInfo(String webAccessToken, String clientId, String merchantId, String programId) {

		try {
			String baseUrl = Utilities.getDomain() + "incentivio-admin-api/incentivio-offers-domain/clients/" + clientId
					+ "/merchants/" + merchantId + "/programs/" + programId + "/campaigns";
			URL url = new URL(baseUrl);
			connection = (HttpURLConnection) url.openConnection();

			connection.setRequestMethod(Constants.POST); // POST
			connection.setRequestProperty(Constants.ContentType, "application/json");

			connection.setRequestProperty(Constants.Authorization, "Bearer " + webAccessToken);
			connection.setRequestProperty(Constants.Accept, "application/json, text/plain, */*");

			connection.setUseCaches(false);
			connection.setDoOutput(true);

			String jsonBody = "{\"title\":\"Campaign Marketing11\",\"shortDescription\":\"CM Description\",\"longDescription\":\"CM Details Description\",\"startDate\":\"2016-10-05\",\"endDate\":\"2017-10-30\"}";

			String campaignTitle = "AUT CM " + Utilities.getCurrentDate();
			String shortDescription = "CM Description";
			String longDescription = "CM Details Description";
			String startDate = "2016-10-05";
			String endDate = "2017-12-30";

			jsonBody = "{\"title\":\"" + campaignTitle
					+ "\",\"shortDescription\":\"CM Description\",\"longDescription\":\"CM Details Description\",\"startDate\":\"2016-10-05\",\"endDate\":\"2017-12-30\"}";

			// request
			DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
			wr.writeBytes(jsonBody);
			wr.close();

			// response
			InputStream is = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			StringBuilder response = new StringBuilder();

			String line;
			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			rd.close();
			Utilities.printInfoMessage(" --- Created Campaign Title : " + campaignTitle);
			return response.toString().split(":")[1].split("}")[0].replace("\"", "");

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}

	public void deleteCampaign(String webAccessToken, String clientId, String merchantId, String programId,
			String campaignId) {

		try {
			String baseUrl = Utilities.getDomain() + "incentivio-admin-api/incentivio-offers-domain/clients/" + clientId
					+ "/merchants/" + merchantId + "/programs/" + programId + "/campaigns/" + campaignId;

			URL url = new URL(baseUrl);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod(Constants.DELETE);
			connection.setRequestProperty(Constants.ContentType, "application/json");
			connection.setRequestProperty("Authorization", "Bearer " + webAccessToken);

			connection.setRequestProperty("Accept", "application/json, text/plain, */*");

			connection.setUseCaches(false);
			connection.setDoOutput(true);

			// Get Response
			InputStream is = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			StringBuilder response = new StringBuilder();

			String line;
			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			rd.close();

			Utilities.printInfoMessage(" --- Deleted Campaign ");

		} catch (Exception e) {
			e.printStackTrace();
			// return null;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}
}
