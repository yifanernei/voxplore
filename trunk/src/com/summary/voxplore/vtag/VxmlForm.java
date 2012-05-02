package com.summary.voxplore.vtag;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.summary.voxplore.vbase.VxmlObject;

public class VxmlForm extends VxmlTagNode{
	
	private  Map<String, String> attrib=null;
	private Map<String, String> localVar=null;

	public VxmlForm(String nodeName) {
		this.nodeName=nodeName;

	}
	
	
	

	public Map<String, String> getLocalVar() {
		return localVar;
	}




	@Override
	public void execute() {
		// TODO Auto-generated method stub
		System.out.println("Executing "+this.getNodeName());
	}
	public void execute(VxmlObject obj, Node fnode) {
		// TODO Auto-generated method stub
		System.out.println("Executing "+this.getNodeName());
	
		// Load attributes of tag
		if(fnode.getAttributes().getLength()>0){
			attrib=new HashMap<String, String>();
			int i=0;
			while(i<fnode.getAttributes().getLength())
			{
				attrib.put(fnode.getAttributes().item(i).getNodeName(), fnode.getAttributes().item(i).getNodeValue());
				i++;
			}
			
		}
		System.out.println("atrrib "+attrib);
		//Load LocalVar 
		
		NodeList nList = fnode.getChildNodes();
		localVar=new HashMap<String, String>();
		  for (int i=0; i<nList.getLength();i++)
		  {
			  Node node=nList.item(i);
			  if(node.getNodeType()==1 && node.getNodeName().equals("var"))
				  {
				 System.out.println( node.getAttributes().getNamedItem("expr").getNodeValue() );
				 if(node.getAttributes().getNamedItem("expr")!=null)
					  localVar.put(node.getAttributes().getNamedItem("name").getNodeValue(), 
							  node.getAttributes().getNamedItem("expr").getNodeValue());
			 		else
			 			  localVar.put(node.getAttributes().getNamedItem("name").getNodeValue(),null);
				  }

		  }
		  // point executing node of VxmlObject to current  Form 
		  
		  obj.setExecNode(this);
		  for (int i=0; i<nList.getLength();i++)
		  {
			  Node node=nList.item(i);
			  if(node.getNodeType()==1 )
				  {System.out.println("Process Vxml object"+nList.item(i).getNodeName());
				  
				  VxmlTagNode vtagnode=VxmlTagBuilderFactory.getVxmlTag(node.getNodeName());
				  System.out.println(".........."+vtagnode.getClass());
				
						vtagnode.execute(obj,node);
				
				  }
		  }
		  System.out.println("localVar "+localVar);
 
		  
	}

	
}
