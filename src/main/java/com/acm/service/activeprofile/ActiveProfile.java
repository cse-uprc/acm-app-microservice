package com.acm.service.activeprofile;

import static com.acm.service.globals.variables.Environments.DEV_ENV;
import static com.acm.service.globals.variables.Environments.LOCAL_ENV;
import static com.acm.service.globals.variables.Environments.PROD_ENV;

import org.springframework.stereotype.Component;

/**
 * Used to set and get the property files and active profile for the application
 * based on the current environment the application is running in.
 * 
 * @author Sam Butler
 * @since July 22, 2020
 */
@Component
public class ActiveProfile {
	/**
	 * Method to set the current active profile the application is running in
	 * 
	 * Possible Values: Production, Development, Local
	 */
	public void setPropertyFile() {
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
	public String getPropertyFilePath() {
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
	public String getEnvironment() {
		if (System.getenv("APP_ENVIRONMENT") != null) {
			return System.getenv("APP_ENVIRONMENT");
		} else
			return "local";

	}

	/**
	 * Gets the environment url
	 * 
	 * @return string of the environment url
	 */
	public String getEnvironmentUrl() {
		String profile = System.getProperty("spring.profiles.active");
		if (profile != null && profile.equals("production"))
			return PROD_ENV;
		else if (profile != null && profile.equals("development"))
			return DEV_ENV;
		else
			return LOCAL_ENV;

	}
}
