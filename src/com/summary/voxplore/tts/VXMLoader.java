package com.summary.voxplore.tts;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
public class VXMLoader {
	public static HashMap<String,String> var_map = new HashMap<String,String>();
	public static CallMyText call =null;
	//=new CallMyText();
	
	public static DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	
	public static  SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
	
	public static Schema schema ;
	public static Source xmlFile;
	public static Validator validator;
	
	
	public static HashMap <String,Object> globalMap= new HashMap<String,Object>();
	public static HashMap <String,String> eventMap= new HashMap<String,String>();
	public static HashMap <String,String> executeMap= new HashMap<String,String>();
	/* currently no differentiation between property scope */
	public static HashMap <String,String> propertyMap= new HashMap<String,String>();
	
	public static void main(String argv[]) {
 
	

		File fXmlFile = new File("voxeo.vxml");
		//File docFile = new File("./xsd_vxml/vxml.xsd");
		boolean valid=true;
		
	
		
   	 
   	
		
		// To validate vxml before processing 
		 
		 /*try {
			   schema = schemaFactory.newSchema(docFile);
		   	   xmlFile = new StreamSource(fXmlFile);
		   	   validator = schema.newValidator();
			   validator.validate(xmlFile);
		   	   System.out.println("validating");
	    	    validator.validate(xmlFile);
	    	    System.out.println(xmlFile.getSystemId() + " is valid");
	    	    valid=true;
	    	  } catch (SAXException e) {
	    	    System.out.println(xmlFile.getSystemId() + " is NOT valid");
	    	    System.out.println("Reason: " + e.getLocalizedMessage() );
	    	  }catch(IOException io){System.out.println("Eating IO");}
	    	  */
	    	 
		//doc.getDocumentElement().normalize();
 if(valid){
	    DocumentBuilder dBuilder = null;
	 	Document doc = null;
		
		eventMap.put("catch", "catch");
		eventMap.put("help", "help");
		eventMap.put("noinput", "noinput");
		eventMap.put("nomatch", "nomatch");
		eventMap.put("error", "error");
		
		executeMap.put("audio", "audio");
		executeMap.put("assign", "assign");
		executeMap.put("clear", "clear");
		executeMap.put("data", "data");
		executeMap.put("disconnect", "disconnect");
		executeMap.put("exit", "exit");
		executeMap.put("goto", "goto");
		executeMap.put("if", "if");
		executeMap.put("return", "return");
		executeMap.put("prompt", "prompt");
		executeMap.put("reprompt", "reprompt");
		executeMap.put("submit", "submit");
		executeMap.put("var", "var");
		executeMap.put("throw", "throw");
		executeMap.put("script", "script");
		executeMap.put("foreach", "foreach");
		
		System.out.println("-----------------------");
		  try {
			  dBuilder=dbFactory.newDocumentBuilder();
			  doc=dBuilder.parse(fXmlFile);
			  System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			  NodeList nList = doc.getElementsByTagName("*");
			
			  //Element eNode = doc.getDocumentElement();
			  
			  //NodeList nList=eNode.getChildNodes();
			  
			  for (int i=0; i<nList.getLength();i++)
			  {
				 // System.out.println("Node of Root"+nList.item(i).getNodeName());
				  if(nList.item(i).getNodeName().equals("if"))
					  {
					  System.out.println("Node of Root"+nList.item(i).getNodeName());
					  System.out.println("Attribute of Root:"+nList.item(i).getAttributes().getNamedItem("cond").getNodeValue().trim());
					  for (int j=0; j<nList.item(i).getChildNodes().getLength();j++)
					  {
						  System.out.println("Child nodes " +nList.item(i).getChildNodes().item(j).getNodeName());
					  }
					 
					  }
				  
			  }
			 /* for (int i=0; i<nList.getLength();i++)
			  {
				  if(nList.item(i).getNodeType()==1)
					  {System.out.println("Node of Root"+nList.item(i).getNodeName());
					  if(nList.item(i).getNodeName().equalsIgnoreCase("var"))
						  processGlobalVar(nList.item(i));
					  else if(nList.item(i).getNodeName().equalsIgnoreCase("menu"))
						  processMenu(nList.item(i));
					  else if(nList.item(i).getNodeName().equalsIgnoreCase("form"))
						  processForm(nList.item(i));
					  else if(nList.item(i).getNodeName().equalsIgnoreCase("data"))
						  processData(nList.item(i));
					  else if(nList.item(i).getNodeName().equalsIgnoreCase("property"))
						  processProperty(nList.item(i));
					  else if(eventMap.containsKey(nList.item(i).getNodeName().toLowerCase()))
						  processEvent(nList.item(i));
					  else 
						  System.out.println("Undefined Tag "+nList.item(i).getNodeName());
					  }
			  }
*/
		  } catch (Exception e) {
				e.printStackTrace();
			  }finally{
				  doc=null;
				  dBuilder=null;
			  }
		System.out.println(globalMap);
		System.out.println(propertyMap);
		//call.destroySpeaker();
 }// end of valid if
	  
  }
 
