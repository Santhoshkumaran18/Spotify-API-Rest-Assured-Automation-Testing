package com.spotify.OAuth2.utils;

import java.util.Properties;

public class ConfigLoader {
	private  final Properties properties;
	private static  ConfigLoader configLoader;
	
	private ConfigLoader() {
		properties=PropertyUtils.propertyLoader("src/test/resources/config.properties");
		
	}
	
	public static ConfigLoader getInstance() {
	
			if(configLoader==null) {
				configLoader=new ConfigLoader();
			}
			return configLoader;
	}
	
	public String getClient() {
		String prop=properties.getProperty("client_id");
		if(prop!=null) {
			return prop;
		}else {
			throw new RuntimeException("Cient id is not specified in the config.properties file");
		}
	}
	
	
	public String getClientSecret() {
		String prop=properties.getProperty("client_secret");
		if(prop!=null) {
			return prop;
		}else {
			throw new RuntimeException("Cient id is not specified in the config.properties file");
		}
	}
	
	
	public String getGranttype() {
		String prop=properties.getProperty("grant_type");
		if(prop!=null) {
			return prop;
		}else {
			throw new RuntimeException("Cient id is not specified in the config.properties file");
		}
	}
	
	public String getUserId() {
		String prop=properties.getProperty("user_id");
		if(prop!=null) {
			return prop;
		}else {
			throw new RuntimeException("Cient id is not specified in the config.properties file");
		}
	}
	
	
	public String getRefreshToken() {
		String prop=properties.getProperty("refresh_token");
		if(prop!=null) {
			return prop;
		}else {
			throw new RuntimeException("Cient id is not specified in the config.properties file");
		}
	}
}
