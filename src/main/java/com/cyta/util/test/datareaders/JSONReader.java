package com.cyta.util.test.datareaders;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
public class JSONReader {
	
    private static JSONArray objList;
    
	//read the JSON Doc and return JSON Array of it
	public static JSONArray readJSONdoc (String filePath)
	{  
		
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader(filePath));
	 
	        objList = (JSONArray) obj;
	        System.out.println(objList);
	        return objList;
			 
		}   
		catch (Exception e)   
		{  
			e.printStackTrace(); 
			System.out.println(e);
			return null;
		}  
	}
	
	public JSONArray getObjList() {	

		return objList;
	}

	//read child value with the child name and its parent tag name
	public static List<String> getValueByName(String parentKey, String childKey)
	{
		List<String> childValues = new ArrayList<>();
		try {

			for (Object o : objList) {
				JSONObject parentObject = (JSONObject) ((JSONObject) o).get(parentKey);
				String childValue = (String) parentObject.get(childKey);
				System.out.println(childValue);
				childValues.add(childValue);
			}

			return childValues;
			
		} catch (Exception e) {

			e.printStackTrace(); 
			return Collections.emptyList();
		}
	}
	
}
