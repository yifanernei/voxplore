package com.summary.voxplore.vtag;

import org.w3c.dom.Node;

import com.summary.voxplore.vbase.VxmlObject;



public class VxmlAssign  extends VxmlTagNode {
	
	public VxmlAssign(String nodeName) {
		this.nodeName=nodeName;

	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		System.out.println("Executing "+this.getNodeName());
	}
	
	public void execute(VxmlObject obj, Node fnode) {
		
		StringBuffer  logstr=new StringBuffer();
		Node vname = fnode.getAttributes().getNamedItem("name");
		if(vname!=null)
		{
			Node vexpr = fnode.getAttributes().getNamedItem("expr");
			if(obj.getExecNode().getNodeName().equals("form"))
			{
				VxmlForm vform = (VxmlForm)obj.getExecNode();
				if(vform.getLocalVar().containsKey(vname.getNodeValue()))
					vform.getLocalVar().put(vname.getNodeValue(),vexpr.getNodeValue());
				else
					if(obj.getGlobalVar().containsKey(vname.getNodeValue()))
						obj.getGlobalVar().put(vname.getNodeValue(),vexpr.getNodeValue());
			}
		}
		
			
	}

	
}
