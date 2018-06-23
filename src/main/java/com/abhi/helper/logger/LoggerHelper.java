package com.abhi.helper.logger;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * @author abhinandan
 * 
 */
@SuppressWarnings("rawtypes")
public class LoggerHelper {

	private static boolean root = false;

	public static Logger getLogger(Class className){
		if (root) {
			return Logger.getLogger(className);
		}
		PropertyConfigurator.configure( System.getProperty("user.dir")+"/src/main/resources/config/log4j.properties");
		root = true;
		return Logger.getLogger(className);
	}
}
