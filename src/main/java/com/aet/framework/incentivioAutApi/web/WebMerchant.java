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

public class WebMerchant {
	HttpURLConnection connection = null;

	public String createMerchant(String webAccessToken, String clientId) {
		String merchantId = setMerchantDetail(webAccessToken, clientId);
		setLoyaltySpec(webAccessToken, clientId, merchantId);
		return merchantId;
	}

	private String setMerchantDetail(String webAccessToken, String clientId) {
		try {
			String baseUrl = Utilities.getDomain() + "incentivio-admin-api/incentivio-client-domain/clients/" + clientId
					+ "/merchants";

			URL url = new URL(baseUrl);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod(Constants.POST); // POST
			connection.setRequestProperty(Constants.ContentType, "application/json");

			connection.setRequestProperty(Constants.Authorization, "Bearer " + webAccessToken);
			connection.setRequestProperty(Constants.Accept, "application/json, text/plain, */*");

			connection.setUseCaches(false);
			connection.setDoOutput(true);

			String randomMessage = Utilities.getCurrentDate();


			String merchantTitle = "-0 00T MRCHNT " + randomMessage;

			String shortDesc = "AUT MERCHANT";
			String longDesc = "AUT MERCHANT";
			String messageFromEmail = "test@test.org";
			String streetAdd1 = "AUT MERCHANT";
			String streetAdd2 = "AUT MERCHANT";
			String city = "AUT MERCHANT";
			String region = "Alaska";
			String postalCode = "10250";
			String country = "United States";
			String pointAwardRatio = "12";
			String expiryMode = "SinceCurrentTx";
			String expiryPeriod = "5";
			String timeZone = "Asia/Colombo";

			String body = "{\"title\":\"" + merchantTitle
					+ "\",\"shortDescription\":\"AUT MERCHANT\",\"longDescription\":\"AUT MERCHANT\",\"messageFromEmail\":\"test@test.org\",\"address\":{\"streetAddress1\":\"AUT MERCHANT\",\"streetAddress2\":\"AUT MERCHANT\",\"city\":\"AUT MERCHANT\",\"region\":\"Alaska\",\"postalCode\":\"10250\",\"country\":\"United States\"},\"pointsAwardRatio\":\"12\",\"expiryMode\":\"SinceCurrentTx\",\"expiryPeriod\":\"5\",\"timezone\":\"Asia/Colombo\"}";

			DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
			wr.writeBytes(body);
			wr.close();

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
			Utilities.printInfoMessage(" --- Created Merchant Title : " + merchantTitle);
			return response.toString().split(":")[1].split(",")[0].replace("\"", "");

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}

	private String setLoyaltySpec(String webAccessToken, String clientId, String merchantId) {
		try {
			String baseUrl = Utilities.getDomain() + "incentivio-admin-api/incentivio-loyalty-domain/clients/"
					+ clientId + "/loyaltyspec/" + merchantId;

			URL url = new URL(baseUrl);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod(Constants.POST);
			connection.setRequestProperty("Content-Type", "application/json");

			connection.setRequestProperty("Authorization", "Bearer " + webAccessToken);
			connection.setRequestProperty("Accept", "application/json, text/plain, */*");

			connection.setUseCaches(false);
			connection.setDoOutput(true);

			// Request
			String languageCode = "EN";

			String smallImage = "/media/57e77a2ae4b052d3af4609b0/content";
			String mediumImage = "/media/57e77a51e4b052d3af4609b1/content";

			String jsonString = "{\"confirmationPeriod\":\"100\",\"pointsAwardRatio\":\"12\",\"expiryMode\":\"SinceCurrentTx\",\"expiryPeriod\":\"5\",\"awardPointsAsPending\":true,\"allowReturnsAfterConfirmationPeriod\":true}";

			DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
			wr.writeBytes(jsonString);
			wr.close();

			// Response
			InputStream is = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			StringBuilder response = new StringBuilder();

			String line;
			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			rd.close();

			return response.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}

	public void deleteMerchant(String webAccessToken, String clientId, String merchantId) {
		try {
			String baseUrl = Utilities.getDomain() + "incentivio-admin-api/incentivio-client-domain/clients/" + clientId
					+ "/merchants/" + merchantId;
			System.out.println(baseUrl);
			URL url = new URL(baseUrl);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod(Constants.DELETE);
			connection.setRequestProperty(Constants.ContentType, "application/json");
			connection.setRequestProperty("Authorization", "Bearer " + webAccessToken);
			System.out.println("Authorization " + webAccessToken );
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

			Utilities.printInfoMessage(" --- Deleted Merchant ");

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
