package com.cyta.util.test.datareaders;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;  
import javax.xml.parsers.DocumentBuilderFactory;  
import org.w3c.dom.Document;
import org.w3c.dom.Element; 
import org.w3c.dom.Node;  
import org.w3c.dom.NodeList;  

public class XMLReader {
	private XMLReader(){

	}
	private static Document doc;
	
	//load XML document in doc variable with the file path
	public static Document readXMLdoc(String filePath)
	{  
		try   
		{  
			//creating a constructor of file class and parsing an XML file  
			File file = new File(filePath);
			//an instance of factory that gives a document builder  
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
			//an instance of builder to parse the specified xml file  
			DocumentBuilder db = dbf.newDocumentBuilder();  
			doc = db.parse(file);  
			doc.getDocumentElement().normalize();  
			System.out.println("Root element: " + doc.getDocumentElement().getNodeName());  
			return doc;
			 
		}   
		catch (Exception e)   
		{  
			e.printStackTrace(); 
			System.out.println(e);
			return null;
		}  
	}
	
	//read child value with the child name and its parent tag name
	public static List<String> getValueByNodeName(String parentNode, String childNode)
	{
		List<String> childValues = new ArrayList<>();
		try {
			NodeList nodeList = doc.getElementsByTagName(parentNode);
			// nodeList is not iterable, so we are using for loop  
			for (int itr = 0; itr < nodeList.getLength(); itr++)   
			{  
				Node node = nodeList.item(itr);  
				System.out.println("\nNode Name :" + node.getNodeName());  
				if (node.getNodeType() == Node.ELEMENT_NODE)   
				{  
					Element eElement = (Element) node;  
					System.out.println(childNode +" :" + eElement.getElementsByTagName(childNode).item(0).getTextContent());
					childValues.add(eElement.getElementsByTagName(childNode).item(0).getTextContent());
				}  
			}  
			
			return childValues;
			
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

}
