package com.cyta.util.test.datareaders;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
	private PropertiesReader(){

	}

private static Properties properties;
	
	//load Properties document in properties variable with the file path 
	public static Properties readProperties(String propertyFilePath){
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
				return properties;
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		}	
		return null;
	}
	
	//get value with the key name
	public static String getPropertyValue(String propertyName) {
		String propValue = properties.getProperty(propertyName);
		if(propValue!= null) return propValue;
		else throw new RuntimeException("propValue not specified in the Configuration.properties file.");
	}
	
	//get value with the key name and the file path
	public static String getPropertyValue(String filePath, String propertyName) {
		readProperties(filePath);
		return getPropertyValue(propertyName);
	}
	
}
