package com.aet.framework.incentivioAutApi.utilities;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyFile {
	/**
	 * Read the required property based on the give property file and the
	 * property name
	 * 
	 * @Author : hirosh.t
	 * @ReturnType String
	 * @param property
	 *            is the property name
	 * @param propertyFileName
	 *            should be with file-extension ("config.properties")
	 */
	public static String readProperty(String property, String propertyFileName) {

		String result = "";
		InputStream inputStream = null;
		try {
			Properties prop = new Properties();
			String propFileName = propertyFileName;

			inputStream = PropertyFile.class.getClassLoader().getResourceAsStream(propFileName);
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
			result = prop.getProperty(property);
			inputStream.close();
		} catch (Exception e) {
			Utilities.printMessage("Exception: " + e);
		} finally {
			// final action ---
		}
		return result;
	}

	public void testApp() {
		System.out.println("Test App");
		// assertTrue( true );
	}

}
