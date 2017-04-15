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

public class WebProgram {
	HttpURLConnection connection = null;
	private static String programID;

	public String createProgram(String webAccessToken, String clientId, String merchantId) {
		
		programID = setProgrammeInfo(webAccessToken, clientId, merchantId);
		return programID;
	}

	private String setProgrammeInfo(String webAccessToken, String clientId, String merchantId) {
		try {
			String baseUrl = Utilities.getDomain() + "incentivio-admin-api/incentivio-offers-domain/clients/" + clientId
					+ "/merchants/" + merchantId + "/programs";

			URL url = new URL(baseUrl);
			connection = (HttpURLConnection) url.openConnection();

			connection.setRequestMethod(Constants.POST); // POST
			connection.setRequestProperty(Constants.ContentType, "application/json");

			connection.setRequestProperty(Constants.Authorization, "Bearer " + webAccessToken);
			connection.setRequestProperty(Constants.Accept, "application/json, text/plain, */*");

			connection.setUseCaches(false);
			connection.setDoOutput(true);

			String programTitle = "AUT PRG " + Utilities.getCurrentDate();

			String jsonBody = "{\"title\":\"" + programTitle
					+ "\",\"shortDescription\":\"AUT Desc\",\"longDescription\":\"AUT Details Desc\"}";

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
			Utilities.printInfoMessage(" --- Created Program Title : " + programTitle);
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
	
	public String getProgramID(){
		return programID;
	}
	
	

	public void deleteProgram(String webAccessToken, String clientId, String merchantId, String programId) {
		try {
			String baseUrl = Utilities.getDomain() + "incentivio-admin-api/incentivio-offers-domain/clients/" + clientId
					+ "/merchants/" + merchantId + "/programs/" + programId;

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

			Utilities.printInfoMessage(" --- Deleted Program ");

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
