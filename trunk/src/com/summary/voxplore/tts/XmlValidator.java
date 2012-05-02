package com.summary.voxplore.tts;
import java.io.File;
import java.net.URL;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.*;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;


public class XmlValidator 
{
  public static void main (String args[]) 
  {
      File docFile = new File("C:\\Documents and Settings\\ssharma\\My Documents\\Kirusa_doc\\xsd_vxml\\vxml.xsd");
      try
      {
    	  //URL schemaFile = new URL("http://java.sun.com/xml/ns/j2ee/web-app_2_4.xsd");
    	 
    	  System.out.println("::"+System.currentTimeMillis());
    	  SchemaFactory schemaFactory = SchemaFactory
    	      .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
    	  Schema schema = schemaFactory.newSchema(docFile);
    	  System.out.println(System.currentTimeMillis());
    	  Validator validator = schema.newValidator();
    	  System.out.println("validating");
    	  
    	  Source xmlFile = new StreamSource(new File("voxeo.vxml"));
    	  
    	 /* DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
          domFactory.setNamespaceAware(true); // never forget this
          DocumentBuilder builder = domFactory.newDocumentBuilder();
          Document doc = builder.parse(new File("voxeo.vxml"));*/
          
        //  DOMSource xmlFile = new DOMSource(doc);
         // DOMResult result = new DOMResult();
   	  
    	  
    	  try {
    	    validator.validate(xmlFile);
    	    System.out.println(xmlFile.getSystemId() + " is valid");
    	  } catch (SAXException e) {
    	    System.out.println(xmlFile.getSystemId() + " is NOT valid");
    	    System.out.println("Reason: " + e.getLocalizedMessage() );
    	  }
    	  System.out.println(System.currentTimeMillis());  
    	  xmlFile = new StreamSource((new File("song.jsp")));
    	  try {
      	    validator.validate(xmlFile);
      	    System.out.println(xmlFile.getSystemId() + " is valid");
      	  } catch (SAXException e) {
      	    System.out.println(xmlFile.getSystemId() + " is NOT valid");
      	    System.out.println("Reason: " + e.getLocalizedMessage() );
      	  }

      	System.out.println(System.currentTimeMillis());
  

     }
     catch (Exception e) 
     {
         System.out.print("Problem parsing the file.");
         e.printStackTrace();
     }
  }
}
