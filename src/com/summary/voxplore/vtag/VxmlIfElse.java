package com.summary.voxplore.vtag;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.summary.voxplore.handler.ForEachHandler;
import com.summary.voxplore.handler.HandlerException;
import com.summary.voxplore.handler.IfElseHandler;
import com.summary.voxplore.handler.VxmlTagHandler;
import com.summary.voxplore.vbase.VxmlObject;

public class VxmlIfElse extends VxmlTagNode{

	private IfElseHandler hh;
	public VxmlIfElse(String nodeName) {
		this.nodeName=nodeName;

	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		System.out.println("Executing "+this.getNodeName());
	}
	
	public void execute(VxmlObject obj, Node fnode) {
		
		
		//Node vname = fnode.getAttributes().getNamedItem("name");
		String cond=fnode.getAttributes().getNamedItem("cond").getNodeValue().trim();
		boolean has=false;

		List <Node>childlist;
		cond=getConditionString(obj,cond);
		// evaluate condition 
		try {
			hh = (IfElseHandler)VxmlTagHandler.getTagHandler(this.getNodeName());
			has = hh.evaluateCondition(cond);	
			System.out.println("resultant result obtained "+has);
		} catch (HandlerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(has)
			processOnlyIf( obj,  fnode);
		else{
			childlist= new ArrayList<Node>();
			for(int i=0; i<fnode.getChildNodes().getLength();i++)
			{
				if(fnode.getChildNodes().item(i).getNodeName().equals("else")  ||
						fnode.getChildNodes().item(i).getNodeName().equals("elseif") )
						childlist.add(fnode.getChildNodes().item(i));
				
				//System.out.println("if():"+fnode.getChildNodes().item(i).getNodeName());
			}
			if(childlist.size()>0)
				processElseIf(obj, fnode ,childlist);
			
		 }
		
		
		
		
			
	}

	private String getConditionString(VxmlObject obj , String cond) {
		// TODO Auto-generated method stub
		
		Map <String,String>condmap;
		Iterator <String> itr;
		if(obj.getExecNode().getNodeName().equals("form"))
		{
			VxmlForm vform = (VxmlForm)obj.getExecNode();
			condmap=vform.getLocalVar();
			itr=condmap.keySet().iterator();
			// replace all local vars first
			while(itr.hasNext())
			{
				String key=itr.next();
				if(cond.contains(key))
				{
					cond=cond.replaceAll(key, condmap.get(key));
					
				}
				
			}
			condmap=obj.getGlobalVar();
			itr=condmap.keySet().iterator();
			// replace all global vars second
			while(itr.hasNext())
			{
				String key=itr.next();
				if(cond.contains(key))
				{
					cond=cond.replaceAll(key, condmap.get(key));
					
				}
				
			}
			
			System.out.println("resultant condition to be passed "+cond);
		}
		return cond;
	}

	private void processElseIf(VxmlObject obj, Node fnode, List<Node> childlist) {
		// TODO Auto-generated method stub
		System.out.println(" going in else if block");
		VxmlTagNode vtagnode;
		String cond="";
		boolean has=false;
		for (int i=0; i<childlist.size();i++)
		{
			if(childlist.get(i).getNodeName().equals("else"))
			{
				processIf( obj,  childlist.get(i)); 
				break;
			}
			cond = childlist.get(i).getAttributes().getNamedItem("cond").getNodeValue().trim();
			cond=getConditionString(obj,cond);
			try {
				hh = (IfElseHandler)VxmlTagHandler.getTagHandler(this.getNodeName());
				has = hh.evaluateCondition(cond);	
				System.out.println("resultant result obtained in processElseIf()"+has);
			} catch (HandlerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(has)
			{
				processIf( obj,  childlist.get(i)); 
				break;
			}
		}
	}

	private void processIf(VxmlObject obj, Node fnode) {
		// TODO Auto-generated method stub
		VxmlTagNode vtagnode;
		Node node;
		boolean sibling=true;
		while (sibling)
		{
			node=fnode.getNextSibling();
			
			if(node==null || ( node.getNodeName().equals("else") || 
					node.getNodeName().equals("elseif")))
				sibling=false;
			if(!sibling ) break;
			
			vtagnode=VxmlTagBuilderFactory.getVxmlTag(node.getNodeName());
			System.out.println(node.getNodeName()+"processIf().........."+vtagnode.getClass());
			vtagnode.execute(obj,node);
			fnode=node;
		
			
		}
	}
	
	private void processOnlyIf(VxmlObject obj, Node fnode) {
		// TODO Auto-generated method stub
		VxmlTagNode vtagnode;
		Node node;
		boolean sibling=true;
		
		System.out.println(fnode.getNodeName()+"processOnlyIf().........."+fnode.getChildNodes().getLength());
		
		
		for(int i=0; i<fnode.getChildNodes().getLength();i++)
		{
			if(fnode.getChildNodes().item(i).getNodeName().equals("else")  ||
					fnode.getChildNodes().item(i).getNodeName().equals("elseif") )
					break;
			vtagnode=VxmlTagBuilderFactory.getVxmlTag(fnode.getChildNodes().item(i).getNodeName());
			System.out.println(fnode.getNodeName()+"processOnlyIf().........."+vtagnode.getClass());
			vtagnode.execute(obj,fnode.getChildNodes().item(i));
			//System.out.println("if():"+fnode.getChildNodes().item(i).getNodeName());
		}
		/*while (sibling)
		{
			node=fnode.getNextSibling();
			
			if(node==null || ( node.getNodeName().equals("else") || 
					node.getNodeName().equals("elseif")))
				sibling=false;
			if(!sibling ) break;
			
			vtagnode=VxmlTagBuilderFactory.getVxmlTag(node.getNodeName());
			System.out.println(node.getNodeName()+"processIf().........."+vtagnode.getClass());
			vtagnode.execute(obj,node);
			fnode=node;
		
			
		}*/
	}

}