  private static void processMenu(Node item) {
		// TODO Auto-generated method stub
	  short dtmf=0; HashMap <String,String>docId=new HashMap<String,String>();
	  if(item.getAttributes().getNamedItem("dtmf")!=null && item.getAttributes().getNamedItem("dtmf").equals("true"))
		  dtmf=1;
		 NodeList nList=item.getChildNodes();
		  for (int i=0; i<nList.getLength();i++)
		  {
			  if(nList.item(i).getNodeType()==1)
				  {System.out.println("Node of Root"+nList.item(i).getNodeName());
				  if(nList.item(i).getNodeName().equalsIgnoreCase("prompt"))
					   processPrompt(nList.item(i),null);
				  else if(nList.item(i).getNodeName().equalsIgnoreCase("property"))
				  	  processProperty(nList.item(i));				
				  else if(nList.item(i).getNodeName().equalsIgnoreCase("choice"))
				  {
					  processChoice(nList.item(i));
					  docId.put(nList.item(i).getAttributes().getNamedItem("dtmf").getNodeValue(), 
							  nList.item(i).getAttributes().getNamedItem("next").getNodeValue());
				  }
				  else 
					  System.out.println("Undefined Tag "+nList.item(i).getNodeName());
				  }
		  }
		  long sec=propertyMap.get("fetchtimeout")!=null?Long.valueOf(propertyMap.get("fetchtimeout")):3000;
		  try {
			Thread.sleep(sec);
			//recordVoice();
			//if(dtmf) || propertyMap.get("inputmodes")=="dtmf" scanDTMFKey();
			// if keymatches then get value from docId
			//processNodeById("ID")
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

private static void processChoice(Node item) {
	// TODO Auto-generated method stub
	System.out.println("choice :"+item.getTextContent());
}

private static void processEvent(Node item) {
		// TODO Auto-generated method stub
		System.out.println("Event handling block not implemented for "+item.getNodeName());
	}

private static void processProperty(Node node) {
		// TODO Auto-generated method stub
	if(node.getAttributes().getNamedItem("name")!=null && node.getAttributes().getNamedItem("value")!=null)
		propertyMap.put(node.getAttributes().getNamedItem("name").getNodeValue(), node.getAttributes().getNamedItem("value").getNodeValue());

	}

private static void processData(Node node) {
		// TODO Auto-generated method stub
		
	}

private static void processForm(Node node) {
		// TODO Auto-generated method stub
	String  id =null;
	if(node.getAttributes().getNamedItem("id")!=null)
		id= node.getAttributes().getNamedItem("id").getNodeValue();
	HashMap <String,String> localMap= new HashMap<String,String>();
	 NodeList nList=node.getChildNodes();
	  for (int i=0; i<nList.getLength();i++)
	  {
		  if(nList.item(i).getNodeType()==1)
			  {System.out.println("Node of Root"+nList.item(i).getNodeName());
			  if(nList.item(i).getNodeName().equalsIgnoreCase("var"))
				  localMap=processLocalVar(nList.item(i),localMap);
			  else if(nList.item(i).getNodeName().equalsIgnoreCase("filled"))
				  processFilled(nList.item(i),localMap); 
			  else if(eventMap.containsKey(nList.item(i).getNodeName().toLowerCase()))
				  processEvent(nList.item(i));
			  else if(nList.item(i).getNodeName().equalsIgnoreCase("field"))
				  processField(nList.item(i));
			  else if(nList.item(i).getNodeName().equalsIgnoreCase("block"))
				  processBlock(nList.item(i));
			  else if(nList.item(i).getNodeName().equalsIgnoreCase("property"))
				  processProperty(nList.item(i));
			  else 
				  System.out.println("Undefined Tag "+nList.item(i).getNodeName());
			  }
	  }
		
	}



private static void processField(Node item) {
	// TODO Auto-generated method stub
	
	String  name =null;
	HashMap <String,String>docId=new HashMap<String,String>();
	if(item.getAttributes().getNamedItem("name")!=null)
		name= item.getAttributes().getNamedItem("name").getNodeValue();
	System.out.println("Field Block" + item.getNodeName());
	HashMap <String,String> localMap= new HashMap<String,String>();
	if(name!=null)
		localMap.put(name, null);
	 NodeList nList=item.getChildNodes();
	  for (int i=0; i<nList.getLength();i++)
	  {
		
		  if(nList.item(i).getNodeType()==1)
			  {System.out.println("Node of Root"+nList.item(i).getNodeName());
			  if(nList.item(i).getNodeName().equalsIgnoreCase("var"))
				  localMap=processLocalVar(nList.item(i),localMap);
			  // only prompt can come in execute inside Field
			  else if(executeMap.containsKey(nList.item(i).getNodeName().toLowerCase()))
				  {
				  System.out.println("--------Field ---------------");
				  processExecute(nList.item(i),localMap);
				  System.out.println("--------Field ---------------");
				  
				  }
			  else if(nList.item(i).getNodeName().equalsIgnoreCase("filled"))
			  {
				  processFilled(nList.item(i),localMap);
			  }
			  else if(nList.item(i).getNodeName().equalsIgnoreCase("property"))
			  {
				  processProperty(nList.item(i));
			  } 
			  else if(eventMap.containsKey(nList.item(i).getNodeName().toLowerCase()))
				  processEvent(nList.item(i));
			  else if(nList.item(i).getNodeName().equalsIgnoreCase("option"))
			  {
				  processChoice(nList.item(i));
				  if(nList.item(i).getAttributes().getNamedItem("dtmf")!=null)
				  docId.put(nList.item(i).getAttributes().getNamedItem("dtmf").getNodeValue(), 
						  nList.item(i).getAttributes().getNamedItem("value").getNodeValue());
				  if(nList.item(i).getNextSibling()!=null && !nList.item(i).getNextSibling().getNodeName().equals("option"))
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					  
			  }
			  else 
				  System.out.println("Undefined Tag "+nList.item(i).getNodeName());
			  }
	  }
	  System.out.println("docID "+docId);
}

private static void processFilled(Node item, HashMap<String, String> localMap) {
	// TODO Auto-generated method stub

	 NodeList nList=item.getChildNodes();
	  for (int i=0; i<nList.getLength();i++)
	  {
		  if(nList.item(i).getNodeType()==1)
			  {System.out.println("Node of Root"+nList.item(i).getNodeName());
			  if(nList.item(i).getNodeName().equalsIgnoreCase("var"))
				  localMap=processLocalVar(nList.item(i),localMap);
			  else if(executeMap.containsKey(nList.item(i).getNodeName().toLowerCase()))
				  processExecute(nList.item(i),localMap);
			  else 
				  System.out.println("Undefined Tag "+nList.item(i).getNodeName());
			  }
	  }
}
private static void processBlock(Node item) {
	// TODO Auto-generated method stub
	HashMap <String,String> localMap= new HashMap<String,String>();
	 NodeList nList=item.getChildNodes();
	  for (int i=0; i<nList.getLength();i++)
	  {
		  if(nList.item(i).getNodeType()==1)
			  {System.out.println("Node of Root"+nList.item(i).getNodeName());
			  if(nList.item(i).getNodeName().equalsIgnoreCase("var"))
				  localMap=processLocalVar(nList.item(i),localMap);
			  else if(executeMap.containsKey(nList.item(i).getNodeName().toLowerCase()))
				  processExecute(nList.item(i),localMap);
			  else 
				  System.out.println("Undefined Tag "+nList.item(i).getNodeName());
			  }
	  }
}

private static void processExecute(Node item, HashMap<String, String> localMap) {
	// TODO Auto-generated method stub
	
	// NodeList nList=item.getChildNodes();
	 		  if(item.getNodeName().equalsIgnoreCase("clear"))
			  {// check for parent map if var to be cleared exist in that localmap it will be null
			  
			  }
			  else if(item.getNodeName().equalsIgnoreCase("assign"))
			  {
				  // check for parent map if var to be assign exist in that localmap it will be assigned to this value
				  Node ele=item;

		    		if(ele.getAttributes().getNamedItem("expr")!=null && localMap.containsKey(ele.getAttributes().getNamedItem("name").getNodeValue()))
		    			localMap.put(ele.getAttributes().getNamedItem("name").getNodeValue(), ele.getAttributes().getNamedItem("expr").getNodeValue());
		    		else if(ele.getAttributes().getNamedItem("expr")!=null && globalMap.containsKey(ele.getAttributes().getNamedItem("name").getNodeValue()))
			    		globalMap.put(ele.getAttributes().getNamedItem("name").getNodeValue(), ele.getAttributes().getNamedItem("expr").getNodeValue());
		    		else
		    			System.out.println("no such var found !");
			  }			  
			  else if(item.getNodeName().equalsIgnoreCase("goto"))
			  {
				  // check for the cond 
			  }
			  else if(item.getNodeName().equalsIgnoreCase("prompt"))
			  {
				  // check for the cond 
				 // System.out.println("inside prompt"+item.getTextContent());
				  processPrompt(item,localMap);
			  }
			  else if(item.getNodeName().equalsIgnoreCase("reprompt"))
			  {
				  // check for the cond 
			  }
			  else if(item.getNodeName().equalsIgnoreCase("return"))
			  {
				  // check for the cond 
			  }
			  else if(item.getNodeName().equalsIgnoreCase("disconnect"))
			  {
				  // check for the cond 
			  }
			  else if(item.getNodeName().equalsIgnoreCase("exit"))
			  {
				  // check for the cond 
			  }
			  else 
				  System.out.println("Undefined Tag "+item.getNodeName());
	
	
}

private static void processPrompt(Node item, HashMap<String, String> localMap) {
	// TODO Auto-generated method stub
	NodeList nlList = item.getChildNodes();
	  String str="";
		for (int i=0; i<nlList.getLength();i++)
		{
			  Node nValue = (Node) nlList.item(i);
			  if(nValue.getNodeType()==3)
				  str=str+nValue.getNodeValue();
			  else if(nValue.getNodeType()==1)
			  {
				  if(nValue.getNodeName().equals("value"))
				  {
					  String key=nValue.getAttributes().getNamedItem("expr").getNodeValue();
					  Iterator it=null;
					  if(localMap.containsKey(key))
						  str=str+localMap.get(key);
					  else if(globalMap.containsKey(key))
						  str=str+globalMap.get(key);
			         

				  
				  }else
					  if(nValue.getNodeName().equals("audio"))
					  {
						  System.out.println("will play the audio from "+nValue.getAttributes().getNamedItem("expr").getNodeValue());
				         

					  
					  }
			  }
		}
System.out.println(str);
	//return str; 
}

private static HashMap<String, String> processLocalVar(Node item,
		HashMap<String, String> localMap) {
	// TODO Auto-generated method stub
	if(item.getAttributes().getNamedItem("expr")!=null)
		localMap.put(item.getAttributes().getNamedItem("name").getNodeValue(), item.getAttributes().getNamedItem("expr").getNodeValue());
		else
			localMap.put(item.getAttributes().getNamedItem("name").getNodeValue(),null);
	return localMap;
}

private static void processGlobalVar(Node node) {
		// TODO Auto-generated method stub

	 	if(node.getAttributes().getNamedItem("expr")!=null)
 			globalMap.put(node.getAttributes().getNamedItem("name").getNodeValue(), node.getAttributes().getNamedItem("expr").getNodeValue());
 		else
 			globalMap.put(node.getAttributes().getNamedItem("name").getNodeValue(),null);
	}

private static String getTagValue(String sTag, Element eElement) {
	NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
 
        Node nValue = (Node) nlList.item(0);

	return nValue.getNodeValue();
  }
 
  private static void processBlockTag(Element eElement)
  {
	  System.out.println("inside process block");
	  String str="";
	  //define a  map to store local vars
	  HashMap<String , String> local_map= new HashMap<String , String>();
	  Node ele=null;
		for (int i=0; i<eElement.getChildNodes().getLength();i++)
  	{
  		short j=eElement.getChildNodes().item(i).getNodeType();
  	
	    	 switch (j){
		    	 case 3:
		    		 str=str+eElement.getChildNodes().item(i).getNodeValue();
		    		 break;
		    	 case 1:
		    		 if(eElement.getChildNodes().item(i).getNodeName().equals("var"))
			    		{
		    			 ele=eElement.getChildNodes().item(i);
		    			 if(ele.getAttributes().getNamedItem("expr")!=null)
		 		    		local_map.put(ele.getAttributes().getNamedItem("name").getNodeValue(), ele.getAttributes().getNamedItem("expr").getNodeValue());
		 		    		else
		 		    			local_map.put(ele.getAttributes().getNamedItem("name").getNodeValue(),null);
			    		}
		    		 else if(eElement.getChildNodes().item(i).getNodeName().equals("assign"))
		    		 {
		    			 ele=eElement.getChildNodes().item(i);

				    		if(ele.getAttributes().getNamedItem("expr")!=null && local_map.containsKey(ele.getAttributes().getNamedItem("name").getNodeValue()))
				    			local_map.put(ele.getAttributes().getNamedItem("name").getNodeValue(), ele.getAttributes().getNamedItem("expr").getNodeValue());
				    		else if(ele.getAttributes().getNamedItem("expr")!=null && var_map.containsKey(ele.getAttributes().getNamedItem("name").getNodeValue()))
					    		var_map.put(ele.getAttributes().getNamedItem("name").getNodeValue(), ele.getAttributes().getNamedItem("expr").getNodeValue());


		    		 }
		    		else if(eElement.getChildNodes().item(i).getNodeName().equals("value")){}
		    		//str=str+var_map.get(eElement.getChildNodes().item(i).getAttributes().getNamedItem("expr").getNodeValue());
		    		else if(eElement.getChildNodes().item(i).getNodeName().equals("prompt"))
		    		{
		    			String text=//eElement.getChildNodes().item(i).getNodeValue();
		    			//checkiftext contains <value expr>
		    			parsePromptTag(eElement.getChildNodes().item(i));
		    			System.out.println("text>>"+text);
		    			//str=str+parseValueTag(text);
		    			
		    		} 
		    		 break;
	    		 default:
	    			 break;
	    	 }
  
  	}
// str=str.replaceAll("'", "");
		System.out.println(">>"+str+"<<"+local_map);
		//if(!str.equals(""))
			//call.callSpeaker(str);

  }
  private static String parsePromptTag(Node node)
  {
	  NodeList nlList = node.getChildNodes();
	  String str="";
		for (int i=0; i<nlList.getLength();i++)
		{
			  Node nValue = (Node) nlList.item(i);
			  if(nValue.getNodeType()==3)
				  str=str+nValue.getNodeValue();
			  else if(nValue.getNodeType()==1)
			  {
				  if(nValue.getNodeName().equals("value"))
				  {
					  String key=nValue.getAttributes().getNamedItem("expr").getNodeValue();
					  Iterator it= var_map.keySet().iterator();
			          while(it.hasNext()){
			        	if(  key.equals(it.next().toString()))
			        		str=str+var_map.get(key);
			          }

				  
				  }
			  }
		}

	return str; 
  }
  
  private static String parseValueTag(String text) {
	  String REGEX = "<value[\\s]+expr[\\s]*=[\\s]*\"[a-zA-Z]*\"[\\s]*/>";
	  System.out.println("text["+text+"]");
	   Pattern p = Pattern.compile(REGEX);
       Matcher m = p.matcher(text); // get a matcher object
       int count = 0;
       while(m.find()) {
           count++;
          Iterator it= var_map.keySet().iterator();
          while(it.hasNext()){
        	  String key=it.next().toString();
        	
           if(m.group().contains(key))
               text=text.replaceAll(m.group(), (String)var_map.get(key));
          }
     
       }
     
       return text;
	  }
 
}