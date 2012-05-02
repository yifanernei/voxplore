package com.summary.voxplore.validator;
import java.io.File;
import java.io.IOException;


import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.*;
import org.xml.sax.SAXException;


public class VxmlValidator 
{
	 
	//URL schemaFile = new URL("http://java.sun.com/xml/ns/j2ee/web-app_2_4.xsd");
	
	 private static File schemaDocument = null;
	
	 private static SchemaFactory schemaFactory = SchemaFactory
      .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
	 
	private static Validator validator=null;
	
	private static  Source xmlFile = null;
	
	static {
		
		try{
			schemaDocument=	new 
				File("C:\\Documents and Settings\\ssharma\\My Documents\\Kirusa_doc\\xsd_vxml\\vxml.xsd");
			Schema schema = schemaFactory.newSchema(schemaDocument); 
			validator = schema.newValidator();
		}catch(Exception ex){System.out.println(" Schema Definition document is not defined .");
				ex.printStackTrace();}
		
	}
	
	 public static boolean isValidSchema(File filename)
	 {
		 
		 boolean success=false;
		 try {
			 	xmlFile = new StreamSource(filename);
	    		validator.validate(xmlFile);				
	    	    System.out.println(xmlFile.getSystemId() + " is valid");
	    	    success=true;
	    	  } catch (NullPointerException e) {
	    		  System.out.println(" unable to open filename "+filename+":"+e.getMessage());
				} catch (IOException e) {
	    		  System.out.println(" unable to open filename "+filename+":"+e.getMessage());
				} catch (SAXException e) {
	    	    System.out.println(xmlFile.getSystemId() + " is NOT valid" +
	    	    		"\nReason: " + e.getLocalizedMessage());
	    	   
	    	  }finally{
	    		  xmlFile=null;
	    	  }
		 return success;
	 }
  /*public static void main (String args[]) 
  {
	  System.out.println(isValidSchema("abc.xml"));
  }*/
      
    
}
