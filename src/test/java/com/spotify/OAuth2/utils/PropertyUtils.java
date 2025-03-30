package com.spotify.OAuth2.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Properties;

public class PropertyUtils {
	
	public static Properties propertyLoader(String file) {
		Properties properties=new Properties();
		BufferedReader reader;
		
		try {
			reader=new BufferedReader(new FileReader(file));
			try {
				properties.load(reader);
				reader.close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Failed to load the properties file" + file);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Properties file not found at " + file);
		}
		return properties;
		
	}
}
