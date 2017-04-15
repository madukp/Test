package com.aet.framework.incentivioAutApi.web;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Assert;

import com.aet.framework.incentivioAutApi.utilities.Constants;
import com.aet.framework.incentivioAutApi.utilities.PropertyFile;
import com.aet.framework.incentivioAutApi.utilities.Utilities;

public class WebLogin {
	final String propertyFileName = "web_access.properties";

	/**
	 * Returns the Access Token after login in
	 * 
	 * @ReturnType String
	 */
	public String getAutherization() throws Exception {

		HttpURLConnection connection = null;
		boolean result = false;

		try {
			String urlString = Utilities.getDomain() + PropertyFile.readProperty("login_url", Constants.FILE_BASE_URLS);
				
			System.out.println(urlString);
			URL url = new URL(urlString);

			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod(Constants.POST);

			connection.setRequestProperty(Constants.ContentType,
					PropertyFile.readProperty("ContentTypeValue", propertyFileName));
			connection.setRequestProperty(Constants.Authorization,
					PropertyFile.readProperty("AuthorizationValue", propertyFileName));
			connection.setRequestProperty(Constants.Accept, PropertyFile.readProperty("AcceptValue", propertyFileName));

			connection.setUseCaches(false);
			connection.setDoOutput(true);

			// Send request ----
			String username = PropertyFile.readProperty("username_onrequest", "config.properties");
			String password = PropertyFile.readProperty("password", "config.properties");
			String client_alias = PropertyFile.readProperty("client_alias", "config.properties");

			// String requestDataString =
			// "username=shihan.s%40aeturnum.com&password=Asd123&clientId=undefined&grant_type=password&scope=read%20write&client_alias=fitwerxqa";
			//changed authenticatin to fail 
			String requestDataString = "username=" + username + "&password=" + password
					+ "&clientId=undefined&grant_type=password&scope=read%20write&client_alias=" + client_alias;
			System.out.println(requestDataString);
			DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
			wr.writeBytes(requestDataString);
			wr.close();
			int code = connection.getResponseCode();
			System.out.println("status Code" + code);
			// Handle Response Errors
			if(code==500){
				System.out.println("500 Internal Server Error");
						Assert.assertTrue(result);

			}
			if(code==400){
				System.out.println("HTTP Error 400 Bad request");
				Assert.assertTrue(result);

			}
			if(code==401){
				System.out.println("HTTP Error 401 - Unauthorized: Access is denied due to invalid credentials.");
				Assert.assertTrue(result);
			}
			if(code==200){
			InputStream is = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			StringBuilder response = new StringBuilder();

			String line;
			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			rd.close();

			String token = response.toString().split(":")[1].split(",")[0].replace("\"", "");
			return token;
			}
			else{
				return null;
			}
		} catch (Exception e) {			
			e.printStackTrace();
			
			return null;
		} finally {
			if (connection != null) {
				connection.disconnect();
				
			}
		}
	}

}
