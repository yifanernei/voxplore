package com.summary.voxplore.vbase;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.summary.voxplore.vtag.VxmlTagBuilderFactory;
import com.summary.voxplore.vtag.VxmlTagNode;

public class VxmlObject {
	
	private Map<String, String> globalVar;
	private Map<String, String> globalProp;
	private VxmlTagNode execNode;
	


	//private VxmlTagBuilderFactory  vxmlfactory;
	
	
	public VxmlObject() {
		//super();
		this.globalVar = new HashMap<String, String>();
		this.globalProp = new HashMap<String, String>();
	
	}
	public VxmlTagNode getExecNode() {
		return execNode;
	}
	public void setExecNode(VxmlTagNode execNode) {
		this.execNode = execNode;
	}
	public Map<String, String> getGlobalVar() {
		return globalVar;
	}
	
	public void setGlobalVar(String key ,String value)
	{
		globalVar.put(key , value);
	}
	
	public void setGlobalProp(String key ,String value)
	{
		globalProp.put(key , value);
	}
	public void setGlobalVar(Map<String, String> globalVar) {		
		this.globalVar = globalVar;
	}
	public Map<String, String> getGlobalProp() {
		return globalProp;
	}
	public void setGlobalProp(Map<String, String> globalProp) {
		this.globalProp = globalProp;
	}
	
	public void fillGlobalVars(NodeList nList)
	{
		for (int i=0; i<nList.getLength();i++)
		  {
			  Node node=nList.item(i);
			  if(node.getNodeType()==1 && node.getParentNode().getNodeName().equals("vxml") )
				  {System.out.println("Node of Root"+nList.item(i).getNodeName());
				  if(node.getAttributes().getNamedItem("expr")!=null)
					  this.setGlobalVar(node.getAttributes().getNamedItem("name").getNodeValue(), node.getAttributes().getNamedItem("expr").getNodeValue());
			 		else
			 			 this.setGlobalVar(node.getAttributes().getNamedItem("name").getNodeValue(),null);
				  }
		  }
	}
	
	public void fillGlobalProps(NodeList nList)
	{
		for (int i=0; i<nList.getLength();i++)
		  {
			  Node node=nList.item(i);
			  if(node.getNodeType()==1 && node.getParentNode().getNodeName().equals("vxml"))
				  {System.out.println("Node of Root"+nList.item(i).getNodeName());
				  if(node.getAttributes().getNamedItem("expr")!=null)
					  this.setGlobalProp(node.getAttributes().getNamedItem("name").getNodeValue(), node.getAttributes().getNamedItem("expr").getNodeValue());
			 		else
			 			 this.setGlobalProp(node.getAttributes().getNamedItem("name").getNodeValue(),null);
				  }
		  }
	}
	/*
	 * Only below tags are available under vxml  tag
	 * <catch>   <error>   <form>   <help>   <link>  
	 *  <menu>   <meta>   <noinput>   <nomatch>  
	 *   <property>   <script>   <var> 
	 */
	public void processForm(Element eNode)
	{
		//vxmlfactory	= new VxmlTagBuilderFactory();
		VxmlTagNode vtagnode ;
		
		NodeList nList = eNode.getChildNodes();
		boolean form=true;
		  for (int i=0; i<nList.getLength();i++)
		  {
			  Node node=nList.item(i);
			  if(node.getNodeType()==1 )
				  {System.out.println("Process Vxml object"+nList.item(i).getNodeName());
				  
				  vtagnode=VxmlTagBuilderFactory.getVxmlTag(node.getNodeName());
				  System.out.println(".........."+vtagnode.getClass());
					if(node.getNodeName().equals("form") ){
						if( form ) 
							vtagnode.execute(this,node);
						form=false;
					}
					else
						vtagnode.execute(this,node);
				
				  }
		  }

		
	}
	

}
