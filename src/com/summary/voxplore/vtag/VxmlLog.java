package com.summary.voxplore.vtag;

import org.w3c.dom.Node;

import com.summary.voxplore.vbase.VxmlObject;



public class VxmlLog  extends VxmlTagNode implements VoiceLogImpl{
	
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		System.out.println("Executing "+this.getNodeName());
	}
	
	public void execute(VxmlObject obj, Node fnode) {
		
		StringBuffer  logstr=new StringBuffer();
		if(fnode.getAttributes().getNamedItem("expr")!=null)
		{
			if(obj.getExecNode().getNodeName().equals("form"))
			{
				VxmlForm vform = (VxmlForm)obj.getExecNode();
				if(vform.getLocalVar().containsKey(fnode.getAttributes().getNamedItem("expr").getNodeValue()))
					logstr.append(vform.getLocalVar().get(fnode.getAttributes().getNamedItem("expr").getNodeValue()));
				else
					if(obj.getGlobalVar().containsKey(fnode.getAttributes().getNamedItem("expr").getNodeValue()))
					logstr.append(obj.getGlobalVar().get(fnode.getAttributes().getNamedItem("expr").getNodeValue()));
			}
		}
			
		for (int i=0; i<fnode.getChildNodes().getLength();i++)
		  {
			  Node node=fnode.getChildNodes().item(i);
			  if(node.getNodeType()==1 )
				  {System.out.println("Process Vxml object"+fnode.getChildNodes().item(i).getNodeName());
				  if(node.getAttributes().getNamedItem("expr")!=null)
					{
					  
					  if(obj.getExecNode().getNodeName().equals("form"))
						{
							VxmlForm vform = (VxmlForm)obj.getExecNode();
							if(vform.getLocalVar()!=null && vform.getLocalVar().containsKey(node.getAttributes().getNamedItem("expr").getNodeValue()))
								logstr.append(vform.getLocalVar().get(node.getAttributes().getNamedItem("expr").getNodeValue()));
							else
								if(obj.getGlobalVar()!=null && obj.getGlobalVar().containsKey(node.getAttributes().getNamedItem("expr").getNodeValue()))
								logstr.append(obj.getGlobalVar().get(node.getAttributes().getNamedItem("expr").getNodeValue()));
						}
					  
					}
				
						
				
				  }else if(node.getNodeType() == Node.TEXT_NODE )
				  {
			
					  logstr.append(node.getTextContent().trim()); 
				  }
		  }
		
		
		/*if(fnode.getTextContent()!=null && !fnode.getTextContent().trim().equals("") )
			logstr.append(fnode.getTextContent().trim());*/
			printVxmlLog(logstr.toString());
	}

	public void printVxmlLog(String str) {
		// TODO Auto-generated method stub
		System.out.println("<<log>>"+str+"<<log>>");
	}

}
