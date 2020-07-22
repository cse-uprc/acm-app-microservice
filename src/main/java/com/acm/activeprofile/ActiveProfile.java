package com.acm.activeprofile;

/**
 * Used to set and get the property files and active profile for the application
 * based on the current environment the application is running in.
 * 
 * @author Sam Butler
 * @since July 22, 2020
 */
public class ActiveProfile {
	public static final String PROD_ENV = "/app/src/main";
	public static final String DEV_ENV = "/app/src/main";
	public static final String LOCAL_ENV = "../acm-app-microservice/src/main";

	/**
	 * Method to set the current active profile the application is running in
	 * 
	 * Possible Values: Production, Development, Local
	 */
	public static void setPropertyFile() {
		if (System.getenv("APP_ENVIRONMENT") != null) {
			System.setProperty("spring.profiles.active", System.getenv("APP_ENVIRONMENT"));
		} else {
			System.setProperty("spring.profiles.active", "local");
		}
	}

	/**
	 * This method gets the path to the property file based on the environment
	 * 
	 * @return string of the path to the set property file
	 */
	public static String getPropertyFilePath() {
		String profile = System.getProperty("spring.profiles.active");
		if (profile.equals("production")) {
			return PROD_ENV + "/resources/application-prod.properties";
		} else if (profile.equals("development")) {
			return DEV_ENV + "/resources/application-development.properties";
		} else {
			return LOCAL_ENV + "/resources/application-development.properties";
		}
	}

	/**
	 * This method gets the current environment
	 * 
	 * @return string of the environment currently running
	 */
	public static String getEnvironment() {
		if (System.getenv("APP_ENVIRONMENT") != null) {
			return System.getenv("APP_ENVIRONMENT");
		} else {
			return "Local";
		}

	}
}
