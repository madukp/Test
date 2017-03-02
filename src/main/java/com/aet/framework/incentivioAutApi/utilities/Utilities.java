package com.aet.framework.incentivioAutApi.utilities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import javax.swing.JOptionPane;

public class Utilities {
	/**
	 * Prints the message with [INFO] tag infront in console
	 * 
	 * @Author : hirosh.t
	 * @ReturnType void
	 * @param message
	 *            is the text required to display in the console
	 */
	public static void printInfoMessage(String message) {
		System.out.println("[INFO] " + message);
	}

	public static void printMessage(String message) {
		System.out.println(message);
	}

	/**
	 * Description
	 * 
	 * @Author : hirosh.t
	 * @ReturnType String will return Ex : a"Test 23456"
	 */
	public static String randomMessageGenerate() {
		// String rndomValue = String.valueOf(new Random().nextInt(999999999));
		return "AUT MSG " + getCurrentDate();
	}

	/**
	 * 
	 * @Author : hirosh.t
	 * @ReturnType void
	 */
	public void displayRandomMessageInConsole() {
		Utilities.printInfoMessage(PropertyFile.readProperty("user", "config.properties"));
	}

	/**
	 * 
	 * @Author : hirosh.t
	 * @ReturnType void
	 */
	public static String getDomain() {
		return PropertyFile.readProperty("domain", "config.properties");
	}
	
	public static String getExternalId() {
		return PropertyFile.readProperty("itemExternalId", "config.properties");
	}
	
	public static String getCatalogExternalId() {
		return PropertyFile.readProperty("catalogExternalId", "config.properties");
	}
	
	public static String getsubGroupSize() {
		return PropertyFile.readProperty("subgroupSize", "config.properties");
	}
	public static String getOptionGroupSize() {
		return PropertyFile.readProperty("optiongroupSize", "config.properties");
	}
	
	public static String getBrandId() {
		return PropertyFile.readProperty("brandId", "config.properties");
	}
	
	public static String getCategoryId() {
		return PropertyFile.readProperty("categoryId", "config.properties");
	}
	public static String getgroupExternalId() {
		return PropertyFile.readProperty("groupExternalId", "config.properties");
	}
	public static String getclientId() {
		return PropertyFile.readProperty("clientId", "config.properties");
	}
	
	
	public static String getOptionGroups() {
		return PropertyFile.readProperty("optionGroups", "config.properties");
	}
	
	public static String getPrice() {
		return PropertyFile.readProperty("price", "config.properties");
	}
	public static String getgroupType() {
		return PropertyFile.readProperty("groupType", "config.properties");
	}
	
	public static String getsubGroups() {
		return PropertyFile.readProperty("subGroups", "config.properties");
	}
	
	public static String getCurrentDate() {

		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();

		// Utilities.printMessage(String.format(now.format(format)));
		return String.format(now.format(format));
	}

	public static String getFutureDate(int daysahead) {

		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime future = now.plusDays(daysahead);

		// Utilities.printMessage(String.format(future.format(format)));
		return String.format(future.format(format));

	}

	public static void waitForSeconds(int seconds) {
		int miliseconds = seconds * 1000;
		try {
			Thread.sleep(miliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	// Prompt Panels / Frames
	public static String showInputBox(String displayMessage) {
		return JOptionPane.showInputDialog(displayMessage);
	}

	public static void showMessageBox(String displayMessage) {
		JOptionPane.showMessageDialog(null, displayMessage);
	}

	public static void showConfirmationBox(String displayMessage) {
		JOptionPane.showConfirmDialog(null, displayMessage, "Warning", JOptionPane.OK_CANCEL_OPTION);
	}

}
