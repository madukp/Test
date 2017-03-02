package com.aet.framework.incentivioAutApi.web;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.aet.framework.incentivioAutApi.utilities.Constants;
import com.aet.framework.incentivioAutApi.utilities.JsonMapper;
import com.aet.framework.incentivioAutApi.utilities.PropertyFile;
import com.aet.framework.incentivioAutApi.utilities.Utilities;


public class WebCatalog {
	
	final String propertyFileName = "web_access.properties";
	final String ConfigpropertyFileName = "config.properties";
	
	HttpURLConnection connection = null;
		
		public String createCatalog(String webAccessToken, String clientId, String merchantId, String storeId , String groupId) {
			
			String catalogId = setCatalogInfo(webAccessToken, clientId, merchantId, storeId, groupId);
			return catalogId;
		}
		
		
		public String setCatalogInfo(String webAccessToken, String clientId, String merchantId, String storeId , String groupId){
			try{
				String baseUrl = Utilities.getDomain() + "incentivio-admin-api/incentivio-ordering-service/catalogs";
				
				URL url = new URL(baseUrl);
				connection = (HttpURLConnection) url.openConnection();

				connection.setRequestMethod(Constants.PUT); 
				connection.setRequestProperty(Constants.ContentType, "application/json");

				connection.setRequestProperty(Constants.Authorization, "Bearer " + webAccessToken);
				
				connection.setRequestProperty(Constants.Accept, PropertyFile.readProperty("AcceptValue", propertyFileName));
				connection.setUseCaches(false);
				connection.setDoOutput(true);
				
				
				
				
				//Request
				
				String catalogTitle = "AUT CTLG " + Utilities.getCurrentDate();
				String groupGroupType = Utilities.getgroupType();
				String catalogStartDate =  Utilities.getCurrentDate();
				//End date is set for 10 days after the start date
				String catalogEndDate = Utilities.getFutureDate(10);
				String catalogShortDescription = "This is the short description for the group language";
				String catalogLongDescription = "This is the detailed description for the group language";
				String catalogDisplayRank = PropertyFile.readProperty("catalogDisplayRank", ConfigpropertyFileName);
				String catalogDisplayStatus =PropertyFile.readProperty("catalogDisplayStatus", ConfigpropertyFileName);
				String catalogDispalyTaxable =PropertyFile.readProperty("catalogDispalyTaxable", ConfigpropertyFileName);
				String catalogSubGroups = Utilities.getsubGroups();
				String groupOptionGroups= Utilities.getOptionGroups();
				String catalogExternalID = Utilities.getCatalogExternalId();
				
			
				String jsonString = JsonMapper.createCatalogRequest(catalogTitle, clientId,catalogDisplayStatus, catalogStartDate,  catalogEndDate, merchantId); 
				
				String jsonBody = "{"+jsonString+",\""+Constants.displayRank+"\":"+catalogDisplayRank+",\""+Constants.displayInfo+"\":[{\""+Constants.title+"\":\"en\",\""+Constants.shortDescription+"\":\""+catalogShortDescription+"\",\""+Constants.longDescription+"\":\""+catalogLongDescription+"\",\""+Constants.smallImage+"\":[\"https://s3.amazonaws.com/incentdev/94cf98f2-8514-40c0-bfb6-c04a52e32714/c/qa_logo_sm_67b918a56c344025aa1e1d31f4cb5b3d.jpg\"],\""+Constants.mediumImage+"\":[\"https://s3.amazonaws.com/incentdev/94cf98f2-8514-40c0-bfb6-c04a52e32714/c/qa_logo_sm_09725f4f8dfd4c8aa21f5ec3b84b12f5.jpg\"],\""+Constants.langCode+"\":\"EN\"}],\""+Constants.storeIds+"\":[\""+storeId+"\"],\""+Constants.groupIds+"\":[\""+groupId+"\"],\""+Constants.privateAttributes+"\":{\"key\":\"1\"},\""+Constants.extendedAttributes+"\":{\"key\":\"1\"},\""+Constants.catalogexternalId+"\":\""+catalogExternalID+"\"}";
				
				
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
				Utilities.printInfoMessage(" --- Created Catalog Title : " + catalogTitle);
				System.out.println("response" + response);
				String st = response.toString().split(":")[1].split("}")[0].replace("\"", "");
				System.out.println(st);
				return st;
				
				
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			} finally {
				if (connection != null) {
					connection.disconnect();
				}

			}

		}
		public void deleteCatalog(String webAccessToken, String clientId, String merchantId, String storeId , String groupId, String catalogId) {
			{
			try {
				
				String baseUrl = Utilities.getDomain() + "incentivio-admin-api/incentivio-ordering-service/catalogs/" + catalogId;

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

			Utilities.printInfoMessage(" --- Deleted Catalog ");

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
	}
