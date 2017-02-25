package com.aet.framework.incentivioAutApi.web;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.aet.framework.incentivioAutApi.utilities.Constants;
import com.aet.framework.incentivioAutApi.utilities.PropertyFile;
import com.aet.framework.incentivioAutApi.utilities.Utilities;

public class WebLogout {
	final String propertyFileName = "access_logout.properties";

	/**
	 * <Method Description>
	 * 
	 * @param auth
	 *            is the access-token during the login
	 * @throws Exception
	 */
	public void getLogout(String auth) throws Exception {

		StringBuilder result = new StringBuilder();
		URL url = new URL(Utilities.getDomain() + PropertyFile.readProperty("logout_url", Constants.FILE_BASE_URLS));
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod(Constants.GET); // GET
		conn.setRequestProperty("Authorization", "Bearer " + auth);
		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line;
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		rd.close();
		result.toString();
	}

}
