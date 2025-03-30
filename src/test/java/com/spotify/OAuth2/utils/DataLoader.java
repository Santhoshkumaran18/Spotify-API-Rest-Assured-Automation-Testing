package com.spotify.OAuth2.utils;

import java.util.Properties;

public class DataLoader {
	private  final Properties properties;
	private static  DataLoader dataLoader;
	
	private DataLoader() {
		properties=PropertyUtils.propertyLoader("src/test/resources/data.properties");
		
	}
	
	public static DataLoader getInstance() {
	
			if(dataLoader==null) {
				dataLoader=new DataLoader();
			}
			return dataLoader;
	}
	
	public String getPlaylistid() {
		String prop=properties.getProperty("get_playlist_id");
		if(prop!=null) {
			return prop;
		}else {
			throw new RuntimeException("Cient id is not specified in the config.properties file");
		}
	}
	
	
	public String getUpdatePlaylistID() {
		String prop=properties.getProperty("update_playlist_id");
		if(prop!=null) {
			return prop;
		}else {
			throw new RuntimeException("Cient id is not specified in the config.properties file");
		}
	}
	
	
	
	
	
}
