package com.summary.voxplore;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.summary.voxplore.validator.VxmlValidator;
import com.summary.voxplore.vbase.VxmlObject;



public class VxmlInterpreter {
	
	private static DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	//private static VxmlTagBuilderFactory  vxmlfactory;
	
	public static void main(String args[])
	{
		File fXmlFile = new File("voxeo.vxml");
		
		//StartVoiceEngine();

		if(VxmlValidator.isValidSchema(fXmlFile))
		{
			DocumentBuilder dBuilder = null;
		 	Document doc = null;
			try{
				 try {
					dBuilder=dbFactory.newDocumentBuilder();
				} catch (ParserConfigurationException e) {
					System.out.println("ParserConfigurationException :"+e.getMessage());	
					System.exit(1);
					}
				 try {
					doc=dBuilder.parse(fXmlFile);
					System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
					  VxmlObject vObj= new VxmlObject();					
					  NodeList nList = doc.getElementsByTagName("var");				
					  vObj.fillGlobalVars(nList);
					  nList = doc.getElementsByTagName("property");
					  vObj.fillGlobalProps(nList);
					  vObj.processForm(doc.getDocumentElement());
					  
					  //Element eNode = doc.getDocumentElement();
					  //NodeList nList=eNode.getChildNodes();
					  

					  
					  
					
					System.out.println(vObj.getGlobalProp() +":"+vObj.getGlobalVar());
					
				} catch (SAXException e) {
					System.out.println("SAXException :"+e.getMessage());	
				} catch (IOException e) {
					System.out.println("IOException :"+e.getMessage());	
				}
			}finally {				
				doc = null;
				dBuilder = null;
			}
			
		}else
			System.out.println("given vxml is not valid");
	/*VxmlTagBuilderFactory  vxmlfactory
	= new VxmlTagBuilderFactory();
	
	VxmlTagNode vform =vxmlfactory.getVxmlTag("form");
	
	vform.execute();*/
	

	}

}
