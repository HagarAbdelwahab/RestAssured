package com.cyta.util.test.constants;

public class Constants {
	private Constants(){

	}
	
	public static final String FILE_SEPARATOR= System.getProperty("file.separator");
	
	public static final String JAVA_FOLDER_MAIN= System.getProperty("user.dir")+
			FILE_SEPARATOR+"src"+FILE_SEPARATOR
			+"main"+FILE_SEPARATOR+"java"+FILE_SEPARATOR;
	
	public static final String FOLDER_TEST= System.getProperty("user.dir")+
			FILE_SEPARATOR+"src"+FILE_SEPARATOR
			+"test"+FILE_SEPARATOR;
	
	public static final String TEST_DATA_PATH=FOLDER_TEST+"resources"+FILE_SEPARATOR;
	public static final String CONFIG_PATH=JAVA_FOLDER_MAIN+"com"+FILE_SEPARATOR+"cyta"

			+FILE_SEPARATOR+"resources"+FILE_SEPARATOR;
	public static final String APK_INFO=JAVA_FOLDER_MAIN+"";
	public static final String APP_CREDENTIALS_CONFIG_PATH=JAVA_FOLDER_MAIN+"";

}

