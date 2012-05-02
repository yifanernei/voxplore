package com.summary.voxplore.vtag;

import org.w3c.dom.Node;

import com.summary.voxplore.vbase.VxmlObject;

public class VxmlBlock extends VxmlTagNode implements VxmlVoiceImpl{

	public VxmlBlock(String nodeName) {
		this.nodeName=nodeName;

	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		System.out.println("Executing "+this.getNodeName());
	}
	
	public void execute(VxmlObject obj, Node fnode) {
		// TODO Auto-generated method stub
		System.out.println("Executing "+this.getNodeName());
		
		/*if(fnode.getTextContent()!=null && !fnode.getTextContent().trim().equals("") )
			printVxmlVoice(fnode.getTextContent().trim());*/
		StringBuffer speakBuffer=new StringBuffer();
		  for (int i=0; i<fnode.getChildNodes().getLength();i++)
		  {
			  Node node=fnode.getChildNodes().item(i);
			  if(node.getNodeType()==1 )
				  {System.out.println("Process Vxml object"+fnode.getChildNodes().item(i).getNodeName());
				  if(node.getNodeName().equals("value"))
				  {
				
					  if(node.getAttributes().getNamedItem("expr")!=null)
						{
						  if(obj.getExecNode().getNodeName().equals("form"))
							{
								VxmlForm vform = (VxmlForm)obj.getExecNode();
								if(vform.getLocalVar().containsKey(node.getAttributes().getNamedItem("expr").getNodeValue()))
									speakBuffer.append(vform.getLocalVar().get(node.getAttributes().getNamedItem("expr").getNodeValue()));
								else
									if(obj.getGlobalVar().containsKey(node.getAttributes().getNamedItem("expr").getNodeValue()))
										speakBuffer.append(obj.getGlobalVar().get(node.getAttributes().getNamedItem("expr").getNodeValue()));
							}
						  
						}
					 
				  }
				  else{
				  VxmlTagNode vtagnode=VxmlTagBuilderFactory.getVxmlTag(node.getNodeName());
				  System.out.println(".........."+vtagnode.getClass());
				
						vtagnode.execute(obj,node);
				  }
				
				  }else if(node.getNodeType() == Node.TEXT_NODE )
				  {
					  speakBuffer.append(node.getTextContent().trim()); 
				  }
		  }
		  
		  printVxmlVoice(speakBuffer.toString());
	}

	public void printVxmlVoice(String str) {
		if(!str.equals(""))
		 System.out.println("<<speak>>"+str+"<<speak>>");
		
	}

}
