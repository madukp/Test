package com.aet.framework.incentivioAutApi.mob;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Assert;

import com.aet.framework.incentivioAutApi.utilities.Constants;
import com.aet.framework.incentivioAutApi.utilities.PropertyFile;
import com.aet.framework.incentivioAutApi.utilities.Utilities;

public class MobMessages {
	String jsonString = null;
	HttpURLConnection connection = null;

	public void VerifyMessagesList(String mobAccessToken, String messageId) throws Exception {
		try {
			String baseUrl = Utilities.getDomain()
					+ PropertyFile.readProperty("mob_verify_message", Constants.FILE_BASE_URLS);

			StringBuilder result = new StringBuilder();
			System.out.println(baseUrl);
			URL url = new URL(baseUrl); // "https://incentqa.aeturnum.com/incentivio-mobile-api/appcontent?page=1&count=200&languagecode=EN"
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod(Constants.GET);
			connection.setRequestProperty(Constants.Authorization, "Bearer " + mobAccessToken);
			connection.setRequestProperty(Constants.ContentType, "application/json");
			connection.setUseCaches(false);
			connection.setDoOutput(true);

			// Get response
			BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = rd.readLine()) != null) {
				jsonString = result.append(line).toString();
			}
			rd.close();
			// JSONObject object = (JSONObject) JSONValue.parse(jsonString);

			Utilities.printInfoMessage("Mobile -  Recieved Message ID : " + messageId + "\n");
			// Utilities.printMessage(jsonString.replace(',', '\n'));

			System.out.println("js : " + jsonString);
			Assert.assertTrue("Test Results : Received Message ID is not matching", jsonString.contains(messageId));

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
